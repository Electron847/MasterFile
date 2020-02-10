
package common

import akka.actor.Actor
import com.typesafe.config.ConfigFactory

import scala.collection.mutable
import scala.collection.mutable.HashMap

class ReduceActor extends Actor {

  println(self.path)

  Thread sleep 2000

  var remainingMappers = ConfigFactory.load.getInt("number-mappers")

  var reduceMapTitle = HashMap[String, mutable.Set[String]]()

  //var reduceMapTitle = HashMap[String, List[String]]()


  def receive = {
    case Word(word, title) =>
      if (reduceMapTitle.contains(word)) {
        if(!reduceMapTitle(word).contains(title))
         // reduceMapTitle += (word -> (title :: reduceMapTitle(word)))
          reduceMapTitle += (word -> (reduceMapTitle(word) += title))
          //println("\n" + reduceMapTitle)
      }
      else
        //reduceMapTitle += (word -> List(title))
        reduceMapTitle += (word -> (scala.collection.mutable.Set.empty[String] += title))


    case Flush =>
      remainingMappers -= 1
      if (remainingMappers == 0) {

        println("\n")
        println(self.path + " processed " + reduceMapTitle.keys.size + " unique capitalized words \n" + reduceMapTitle)
        println("\n")

        context.actorSelection(("../..")) ! Done

      }
  }
}

