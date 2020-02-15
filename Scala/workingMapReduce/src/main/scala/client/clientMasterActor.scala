package client

import akka.actor.{Actor, Address, Props}
import akka.remote.routing.RemoteRouterConfig
import akka.routing.ConsistentHashingRouter.ConsistentHashMapping
import akka.routing.{Broadcast, ConsistentHashingPool, RoundRobinPool}
import com.typesafe.config.ConfigFactory
import common_._


class clientMasterActor extends Actor {

  val numberMappers = ConfigFactory.load.getInt("number-mappers")
  val numberReducers = ConfigFactory.load.getInt("number-reducers")
  var pending = numberReducers


  val addresses = Seq (
    Address("akka", "MapReduceClient"),
    Address("akka.tcp", "MapReduceServer", "127.0.0.1", 2555)
  )

  def hashMapping: ConsistentHashMapping = {
    case Word(word, title) => word
  }


  val reduceActors = context.actorOf(RemoteRouterConfig(ConsistentHashingPool(numberReducers, hashMapping = hashMapping), addresses).props(Props[ReduceActor]))


  val mapActors = context.actorOf(RemoteRouterConfig(RoundRobinPool(numberMappers),addresses).props(Props(classOf[MapActor], reduceActors)))

  def receive = {
    case msg: Book =>
      mapActors ! msg
    case Flush =>
      mapActors ! Broadcast(Flush) //sends Flush to all mapActors
    case Done =>
      println("Done from sender " + sender)
      pending -= 1
      if (pending == 0)
        context.system.terminate
  }
}


















