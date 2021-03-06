package server

import akka.actor.ActorSystem
import com.typesafe.config.ConfigFactory


object DeathWatchServer extends App {
  val system = ActorSystem("MapReduceServer", ConfigFactory.load.getConfig("server"))

}