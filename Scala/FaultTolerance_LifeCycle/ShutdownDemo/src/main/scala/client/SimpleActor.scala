package client

import akka.actor.Actor
import akka.event.Logging

case class Message(msg: String)

class SimpleActor extends Actor {
  val log = Logging(context.system, this)

  def receive = {
    case Message(msg) => log.info("Got a valid message: %s".format(msg))
    case default => log.error("Got a message I don't understand.")

  }
}