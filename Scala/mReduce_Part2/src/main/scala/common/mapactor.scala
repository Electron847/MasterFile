
package common

import akka.actor.{Actor, ActorRef}
import akka.routing.Broadcast

import scala.collection.mutable
import scala.io.Source

class MapActor(reduceActors: ActorRef) extends Actor {

  println(self.path)

  Thread sleep 2000

    val STOP_WORDS_LIST = List("a", "am", "an", "and", "are", "as", "at", "be",
      "do", "go", "if", "in", "is", "it", "of", "on", "the", "to", "she", "he", "her",
      "him", "He's", "She's", "his")

  def receive = {
    case Book(title, url)=>
      process(title, url)
    case Flush =>
      reduceActors ! Broadcast(Flush)
  }

  def process(title: String, url:String ) = {

    val content = getContent(url)
    var namesFound = mutable.HashSet[String]()

      for (word <- content.split("[\\p{Punct}\\s]+")) {
        //if (word(0).isUpper && !STOP_WORDS_LIST.contains(word) && !namesFound.contains(word)) {
        if(word(0).isUpper){
          //Thread.sleep(2000)
          reduceActors ! Word(word, title)
          //namesFound += word
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