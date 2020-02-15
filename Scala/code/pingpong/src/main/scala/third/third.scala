// To run program just copy file into IDE and remove 'package third;'
// replace package file path with relevant file paths to system
//
//
//Seth Weber

package third;

import akka.actor.{Actor, ActorRef, ActorSystem, Props}
import scala.collection.mutable

case class Spin(ls: ActorRef, rs: ActorRef)
case class Start(lA: ActorRef, rA: ActorRef)
case class state(tk1c: Int, tk2c: Int, tkm1c: Int, tkm2c: Int)

trait Orientation
case class TokenOne(myNeighbor: Spin) extends Orientation //clockwise token
case class TokenTwo(myNeighbor: Spin) extends Orientation //counter clockwise token
case class markerSend(myNeighbor: Spin) extends Orientation //marker to send to different spin orientations

class PingPong extends Actor {

  var map = scala.collection.mutable.HashMap.empty[ActorRef, mutable.Queue[Orientation]]

  var Token2Count=0
  var Token1Count=0
  var Token2MarkerCount=0
  var Token1MarkerCount=0
  var markerFlagLeft = false
  var markerFlagRight = false
  var markerCounter = 0

  def receive = {
    case TokenTwo(myNeighbors)=>
      Token2Count=Token2Count+1
      println(TokenTwo.toString + " received by " + self.path.name + " process, from process " + context.sender.path.name +  ", token2 counter is: " + Token2Count)
      Thread.sleep(500)
      myNeighbors.rs ! TokenTwo(Spin(self,sender))
      Thread.sleep(300)
      myNeighbors.rs ! markerSend(Spin(self, sender))


    case TokenOne(myNeighbors) =>
      Token1Count=Token1Count+1
      println(TokenOne.toString + " received by " + self.path.name + " process, from process " + context.sender.path.name +  ", token1 counter is: " + Token1Count)
      Thread.sleep(500)
      myNeighbors.ls ! TokenOne(Spin(sender,self))
      Thread.sleep(300)
      myNeighbors.ls ! markerSend(Spin(sender, self))

    case markerSend(myNeighbors) =>
      println("Token Counts for 1 and 2 respectively at "+self.path.name +" is "+ Token1Count+ " and " + Token2Count)
      println(context.sender.path.name + " performing markerSend to " + self.path.name )
      markerCounter = markerCounter+1

      if (sender().equals(myNeighbors.ls)){
        Token2MarkerCount = Token2MarkerCount + 1
        println("Token 2 Marker Counter from " + sender().path.name + " to " + self.path.name + " is " + Token2MarkerCount)
        map +=  (myNeighbors.ls -> mutable.Queue(markerSend(Spin(myNeighbors.rs, self))))
        //map += (myNeighbor.ls -> mutable.Queue(markerSend(Spin(self, myNeighbor.rs))))
        println("Token 2 Queue entry " + map.values)

      }

      if (sender().equals(myNeighbors.rs)){
        Token1MarkerCount = Token1MarkerCount + 1
        println("Token 1 Marker Counter from " + sender().path.name + " to " + self.path.name + " is " + Token1MarkerCount)
        map +=  (myNeighbors.rs -> mutable.Queue(markerSend(Spin(self, myNeighbors.ls))))
        println("Token 1 Queue entry " + map.values)
        //markerFlagLeft = true
        //println("1 clockwise flags "+markerFlagLeft+ " and "+ markerFlagRight)

      }

      else(sender.equals(self.path.parent))
      //println(map.values)

    case Start(la, ra) =>
      la ! TokenOne(Spin(ra,self))
      println("Token 1 going Clockwise")
      ra ! TokenTwo(Spin(self,la))
      println("Token 2 going Counter Clockwise")

      Thread.sleep(3000)

      la ! markerSend(Spin(ra,self))
      ra ! markerSend(Spin(self,la))

      println("SnapShot Initiating")
  }
}

object Server extends App {
  val system = ActorSystem("PingPong")
  val first = system.actorOf(Props[PingPong], name = "first")
  val second = system.actorOf(Props[PingPong], name = "second")
  val third = system.actorOf(Props[PingPong], name = "third")
  println("Server ready")

  first ! Start(second, third)
}
