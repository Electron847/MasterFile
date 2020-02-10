package client



import akka.actor.{ActorSystem, Props}
import com.typesafe.config.ConfigFactory
import common_.{Flush, Simple_Content}

object MapReduceClient extends App {

  println("Press 'Enter' to run a default test: ")
  var str: String = scala.io.StdIn.readLine()

  val system = ActorSystem("MapReduceClient", ConfigFactory.load.getConfig("client"))
  val master = system.actorOf(Props[clientMasterActor], name = "master")

  //master ! Simple_Content("https://stackoverflow.com/")
  master ! Simple_Content("http://maddox.xmission.com/")
  //master ! Simple_Content("https://leafly.com/indica/blackberry-kush")
  //master ! Simple_Content("https://www.youtube.com")
  //master ! justContent("http://budzu.com/prices")   //trolls website for states links
 // master ! onePageContent("http://budzu.com/prices/usa/california")
  master ! Flush
}
