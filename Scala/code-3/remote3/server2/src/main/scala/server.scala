import akka.actor.{Actor, ActorRef, ActorSystem, Props}
import com.typesafe.config.ConfigFactory

import scala.collection.mutable
import scala.collection.mutable.HashMap
import scala.io.Source


case class Word(word: String, title: String)
case object Flush
case object Done

class MapActor extends Actor {

  var idHash2 = Math.abs(self.hashCode())

  val numberReducers  = ConfigFactory.load.getInt("number-reducers")
  var numberMappers = ConfigFactory.load.getInt("number-mappers")
  val numReducers = numberReducers
  var reduceRouterActors = List()
  var reduceActors = List[ActorRef]()
  for (i <- 0 until numberReducers)
    reduceActors = context.actorOf(Props[ReduceActor], name = "reduce"+i)::reduceActors
  val STOP_WORDS_LIST = List("a", "am", "an", "and", "are", "as", "at", "be",
    "do", "go", "if", "in", "is", "it", "of", "on", "the", "to", "she", "he", "her",
    "him", "He's", "She's", "his")

  //val numReducers = reduceActors.size

  def receive = {
    case (title: String, url:String)=>
      println(self.path + " received " + title + " and " + url + " at hash " + idHash2)  ////
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

object Server extends App {
  val system = ActorSystem("GreetingSystem", ConfigFactory.load.getConfig("server"))
  println("Server ready")
  Thread.sleep(30000)
  system.terminate
}


class ReduceActor extends Actor {
  var remainingMappers = ConfigFactory.load.getInt("number-mappers")
  var reduceMap = HashMap[String, Int]()

  //var reduceMap = HashMap[String, String]()
  var reduceMapTitle = HashMap[String, mutable.Set[String]]()
  val books = Set("A Tale of Two Cities", "A Christmas Carol", "Great Expecations", "The Pickwick Papers")

  def receive = {
    case (titleContent, Word(word, title)) =>
      //println("inside the server2 reducer")
      //case (Word(word, title)) =>
      if(reduceMapTitle.contains(word)){
        //if (reduceMap.contains(word)) {
        reduceMapTitle += (word -> (reduceMapTitle(word) += title))
      }

      else
        reduceMapTitle += (word -> (scala.collection.mutable.Set.empty[String] += title))
    //reduceMap += (word -> title)

    //println(reduceMapTitle)

    case Flush =>
      remainingMappers -= 4
      if (remainingMappers == 0) {
        println(self.path.toStringWithoutAddress + " : " + reduceMapTitle)
        //println(self.path.toStringWithoutAddress + " : " + reduceMap)   //prints each reducers map results
        context.parent ! Done
      }
  }
}





