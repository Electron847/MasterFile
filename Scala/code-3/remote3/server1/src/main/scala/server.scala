import akka.actor.{Actor, ActorRef, ActorSystem, Props}
import akka.routing.ConsistentHashingPool
import akka.routing.ConsistentHashingRouter.{ConsistentHashMapping, ConsistentHashable}
import com.typesafe.config.ConfigFactory

import scala.collection.mutable
import scala.collection.mutable.HashMap
import scala.io.Source


case class Word(word: String, title: String)
case object Flush
case object Done

class MapActor extends Actor {

  var idHash = Math.abs(self.hashCode())
  val numberReducers  = ConfigFactory.load.getInt("number-reducers")
  var numberMappers = ConfigFactory.load.getInt("number-mappers")
  val numReducers = numberReducers
  var pending = numberReducers


  val STOP_WORDS_LIST = List("a", "am", "an", "And", "are", "as", "at", "be",
    "do", "go", "if", "in", "is", "it", "of", "on", "the", "to", "She", "he", "Her",
    "Him", "He's", "She's", "his")


  var conMap = Map.empty[String,String]


  var reduceRouterActors = List()
  var reduceActors = List[ActorRef]()
  for (i <- 0 until numberReducers)
    reduceActors = context.actorOf(Props[ReduceActor], name = "reduce"+i)::reduceActors

  def receive = {
    case Entry(key, value) =>
      conMap += (key -> value)

    case (title: String, url:String)=>

      //var idHash = Math.abs(self.hashCode())
      println(self.path + " received " + title + " and " + url + " at hash " + idHash)

      println("inside server mapActor")
      val htmlTitle = Source.fromURL(url)
      val contentString = htmlTitle.mkString
      process(title, contentString)

    case Flush =>
      for (i <- 0 until numReducers) {
        reduceActors(i) ! Flush
      }
  }

  final case class Evict(key: String)

  final case class Get(key: String) extends ConsistentHashable {
    override def consistentHashKey: Any = key
  }

  final case class Entry(key: String, value: String)

  def hashMapping: ConsistentHashMapping = {
    case Evict(key) => key
  }


  var cache: ActorRef =
    context.actorOf(ConsistentHashingPool(numberMappers, hashMapping = hashMapping).props(Props[ReduceActor]), name = "cache")


  def process(titleContent: String, bookToProcessContent:String ) = {

    var myMap = mutable.HashMap[String, Integer]()

    for (word <- bookToProcessContent.split("[\\p{Punct}\\s]+")) {
      if (word.charAt(0).isUpper && word.length >= 3 && !STOP_WORDS_LIST.contains(word)) {
        var reducerID = Math.abs((word.hashCode()) % numberReducers)
        //println("MapActor with hashID: " + idHash + " created by routerHashID "+ sender().hashCode()+ " now sending word")
       // println(word, Math.abs(word.hashCode), titleContent)
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

  var senderHashID = Math.abs(sender().hashCode())
  var selfHashId = Math.abs(self.hashCode())
  selfHashId = senderHashID

  println("remote reducer was created! from sender hash " + selfHashId)

  //var senderHashID = Math.abs(sender().hashCode())

  var remainingMappers = ConfigFactory.load.getInt("number-mappers")
  var reduceMap = HashMap[String, Int]()

  //var reduceMap = HashMap[String, String]()
  var reduceMapTitle = HashMap[String, mutable.Set[String]]()
  val books = Set("A Tale of Two Cities", "A Christmas Carol", "Great Expecations", "The Pickwick Papers")

  def receive = {
    case (titleContent, Word(word, title)) =>
      //println("inside the server1 reducer from sender with hashcode " + selfHashId)
      //case (Word(word, title)) =>
      if(reduceMapTitle.contains(word)){
      //if (reduceMap.contains(word)) {
        reduceMapTitle += (word -> (reduceMapTitle(word) += title))
        //reduceMap += (word -> (reduceMap(word) + " " + (title)))
      }

      else
        reduceMapTitle += (word -> (scala.collection.mutable.Set.empty[String] += title))
        //reduceMap += (word -> title)

      //println(reduceMapTitle)

    case Flush =>
      remainingMappers -= 4
      println("nummappers remaining = " + remainingMappers)
      if (remainingMappers == 0) {
        println(self.path.toStringWithoutAddress + " : " + reduceMapTitle)
        //println(self.path.toStringWithoutAddress + " : " + reduceMap)   //prints each reducers map results
        context.parent ! Done
      }

  }
}


