package common_

import akka.actor.{Actor, ActorRef}
import akka.routing.Broadcast

import scala.collection.mutable
import scala.io.Source

class MapActor(reduceActors: ActorRef) extends Actor {

  val STOP_WORDS_LIST = List("a", "am", "an", "and", "are", "as", "at", "be",
    "do", "go", "if", "in", "is", "it", "of", "on", "the", "to", "she", "he", "her",
    "him", "He's", "She's", "his")

  Thread sleep 2000
  println(self.path)

  def receive = {
    case Book(title, url)=>
      process(title, url)

    case Flush =>
      reduceActors ! Broadcast(Flush)
  }

  def process(title: String, url:String ) = {

    val content = getContent(url)
    var namesFound = mutable.HashSet[String]()    // finish this

    for (word <- content.split("[\\p{Punct}\\s]+")) {
      if (word.charAt(0).isUpper && word.length >= 3 && !STOP_WORDS_LIST.contains(word) && !namesFound.contains(word)) {
        reduceActors ! Word(word, title)
      }
    }
  }

  def getContent(url: String) = {
    try{
      Source.fromURL(url).mkString
    } catch{
      case e: Exception => "Fail"
    }
  }
}