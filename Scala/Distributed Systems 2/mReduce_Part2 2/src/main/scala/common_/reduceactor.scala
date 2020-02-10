package common_

import akka.actor.Actor
import com.typesafe.config.ConfigFactory
import scala.collection.mutable
import scala.collection.mutable.HashMap

class ReduceActor extends Actor {
  //println(self.path)
  var remainingMappers = ConfigFactory.load.getInt("number-mappers")
  var reduceMapTitle = HashMap[String, mutable.Set[String]]()
  var occurenceMap = HashMap[String, Int]()
  var linkMap = HashMap[String, Int]()
  var practiceMap = HashMap[String, mutable.Set[String]]()

  def receive = {

    //added plugin to reduce mappers of word instances to word frequency counts
    case WordCount(word, count) =>
      if (occurenceMap.contains(word)){
        occurenceMap += (word -> (occurenceMap(word) + 1))
      }
      else
        occurenceMap += (word -> 1)

      //added plugin to reduce mappers of word-to-title instances to word-to-title_sets
      //can be adapted to reduce mappers of word-to-url instances to word-to-url_sets
    case Word(word, pageTitle) =>
      if(reduceMapTitle.contains(word)) {
        if (!reduceMapTitle(word).contains(pageTitle))
          reduceMapTitle += (word -> (reduceMapTitle(word) += pageTitle))
          }
      else
        reduceMapTitle += (word -> (scala.collection.mutable.Set.empty[String] += pageTitle))

      //added plugin to reduce mappers of link-to-url_set pair instances to url_set-to-url_set_without_link_as_key
    case incomingHyperLinks(link, url) => {
      if (practiceMap.contains(link) && !practiceMap(link).mkString.contains(link)){
        print("hey")
        practiceMap += (link -> (practiceMap(link) += url))
        linkMap += (link -> (practiceMap(link) += url).size)
      }
      else
        println("ho")
        practiceMap += (link -> (scala.collection.mutable.Set.empty[String] += url))
        linkMap += (link -> (practiceMap(link) += url).size)

      //print(linkMap)
    }

    case Flush =>
      remainingMappers -= 1
      if (remainingMappers == 0) {
       // println(self.path.name + " Word Occurrence Map : " + occurenceMap + "\n")  //prints occurrences of each word in a webcrawl and/or set of files
       // println(self.path + " Practice Map : " + practiceMap)   //prints a URL as key and the set of incoming hyperlinks to that url as the value
       // println(self.path.name + " Incoming HyperLink Count Map : " + linkMap + "\n")  //prints a URL as key and the number of incoming hyperlinks to that url as the value
       // println(self.path.name + " Capitalized Word -> Title Map : " + reduceMapTitle + "\n")    //prints words mapped to the set of URL's containing that word
        context.actorSelection(("../..")) ! Done
      }
  }
}

