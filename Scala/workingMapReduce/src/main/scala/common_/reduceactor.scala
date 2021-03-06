package common_

import akka.actor.Actor
import com.typesafe.config.ConfigFactory

import scala.collection.mutable
import scala.collection.mutable.HashMap

class ReduceActor extends Actor {
  println(self.path)
  var remainingMappers = ConfigFactory.load.getInt("number-mappers")
  var reduceMapTitle = HashMap[String, mutable.Set[String]]()
  //var reduceMapCount = HashMap[String,Int]()


  def receive = {
    case Word(word,title) =>

      if(reduceMapTitle.contains(word)){
        if (!reduceMapTitle(word).contains(title))
          //word -> (reduceMapCount(word) += 1)
          reduceMapTitle += (word -> (reduceMapTitle(word) += title))
      }

      else
        //reduceMapCount += (word -> 1)
        reduceMapTitle += (word -> (scala.collection.mutable.Set.empty[String] += title))

    case Flush =>
      remainingMappers -= 1
      if (remainingMappers == 0) {
        println(self.path.toStringWithoutAddress + " : " + reduceMapTitle)
        //println(self.path.toStringWithoutAddress + " : " + reduceMapCount)
        context.actorSelection(("../..")) ! Done
      }
  }
}