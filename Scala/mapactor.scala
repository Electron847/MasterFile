package mapreduce

import akka.actor.{Actor, ActorRef}
import scala.io.Source

class MapActor(reduceActors: List[ActorRef]) extends Actor {


//  val STOP_WORDS_LIST = List("a", "am", "an", "and", "are", "as", "at", "be",
//    "do", "go", "if", "in", "is", "it", "of", "on", "the", "to")

  val STOP_WORDS_LIST = List("WHATISTHISFOR")

  val numReducers = reduceActors.size

  def receive = {
    case (title: String, url:String)=>

      val htmlTitle = Source.fromURL(url)
      val titleString = htmlTitle.mkString

      println(title + ": word count = " + titleString.length)
      println("url = " + url + "\n")
      process(title, titleString)


    case Flush => 
      for (i <- 0 until numReducers) {
        reduceActors(i) ! Flush
      }
  }

  def process(titleContent: String, bookToProcessContent:String ) = {
    //if ((!STOP_WORDS_LIST.contains(word))) {

    // change titleContent to bookToProcessContent (just using the titles to test easier)
    for (word <- titleContent.split("[\\p{Punct}\\s]+")) {
      if (word.charAt(0).isUpper) {
        var index = Math.abs((word.hashCode()) % numReducers)
        //will need to change this cross reference instead of just accumulating the specific capatalized words
        reduceActors(index) ! (titleContent,Word(word, 1))
      }
    }
  }
}
