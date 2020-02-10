package client

import akka.actor.{Actor, ActorSystem, Address, PoisonPill, Props, Terminated}
import akka.event.Logging
import akka.remote.routing.RemoteRouterConfig
import akka.routing.{Broadcast, RoundRobinPool}
import client.DeathWatchClient.system
import com.typesafe.config.ConfigFactory
//import common.{Message, SimpleActor}


class RouterSupervisor extends Actor {
  val log = Logging(context.system, this)

  val addresses = Seq (
    //Address("akka", "SimpleSystem"),
    Address("akka.tcp", "MapReduceServer", "127.0.0.1", 2554)
  )

  val simpleRouted = system.actorOf(RemoteRouterConfig(RoundRobinPool(nrOfInstances = 1),addresses).props(Props(classOf[SimpleActor])))
  context.watch(simpleRouted) // supervise router

  def receive = {
    case Terminated(corpse) =>
      if (corpse == simpleRouted) {
        log.warning("Received termination notification for '" + corpse + "'," +
          "is in our watch list. Terminating ActorSystem.")
        DeathWatchClient.system.terminate
      }
  }

  simpleRouted ! Broadcast(Message("I will not buy this record, it is scratched!"))
  for (n <- 1 to 10) simpleRouted ! Message("Hello, Akka #%d!".format(n))
  simpleRouted ! Broadcast(PoisonPill)
  //simpleRouted ! Message("Hello? You're looking a little green around the gills...") // never gets read

}

object DeathWatchClient extends App {
  val system = ActorSystem("SimpleSystem", ConfigFactory.load.getConfig("client"))
  val supervisor= system.actorOf(Props[RouterSupervisor], name="routerSupervisor")
}