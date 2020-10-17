package SimpleActing

import akka.actor.{Actor, ActorRef, ActorSystem, Props}

case class Neighbors(left:ActorRef, right:ActorRef)
case object East //timezones ahead of current time
case object West //timezones behind current time

sealed trait Time
case object TimeZoneChange extends Time
case object Start extends Time

class SimpleActor extends Actor{
  var left = self
  var right = self
  var revolving_time = 0
  var time_west = -1
  var time_east = 1

  def receive = {
    //case s: Unit => println("string: " + s)
    //case i: Int => println("Number: " + i )
    //case n: String => foo
    case East =>
      revolving_time = revolving_time + time_east
      println("Relative Eastern Time: " + revolving_time)
      Thread sleep 20
      left ! West
    case West =>
      revolving_time = revolving_time + time_west
      println("Relative Western Time: " + time_west)
      Thread sleep 20
      right ! East
    case Neighbors(l, r) =>
      left = l
      right = r
  }
  //def foo = println("Normal Method")
}

object Server extends App {
  val system = ActorSystem("SimpleSystem")
  val actor1 = system.actorOf(Props[SimpleActor],"Americas")
  val actor2 = system.actorOf(Props[SimpleActor],"Asia")
  val actor3 = system.actorOf(Props[SimpleActor],"Europe")
  actor1 ! Neighbors(actor2,actor3)
  actor2 ! Neighbors(actor3,actor1)
  actor3 ! Neighbors(actor1,actor2)

  actor1 ! West
  actor1 ! East
  Thread.sleep(50)
  actor1 ! Start
  Thread.sleep(600)
  //actor2 ! Start
  Thread.sleep(600)
  //actor ! "Hi There."
  //actor ! 42
  //actor ! "Hello"
  system.terminate
}
