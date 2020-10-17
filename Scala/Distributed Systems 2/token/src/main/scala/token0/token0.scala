package token0;

import akka.actor.{Actor, ActorRef, ActorSystem, Props}
import scala.collection.mutable.{Map, Queue}

// Case classes and objects
case class Neighbors(left:ActorRef, right:ActorRef, across:ActorRef)
case object PING // goes clockwise
case object PONG // goes counterclockwise
case object PANG // goes diagonal

// SNAPSHOT: token classes
sealed trait Token
case object Marker extends Token
case object Start extends Token

class PingPong extends Actor {
  var left = self
  var right = self
  var across = self
  var PINGcount = 0
  var PONGcount = 0
  var PANGcount = 0
  // SNAPSHOT: data structures
  var snapshot = Array(0, 0, 0)
  var inSnapshot = false
  var recording = Map[ActorRef, Queue[Any]]()
  var closed = Map[ActorRef, Boolean]()
  var numOpen = 3
  var transitMsgsCaught1 = 0
  var transitMsgsCaught2 = 0
  var transitMsgsCaught3 = 0

  def receive = {

    case PING =>
      PINGcount = PINGcount + 1
      printf("%12s : PING=%d, PONG=%d, PANG=%d\n", self.path.toStringWithoutAddress, PINGcount, PONGcount, PANGcount)
      // SNAPSHOT: record message in transit
      if (inSnapshot && !closed(sender)) {
        recording(sender).enqueue(PING)
        printf("%12s : MESSAGE RECORDING\n%26s ---> %s\n", self.path.toStringWithoutAddress, sender.path.toStringWithoutAddress, PING)
      }
      Thread sleep 500
      left ! PING

    case PONG =>
      PONGcount = PONGcount + 1
      printf("%12s : PING=%d, PONG=%d, PANG=%d\n", self.path.toStringWithoutAddress, PINGcount, PONGcount, PANGcount)
      // SNAPSHOT: record message in transit
      if (inSnapshot && !closed(sender)) {
        recording(sender).enqueue(PONG)
        printf("%12s : MESSAGE RECORDING\n%26s ---> %s\n", self.path.toStringWithoutAddress, sender.path.toStringWithoutAddress, PONG)
      }
      Thread sleep 500
      right ! PONG

    case PANG =>
      PANGcount = PANGcount + 1
      printf("%12s : PING=%d, PONG=%d, PANG=%d\n", self.path.toStringWithoutAddress, PINGcount, PONGcount, PANGcount)
      // SNAPSHOT: record message in transit
      if (inSnapshot && !closed(sender)) {
        recording(sender).enqueue(PANG)
        printf("%12s : MESSAGE RECORDING\n%26s ---> %s\n", self.path.toStringWithoutAddress, sender.path.toStringWithoutAddress, PANG)
      }
      Thread sleep 500
      across ! PANG

    // SNAPSHOT: snapshot message
    case token : Token =>
      if (inSnapshot) {
        // SNAPSHOT: stop recording from sender + print state if done
        closed(sender) = true
        numOpen = numOpen - 1
        if (numOpen == 1) {
          printf("%12s : SNAPSHOT\n               STATE: %d %d %d\n               MESSAGES IN TRANSIT:\n               %s--->%s\n               %s--->%s\n               %s--->%s\n", self.path.toStringWithoutAddress, snapshot(0), snapshot(1), snapshot(2),  left.path.toStringWithoutAddress, recording(left), right.path.toStringWithoutAddress, recording(right), across.path.toStringWithoutAddress, recording(across) )
          inSnapshot = false
          if (!recording(left).isEmpty)
            transitMsgsCaught1 += 1
            println(self.path.toStringWithoutAddress + " clockwise caught: " + transitMsgsCaught1)
          if (!recording(right).isEmpty)
            transitMsgsCaught2 += 1
            println(self.path.toStringWithoutAddress + " counterclockwise caught: " + transitMsgsCaught2)
          if (!recording(across).isEmpty)
            transitMsgsCaught3 += 1
            println(self.path.toStringWithoutAddress + " diagonal caught: " + transitMsgsCaught3)
        }
      } else {
        // SNAPSHOT: start snapshot
        //printf("%12s : STARTING SNAPSHOT\n               STATE: %d %d %d\n", self.path.toStringWithoutAddress, PINGcount, PONGcount, PANGcount)
        inSnapshot = true
        snapshot(0) = PINGcount
        snapshot(1) = PONGcount
        snapshot(2) = PANGcount
        recording(left) = Queue[Any]()
        recording(right) = Queue[Any]()
        recording(across) = Queue[Any]()
        closed(left) = false
        closed(right) = false
        closed(across) = false
        if (token == Marker) {
          closed(sender) = true
          numOpen = 2
        } else {
          numOpen = 3
        }
        //printf("%12s : SENDING MARKER TO NEIGHBORS\n", self.path.toStringWithoutAddress)
        left ! Marker
        right ! Marker
        across ! Marker
      }

    case Neighbors(l, r, c) =>
      left = l
      right = r
      across = c
  }
}

object Server extends App {
  val system = ActorSystem("PingPong")
  val first = system.actorOf(Props[PingPong], name = "first")
  val second = system.actorOf(Props[PingPong], name = "second")
  val third = system.actorOf(Props[PingPong], name = "third")
  val fourth = system.actorOf(Props[PingPong], name = "fourth")
  // Send instructions/messages to actors in the system
  first ! Neighbors(second, third, fourth)
  second ! Neighbors(fourth, first, third)
  third ! Neighbors(first, fourth, second)
  fourth ! Neighbors(third,second,first)
  first ! PING
  first ! PONG
  first ! PANG
  second ! PING
  second ! PONG
  second ! PANG
  third ! PING
  third ! PONG
  third ! PANG
  first ! Start
  Thread.sleep(10000)
  system.terminate
}
