

package mapreduce

import java.util

import com.typesafe.config.ConfigFactory

class MasterActor extends Actor {

  val numberMappers  = ConfigFactory.load.getInt("number-mappers")
  val numberReducers  = ConfigFactory.load.getInt("number-reducers")
  var pending = numberReducers

  var reduceActors = util.List[ActorRef]()
  for (i <- 0 until numberReducers)
    reduceActors = context.actorOf(Props[ReduceActor], name = "reduce"+i)::reduceActors

  val mapActors = context.actorOf(RoundRobinPool(numberMappers).props(Props(classOf[MapActor], reduceActors)))

  def receive = {
    case (title: String, url: String) =>
      mapActors ! (title, url)
    case Flush =>
      mapActors ! Broadcast(Flush)    //sends Flush to all mapActors
    case Done =>
      pending -= 1
      if (pending == 0)
        context.system.terminate
  }
}



