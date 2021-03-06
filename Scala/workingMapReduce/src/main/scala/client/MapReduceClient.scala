package client

import akka.actor.{ActorSystem, Props}
import com.typesafe.config.ConfigFactory
import common_.{Book, Flush}

object MapReduceClient extends App {

  val system = ActorSystem("MapReduceClient", ConfigFactory.load.getConfig("client"))
  val master = system.actorOf(Props[clientMasterActor], name = "master")


  master ! Book("Tale of Two Cities","http://www.gutenberg.org/files/98/98-0.txt" )
  master ! Book("A Christmas Carol", "http://www.gutenberg.org/files/46/46-0.txt")
  master ! Book("Great Expectations", "http://www.gutenberg.org/files/1400/1400-0.txt")
  master ! Book("The Pickwick Papers", "http://reed.cs.depaul.edu/lperkovic/csc536/homeworks/gutenberg/pg580.txt")
  master ! Book("Life And Adventures Of Martin Chuzzlewit", "http://reed.cs.depaul.edu/lperkovic/csc536/homeworks/gutenberg/pg968.txt")
  master ! Book("Hunted Down", "http://reed.cs.depaul.edu/lperkovic/csc536/homeworks/gutenberg/pg807.txt")
  master ! Book("The Cricket on the Hearth", "http://reed.cs.depaul.edu/lperkovic/csc536/homeworks/gutenberg/pg20795.txt")
  master ! Book("Bleak House", "http://reed.cs.depaul.edu/lperkovic/csc536/homeworks/gutenberg/pg1023.txt")
  master ! Book("Our Mutual Friend", "http://reed.cs.depaul.edu/lperkovic/csc536/homeworks/gutenberg/pg883.txt")
  master ! Book("Dombey and Son", "http://reed.cs.depaul.edu/lperkovic/csc536/homeworks/gutenberg/pg821.txt")
  master ! Book("Oliver Twist", "http://reed.cs.depaul.edu/lperkovic/csc536/homeworks/gutenberg/pg730.txt")
  master ! Book("The Old Curiosity Shop", "http://reed.cs.depaul.edu/lperkovic/csc536/homeworks/gutenberg/pg700.txt")
  master ! Book("David Copperfield", "http://reed.cs.depaul.edu/lperkovic/csc536/homeworks/gutenberg/pg766.txt")
  master ! Book("The Life And Adventures Of Nicholas Nickleby", "http://reed.cs.depaul.edu/lperkovic/csc536/homeworks/gutenberg/pg967.txt")
  master ! Book("A Child's History of England", "http://reed.cs.depaul.edu/lperkovic/csc536/homeworks/gutenberg/pg699.txt")
  master ! Book("Little Dorrit", "http://reed.cs.depaul.edu/lperkovic/csc536/homeworks/gutenberg/pg963.txt")

  master ! Flush
}