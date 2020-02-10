
package common_

import akka.actor.{Actor, ActorRef}

import scala.collection.mutable
import scala.io.Source

class MapActor(reduceActors: ActorRef) extends Actor {

    val STOP_WORDS_LIST = List("a", "am", "an", "and", "are", "as", "at", "be",
      "do", "go", "if", "in", "is", "it", "of", "on", "the", "to", "she", "he", "her",
      "him", "He's", "She's", "his")

  def receive = {
    case Book(title, url)=>
      val htmlTitle = Source.fromURL(url)
      val contentString = htmlTitle.mkString
      process(title, contentString)

    case Flush =>
      //reduceActors ! Broadcast(Flush)
  }

  def process(titleContent: String, bookToProcessContent:String ) = {

      //var myMap = HashMap[String, Integer]()

    var namesFound = mutable.HashSet[String]()    // finish this

      for (word <- bookToProcessContent.split("[\\p{Punct}\\s]+")) {
        if (word.charAt(0).isUpper && word.length >= 3 && !STOP_WORDS_LIST.contains(word) && !namesFound.contains(word)) {
          reduceActors ! Word(titleContent, bookToProcessContent)


          //var reducerID = Math.abs((word.hashCode()) % numReducers)

          //println(word, Math.abs(word.hashCode), titleContent)
          //reduceActors(index) ! (bookToProcessContent,Word(word, 1))
          //reduceActors(reducerID) ! (titleContent, Word(word, titleContent))
        }
      }
    }
}