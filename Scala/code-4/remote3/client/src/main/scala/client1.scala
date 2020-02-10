import akka.actor.{ActorSystem, AddressFromURIString, Props}
import akka.remote.routing.RemoteRouterConfig
import akka.routing.RoundRobinPool
import com.typesafe.config.ConfigFactory



object Client extends App {
  val system = ActorSystem("GreetingSystem", ConfigFactory.load.getConfig("remotelookup"))

  val addresses = Seq(
    AddressFromURIString("akka.tcp://GreetingSystem@127.0.0.1:2552"),
    AddressFromURIString("akka.tcp://GreetingSystem@127.0.0.1:2553"))

  val routerRemote = system.actorOf(
    RemoteRouterConfig(RoundRobinPool(4), addresses).props(Props[Joe]))

  routerRemote ! "1st hello from a remote client!"
  routerRemote ! "2nd hello from a remote client!"
  routerRemote ! "3rd hello from a remote client!"
  routerRemote ! "4th hello from a remote client!"
  println("Client has sent 4 hellos to routerRemote")
  Thread.sleep(30000)
  system.terminate
}
