import akka.actor.Actor
import com.typesafe.config.ConfigFactory
import mapreduce.{Flush, Word}

import scala.collection.mutable

/*

package mapreduce

import akka.actor.Actor
import com.typesafe.config.ConfigFactory

import scala.collection.mutable
import scala.collection.mutable.HashMap

class ReduceActor extends Actor {
  var remainingMappers = ConfigFactory.load.getInt("number-mappers")
  var reduceMap = HashMap[String, Int]()

  //var reduceMap = HashMap[String, String]()
  var reduceMapTitle = HashMap[String, mutable.Set[String]]()
  val books = Set("A Tale of Two Cities", "A Christmas Carol", "Great Expecations", "The Pickwick Papers")


  def receive = {
    //println(reduceMap)
    case (titleContent, Word(word, title)) =>

    //case (Word(word, title)) =>
      if(reduceMapTitle.contains(word)){
      //if (reduceMap.contains(word)) {
        //println(word, title) //appears to print every word and it's title association as they are received by the case and handled at the if block
        //println("the word "+ word + " has count " + count)
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
        //println(self.path.toStringWithoutAddress + " : " + reduceMap)   //prints each reducers map results
        context.parent ! Done
      }
  }
}

*/


class ReduceActor extends Actor {
  println(self.path)

  Thread sleep 2000

  var remainingMappers = ConfigFactory.load.getInt("number-mappers")
  var reduceMap = mutable.HashMap[String, List[String]]()

  def receive = {
    case Word(word, title) ->
      if(reduceMap.contains(word)) {
        if (!reduceMap(word).contains(title))
          reduceMap += (word -> (title :: reduceMap(word)))
      }
      else
    reduceMap += (word -> List[title])
    case Flush ->
      remainingMappers -- 1
  }
}