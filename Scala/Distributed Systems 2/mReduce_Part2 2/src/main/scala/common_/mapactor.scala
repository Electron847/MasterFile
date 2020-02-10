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

  val FLAG_WORDS_LIST = List("Dispensary\nMedical", "Quality", ">Av\n$/\n1g", "Low", "Medium", "High",
                              "Dispensary\nRecreational", "Street\nMedical")

  val HTML_PARSE_LIST = List("data-classes", "table-no-bordered", "data-toggle", "href", "bdzfooter", "src", "class", "data-index")

  println(self.path)
  Thread.sleep(2000)

  def receive = {

    case justContent (url) =>
      val pageContent = Source.fromURL(url).mkString
      val hyperlinkToProcess = new URL(url)
      val infoStream = hyperlinkToProcess.openStream()
      val buf = collection.mutable.Buffer[Byte]()
      var res = infoStream.read()
      while (res > 0) {
        buf += res.toByte
        res = infoStream.read()
      }
      var reBufferedContent = new String(buf.toArray)
      for (line <- reBufferedContent.split("\\s+")){
        if (line.contains("href") || line.contains("HREF")){
          var trimmedLine = line.slice(line.indexOf("\"") + 1, line.lastIndexOf("\""))
          if (trimmedLine.startsWith("http") && !trimmedLine.contains("/css") && trimmedLine.contains("/usa")){
            //crawlPages(trimmedLine)     //comment in and out for massive data
            //reduceActors ! incomingHyperLinks(url, trimmedLine)
            println(trimmedLine)
            val insideContent = getContent(trimmedLine)
            println(insideContent.length)     //add .length to compress content to solely state url
          }
        }
      }
      infoStream.close()

    case onePageContent(url) =>
      var elementList = scala.collection.mutable.ListBuffer.empty[String]   //set up a list for index manipulation
      var proxyList = scala.collection.mutable.ListBuffer.empty[String]
      val singlePageHTML = Source.fromURL(url).mkString
      for (line <- singlePageHTML.split("\\s+")){
        if (!line.contains("href") || !line.contains("<hr>")) {
          //var strippedLine = line.slice(line.indexOf("\"") + 1, line.lastIndexOf("\""))
          elementList += line
          //println(line)
        }
      }
      for (element <- elementList){
        if(element.contains("href") || element.contains("class") || element.contains("data") || element.contains("src") || element.contains("table") || element.contains("</table>") || element.contains("padding") || element.contains("name") || element.contains("li>") || element.contains("ul>") || element.contains("text") || element.contains("tbody") || element.contains("margin") || element.contains("span")) {
          //println(element)
          elementList= elementList.-=(element)    //list of elements to manipulate
        }
      }
      for (element <- elementList) {
        if (element.contains("<hr>") || element.contains("rel") || element.contains("<div") || element.contains("<td") || element.contains("px") || element.contains("/th") || element.contains("center") || element.contains("</tr>") || element.contains("b>") || element.contains("<tr") || element.contains("<th") || element.contains("top") || element.contains("float") || element.contains("please") || element.contains("wait")){
          elementList= elementList.-=(element)
        }
      }

      for (element <- elementList){
        if(element.contains("style=\"display:") || element.contains("</a>") || element.contains("</label>") || element.containsSlice("style=\"color:") || element.contains("\">-</td>")){
          elementList= elementList.-=(element)
         // println(element)
        }
    //    if(element.contains(",")){
      //    elementList= elementList.-=(element)
        //}
      }

      //prints out the elementList, turned into a string and split on the "</div>" anchor
      for(word <- elementList.toString().split("</div>")){
        proxyList += word
        println(word)
        if (word.contains()) {

          // println(word) //+ " index " + proxyList.indexOf(word))     //prints a readable extraction, but it is part clean
        }
      }

      for (word <- proxyList.toString().split(",")) {      //"[\\p{Punct}\\s]+" if you'd like some raw text strict data, stripped of $%&!@ symbols -OR- '\\s' for split on space
        //println(word)     //prints a differently part readable version with all spaces (\n) still there
        if (!word.equals(" ")) {  //strips out empty spaces
         //println(word) //prints a condensed cleaner version of above but somehow not as readable
        }
      }
      println("Length is " + proxyList.length)


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
            //print(trimmedLine + "\n")     //prints every url on first encountered page
            //crawlPages(trimmedLine)       //comment in and out for massive data
            reduceActors ! incomingHyperLinks(url, trimmedLine)     //
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
        if (trimmedLine.startsWith("http") && trimmedLine.contains("price") && !trimmedLine.contains("/css")){
          //crawlPages(trimmedLine)       //setting up for recursive function
          //reduceActors ! incomingHyperLinks(hyperlink, trimmedLine)     //implements the bottom layer of sending crawled pages links, Page Rank
          println("i am a :" + trimmedLine)
        }
      }
    }
    pageStream.close()
  }


  def processCrawl(title: String, url: String): Unit = {

  }
}