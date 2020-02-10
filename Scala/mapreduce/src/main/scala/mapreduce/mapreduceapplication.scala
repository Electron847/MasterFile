/*

package mapreduce

import akka.actor.{ActorSystem, Props}
import scala.io.Source



object MapReduceApplication extends App {

  val system = ActorSystem("MapReduceApp")
  val master = system.actorOf(Props[MasterActor], name = "master")

  val htmlTwoCities = Source.fromURL("http://www.gutenberg.org/files/98/98-0.txt")
  val twoCities = htmlTwoCities.mkString

  val htmlThePickwickPapers = Source.fromURL("http://www.gutenberg.org/files/580/580-0.txt")
  val ThePickwickPapers = htmlThePickwickPapers.mkString

  val htmlChristmasCarol = Source.fromURL("http://www.gutenberg.org/files/46/46-0.txt")
  val christmasCarol = htmlChristmasCarol.mkString

  val htmlGreatExpectations = Source.fromURL("http://www.gutenberg.org/files/1400/1400-0.txt")
  val greatExpectations = htmlGreatExpectations.mkString

  master ! ("Tale of Two Cities","http://www.gutenberg.org/files/98/98-0.txt" )
  master ! ("A Christmas Carol", "http://www.gutenberg.org/files/46/46-0.txt")
  master ! ("Great Expectations", "http://www.gutenberg.org/files/1400/1400-0.txt")
  master ! ("The Pickwick Papers", "http://www.gutenberg.org/files/580/580-0.txt")


  master ! Flush
}


*/
