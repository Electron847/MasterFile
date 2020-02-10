/*


package mapreduce

import akka.actor.Actor
import com.typesafe.config.ConfigFactory

import scala.collection.mutable.HashMap

class ReduceActor extends Actor {
  var remainingMappers = ConfigFactory.load.getInt("number-mappers")
  var reduceMap = HashMap[String, Int]()

  def receive = {
    case Word(word, count) =>
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
*/

import akka.actor.Actor
import com.typesafe.config.ConfigFactory

import scala.collection.mutable
import scala.collection.mutable.HashMap

class ReduceActor extends Actor {
  var remainingMappers = ConfigFactory.load.getInt("number-mappers")
  var reduceMap = HashMap[String, Int]()
  var reduceMapTitle = HashMap[String, mutable.Set[String]]()
  val books = Set("A Tale of Two Cities", "A Christmas Carol", "Great Expecations", "The Pickwick Papers")


  def receive = {
    //println(reduceMap)
    //case (titleContent, Word(word, title)) =>

    case (Word(word, title)) =>
      if(reduceMapTitle.contains(word)){
        reduceMapTitle += (word -> (reduceMapTitle(word) += title))
        //reduceMap += (word -> (reduceMap(word) + " " + (title)))
        //reduceMap += (word -> (reduceMap(word) + " " + titleList))    //adds the titles related to each word HOWEVER it adds first instance as a String
      }

      else
        reduceMapTitle += (word -> (scala.collection.mutable.Set.empty[String] += title))
        //reduceMap += (word -> title)

    case Flush =>
      remainingMappers -= 1
      if (remainingMappers == 0) {
        println(self.path.toStringWithoutAddress + " : " + reduceMapTitle)
        context.parent ! Done
      }
  }
}

