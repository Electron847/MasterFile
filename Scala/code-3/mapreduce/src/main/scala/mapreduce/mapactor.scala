
package mapreduce

import akka.actor.{Actor, ActorRef}

import scala.collection.mutable.HashMap
import scala.io.Source

class MapActor(reduceActors: List[ActorRef]) extends Actor {

    val STOP_WORDS_LIST = List("a", "am", "an", "and", "are", "as", "at", "be",
      "do", "go", "if", "in", "is", "it", "of", "on", "the", "to", "she", "he", "her",
      "him", "He's", "She's", "his")

  val numReducers = reduceActors.size

  def receive = {
    case (title: String, url:String)=>
      val htmlTitle = Source.fromURL(url)
      val contentString = htmlTitle.mkString
      process(title, contentString)

    case Flush =>
      for (i <- 0 until numReducers) {
        reduceActors(i) ! Flush
      }
  }

  def process(titleContent: String, bookToProcessContent:String ) = {

      var myMap = HashMap[String, Integer]()

      for (word <- bookToProcessContent.split("[\\p{Punct}\\s]+")) {
        if (word.charAt(0).isUpper && word.length >= 3 && !STOP_WORDS_LIST.contains(word)) {
          var reducerID = Math.abs((word.hashCode()) % numReducers)

          //println(word, Math.abs(word.hashCode), titleContent)
          //reduceActors(index) ! (bookToProcessContent,Word(word, 1))
          reduceActors(reducerID) ! (titleContent, Word(word, titleContent))
        }
      }
    }
}