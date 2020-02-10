import Client.masterRouter
import akka.actor.{Actor, ActorSystem, AddressFromURIString, Props}
import akka.remote.routing.RemoteRouterConfig
import akka.routing.{Broadcast, RoundRobinPool}
import com.typesafe.config.ConfigFactory

import scala.collection.mutable.HashMap
import scala.io.Source

class MapActor extends Actor {
  val numberMappers  = ConfigFactory.load.getInt("number-mappers")
  val numberReducers  = ConfigFactory.load.getInt("number-reducers")
  var pending = numberReducers


  val STOP_WORDS_LIST = List("a", "am", "an", "and", "are", "as", "at", "be",
    "do", "go", "if", "in", "is", "it", "of", "on", "the", "to", "she", "he", "her",
    "him", "He's", "She's", "his")

  def receive = {
    case (title: String, url:String)=>
      val htmlTitle = Source.fromURL(url)
      val contentString = htmlTitle.mkString
      println("inside client mapActor")
      process(title, contentString)

    case Flush =>
      masterRouter ! Broadcast(Flush)
  }

  def process(titleContent: String, bookToProcessContent:String ) = {

    var myMap = HashMap[String, Integer]()

    for (word <- bookToProcessContent.split("[\\p{Punct}\\s]+")) {
      if (word.charAt(0).isUpper && word.length >= 3 && !STOP_WORDS_LIST.contains(word)) {
        var reducerID = Math.abs((word.hashCode()) % numberReducers)

        //println(word, Math.abs(word.hashCode), titleContent)
        //reduceActors(index) ! (bookToProcessContent,Word(word, 1))
        //reduceActors(reducerID) ! (titleContent, Word(word, titleContent))
      }
    }
  }
}


object Client extends App {

  val numberMappers  = ConfigFactory.load.getInt("number-mappers")
  val numberReducers  = ConfigFactory.load.getInt("number-reducers")
  var pending = numberReducers

  val system = ActorSystem("GreetingSystem", ConfigFactory.load.getConfig("remotelookup"))

  //val master = system.actorOf(Props[MasterActor], name = "master")

  val htmlTwoCities = Source.fromURL("http://www.gutenberg.org/files/98/98-0.txt")
  val twoCities = htmlTwoCities.mkString
  val htmlThePickwickPapers = Source.fromURL("http://www.gutenberg.org/files/580/580-0.txt")
  val ThePickwickPapers = htmlThePickwickPapers.mkString
  val htmlChristmasCarol = Source.fromURL("http://www.gutenberg.org/files/46/46-0.txt")
  val christmasCarol = htmlChristmasCarol.mkString
  val htmlGreatExpectations = Source.fromURL("http://www.gutenberg.org/files/1400/1400-0.txt")
  val greatExpectations = htmlGreatExpectations.mkString

  val addresses = Seq(
    AddressFromURIString("akka.tcp://GreetingSystem@127.0.0.1:2552"),
    AddressFromURIString("akka.tcp://GreetingSystem@127.0.0.1:2553"))

  val masterRouter = system.actorOf(
    RemoteRouterConfig(RoundRobinPool(numberMappers), addresses).props(Props[MapActor]))

  masterRouter ! ("Tale of Two Cities", " http://www.gutenberg.org/files/98/98-0.txt")
  masterRouter ! ("A Christmas Carol", "http://www.gutenberg.org/files/46/46-0.txt")
  masterRouter ! ("Great Expectations", "http://www.gutenberg.org/files/1400/1400-0.txt")
  masterRouter ! ("The Pickwick Papers", "http://www.gutenberg.org/files/580/580-0.txt")

  masterRouter ! Flush


  /*case Flush =>
    mapActors ! Broadcast(Flush)    //sends Flush to all mapActors
  case Done =>
    pending -= 1
  if (pending == 0)
    context.system.terminate*/

  //println("Client has sent 4 hellos to routerRemote")
  Thread.sleep(30000)
  system.terminate
}


/*

class MasterActor extends Actor {

  val numberMappers  = ConfigFactory.load.getInt("number-mappers")
  val numberReducers  = ConfigFactory.load.getInt("number-reducers")
  var pending = numberReducers

  var reduceActors = List[ActorRef]()
  for (i <- 0 until numberReducers)
    reduceActors = context.actorOf(Props[ReduceActor], name = "reduce"+i)::reduceActors

  //val mapActors = context.actorOf(RoundRobinPool(numberMappers).props(Props(classOf[MapActor], reduceActors)))
  val mapActors = context.actorOf(RoundRobinPool(numberMappers).props(Props(classOf[MapActor], reduceActors)))

  def receive = {
    case (title: String, url: String) =>
      var mapperID = mapActors.hashCode()
      //println(mapActors.hashCode())
      println("MasterClient "+self.path + " received " + title + " " + url + " from sender " + mapperID)
      //mapActors ! (title, url)

    //case msg: String =>
     // println(self.path + " received " + msg + " from " + sender)
    case _ => println("Received unknown msg ")

  }
}
*/




