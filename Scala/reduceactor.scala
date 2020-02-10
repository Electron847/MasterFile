package mapreduce

import akka.actor.Actor
import com.typesafe.config.ConfigFactory

import scala.collection.mutable.HashMap

class ReduceActor extends Actor {
  var remainingMappers = ConfigFactory.load.getInt("number-mappers")
  var reduceMap = HashMap[String, Int]()

  def receive = {
    case (titleContent,Word(word, count)) =>
      if (reduceMap.contains(word)) {
        reduceMap += (word -> (reduceMap(word) + 1))
      }
      else
        reduceMap += (word -> 1)
    case Flush =>
      remainingMappers -= 1
      if (remainingMappers == 0) {
        println(self.path.toStringWithoutAddress + " : " + reduceMap)
        context.parent ! Done
      }
  }
}
