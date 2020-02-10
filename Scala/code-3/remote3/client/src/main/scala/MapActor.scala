import akka.actor.{Actor, ActorRef, ActorSystem, AddressFromURIString}
import com.typesafe.config.ConfigFactory

import scala.collection.mutable
import scala.io.Source

case class Word(word: String, title: String)
case object Flush
case object Done

class MapActor(mapperRouterRemote:ActorRef) extends Actor {

  val addresses = Seq(
    AddressFromURIString("akka.tcp://GreetingSystem@127.0.0.1:2552"),
    AddressFromURIString("akka.tcp://GreetingSystem@127.0.0.1:2553"))

  val STOP_WORDS_LIST = List("a", "am", "an", "and", "are", "as", "at", "be",
    "do", "go", "if", "in", "is", "it", "of", "on", "the", "to", "she", "he", "her",
    "him", "He's", "She's", "his")

  val numberReducers  = ConfigFactory.load.getInt("number-reducers")

  val system1 = ActorSystem("GreetingSystem", ConfigFactory.load.getConfig("remotelookup"))


  //val reducersRouterRemote:ActorRef = system1.actorOf(
   // RemoteRouterConfig(RoundRobinPool(numberReducers), addresses).props(Props[ReduceActor], reducersRouterRemote))


  //val mapperRouterRemote:ActorRef = system.actorOf(
  //  RemoteRouterConfig(RoundRobinPool(numberReducers), addresses).props(Props[MapActor], reducersRouterRemote))


  val numReducers = numberReducers

  def receive = {
    case (title: String, url:String)=>

      println("received by mappers!")
      val htmlTitle = Source.fromURL(url)
      val contentString = htmlTitle.mkString

      process(title, contentString)


    /*case Flush =>
      for (i <- 0 until numReducers) {
        reducerActors(i) ! Flush
      }*/
  }

  def process(titleContent: String, bookToProcessContent:String ) = {

    var myMap = mutable.HashMap[String, Integer]()



    for (word <- bookToProcessContent.split("[\\p{Punct}\\s]+"))
    {
      if (word.charAt(0).isUpper && word.length >= 3 && !STOP_WORDS_LIST.contains(word))
      {
        var reducerID = Math.abs((word.hashCode()) % numReducers)
        println(self.path.name + " " + word, Math.abs(word.hashCode), titleContent + " " + sender.path.name)
        //reduceActors(index) ! (bookToProcessContent,Word(word, 1))
        //mapperRouterRemote(reducerID) ! Word(word, titleContent)
      }
    }
  }
}

