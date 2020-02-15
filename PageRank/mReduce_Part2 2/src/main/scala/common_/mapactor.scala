package common_

import java.net.URL
import akka.actor.{Actor, ActorRef}
import akka.routing.Broadcast
import scala.io.Source

class MapActor(reduceActors: ActorRef) extends Actor {

    val STOP_WORDS_LIST = List("a", "am", "an", "and", "are", "as", "at", "be",
      "do", "go", "if", "in", "is", "it", "of", "on", "the", "to", "she", "he", "her",
      "him", "He's", "She's", "his", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "bdi", "bdo",
      "blockquote", "dd", "script", "div", "figcaption", "embed", "fieldset", "footer", "frameset",
      "html", "optgroup", "param", "section", "tbody", "tfoot", "textarea")

  println(self.path)
  Thread.sleep(2000)

  def receive = {

    case Book(title, url) =>
      val content = getContent(url)
      for (word <- content.split("[\\p{Punct}\\s]+")) {
        reduceActors ! WordCount(word, 1)
        if (word.charAt(0).isUpper && word.length >= 3 && !STOP_WORDS_LIST.contains(word)) {
           reduceActors ! Word(word, title)
        }
      }

    case Capitalized(title,url) =>
      val content = getContent(url)
      for (word <- content.split("[\\p{Punct}\\s]+")) {
        reduceActors ! WordCount(word, 1)
        if (word.charAt(0).isUpper && word.length >= 3 && !STOP_WORDS_LIST.contains(word)) {
          reduceActors ! Word(word, title)
        }
      }

    case Simple_Content(url) =>
      Thread.sleep(2000)
      val urlToProcess = new URL(url)   //initialise a new URL value with the website address
      val pageStream = urlToProcess.openStream    //opens a data stream from the URL value (website address)
      val buf = collection.mutable.Buffer[Byte]()   //creates a mutable sized buffer byte array
      var res = pageStream.read()             //place a read method on the openStream operating on the URL value

      while (res > 0){                //values will always be positive while there's data to read
        buf += res.toByte             //dynamically add to the buf value (byte array) the read in stream value cast to a byte type
        res = pageStream.read()       //res variable is set to the next piece to be read from the pageStream
      }

      var reBufferedContent = new String(buf.toArray)   //initialise a
      val content = getContent(url)   //other method to get content
      for (eachWord <- content.split("[\\p{Punct}\\s]+")) {   //split based on spacing, paragraph, and punctuation
        if (eachWord.length >= 3 && !STOP_WORDS_LIST.contains(eachWord)) {
         // reduceActors ! Word(eachWord, url)    //set of url addresses each word is in
          reduceActors ! WordCount(eachWord, 1)   //word count for initial url entered
        }
      }

      for (line <- reBufferedContent.split("\\s+")){
        if (line.contains("href") || line.contains("HREF")){
          var trimmedLine = line.slice(line.indexOf("\"") + 1, line.lastIndexOf("\""))
          if (trimmedLine.startsWith("http")){
            crawlPages(trimmedLine)
            reduceActors ! incomingHyperLinks(url, trimmedLine)
          }
        }
      }
      pageStream.close()

    case Flush =>
      reduceActors ! Broadcast(Flush)
  }

  //gets all html content out of a URL
  def getContent(url: String) = {
    try{
      Source.fromURL(url).mkString
    } catch {case e: Exception => "Fail"}
  }

  def crawlPages(hyperlink: String): Unit = {

    val urlToProcess = new URL(hyperlink)
    val pageStream = urlToProcess.openStream
    val buf = collection.mutable.Buffer[Byte]()
    var res = pageStream.read()
    var pageTitle = hyperlink
    var wordCount = 0

    while (res > 0){
      buf += res.toByte
      res = pageStream.read()
    }
    var reBufferedContent = new String(buf.toArray)
    val content = getContent(hyperlink)

    for (eachWord <- content.split("[\\p{Punct}\\s]+")) {
      if (eachWord.length >= 3 && !STOP_WORDS_LIST.contains(eachWord)) {
       // reduceActors ! Word(eachWord, pageTitle)  //end product at reducer is set of url's each word is in
        reduceActors ! WordCount(eachWord, 1)   //for word counts in the crawled pages
      }
    }

    for (line <- reBufferedContent.split("\\s+")){
      if (line.contains("href") || line.contains("HREF")){
        var trimmedLine = line.slice(line.indexOf("\"") + 1, line.lastIndexOf("\""))
        if (trimmedLine.startsWith("http")){
          //crawlPages(trimmedLine)       //setting up for recursive function
          reduceActors ! incomingHyperLinks(hyperlink, trimmedLine)     //implements the bottom layer of sending crawled pages links
        }
      }
    }
    pageStream.close()
  }
}