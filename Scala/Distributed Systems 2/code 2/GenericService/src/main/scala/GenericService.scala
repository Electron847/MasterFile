import akka.actor.{Actor, ActorRef, Props}
import akka.event.Logging

import scala.collection.mutable
import scala.collection.mutable.ListBuffer

class GenericCell(var prev: BigInt, var next: BigInt)
class GenericMap extends scala.collection.mutable.HashMap[BigInt, GenericCell]
case class Message(msg: String)
case object Test

/**
 * GenericService is an example app service for the actor-based KVStore/KVClient.
 * This one stores Generic Cell objects in the KVStore.  Each app server allocates new
 * GenericCells (allocCell), writes them, and reads them randomly with consistency
 * checking (touchCell).  The allocCell and touchCell commands use direct reads
 * and writes to bypass the client cache.  Keeps a running set of Stats for each burst.
 *
 * @param myNodeID sequence number of this actor/server in the app tier
 * @param numNodes total number of servers in the app tier
 * @param storeServers the ActorRefs of the KVStore servers
 * @param burstSize number of commands per burst
 */

class GenericServer (val myNodeID: Int, val numNodes: Int, storeServers: Seq[ActorRef], burstSize: Int) extends Actor {
  val generator = new scala.util.Random
  val cellstore = new KVClient(storeServers)
  val dirtycells = new AnyMap
  val localWeight: Int = 70
  val log = Logging(context.system, this)

  var stats = new Stats
  var allocated: Int = 0
  var endpoints: Option[Seq[ActorRef]] = None

  val GroupID = BigInt(Math.abs(self.hashCode()))
  val GroupList = new ListBuffer[ActorRef]()       //list
  val listOfGroupIDs = new ListBuffer[BigInt]()          //list
  val messageQueue = new mutable.HashMap[BigInt, mutable.Queue[String]] ()

  listOfGroupIDs.append(GroupID)

  cellstore.directWrite(GroupID, GroupList)
  cellstore.directWrite(GroupID, listOfGroupIDs)

  val listOfActorRefs = new ListBuffer[ActorRef]()    //list


  for (stores <- storeServers.toList) {
    if(!listOfActorRefs.contains(stores)){
      listOfActorRefs.append(stores)
    } else{
      println("already in this list")
    }
  }

  cellstore.directWrite(GroupID, GroupList)

  def receive() = {
      case Prime() =>
        joinGroup()
      case Command() =>
        statistics(sender)
        command
      case View(e) =>
        endpoints = Some(e)
      case Message(msg) =>
        //stats.multiCastReceived+=1
        //println(msg + " from " + sender().path.name)
      case Test =>
        println("Test Received")
        stats.multiCastReceived+=1
  }

  private def command() = {
    val sample = generator.nextInt(100)
    if (sample <= 75 && sample >= 25) {
      joinNewGroup()
      //allocCell
    } else if (sample > 75) {
      leavesGroup()
      //touchCell
    }
    else if (sample < 25){
      multiCastToGroup()
    }
  }

  private def statistics(master: ActorRef) = {
    stats.messages += 1
    if (stats.messages >= burstSize) {
      master ! BurstAck(myNodeID, stats)
      stats = new Stats
    }
  }


  private def joinGroup() = {

    var randomID: Int = generator.nextInt(10)
    GroupList.append(listOfActorRefs(randomID))
    cellstore.directWrite(GroupID,GroupList)

  }

  private def joinNewGroup(): Unit = {
    stats.joins += 1

    var randomID: Int = generator.nextInt(10)

    if (!GroupList.contains(listOfActorRefs(randomID))) {

      GroupList.append(listOfActorRefs(randomID))
      cellstore.directWrite(GroupID,GroupList)
    }

    //println("Server " + myNodeID + " has the group ID " + GroupID + " with members " + cellstore.directRead(GroupID))

  }

  private def leavesGroup(): Unit = {
    stats.leaves += 1

    //println(self.path.name + " GroupList before leavesGroup if " + cellstore.directRead(GroupID))

    try {
      if (GroupList.contains(listOfActorRefs(myNodeID)))
      {
        GroupList.remove(myNodeID)
        cellstore.directWrite(GroupID,GroupList)
      }
    } catch{case e: Exception => stats.misses += 1}

  }


  private def multiCastToGroup(): Unit = {

    stats.multicasted += 1


    var tempMembershipMultiCastList = cellstore.directRead(GroupID).get.asInstanceOf[ListBuffer[ActorRef]]

    println(tempMembershipMultiCastList)
    println(self)

    if(tempMembershipMultiCastList.contains(self)){
      println("working")
    }

    for (member <- tempMembershipMultiCastList) {
      //println(tempMembershipMultiCastList.size)
      //println(member.path.name + " to " + self.path.name)
      //member ! Message("What's up Group from : " + member.path.name + " : at destination = ")
      member ! Test
    }

    /* var randomID: Int = generator.nextInt(10)

     directWrite(GroupID, cellstore.store(randomID) ! Message(" from " + self.path.name))

     //listOfActorRefs(myNodeID) ! Message(self.path.name)

     //self ! Message(listOfActorRefs(myNodeID))
 */
  }

/*
  private def allocCell() = {


    //println("This allocCell is from " + self.path.name + " with " + cellstore + " at " + r.hashCode())
    val key = chooseEmptyCell
    var cell = directRead(key)
    assert(cell.isEmpty)
    val r = new GenericCell(0, 1)
    stats.allocated += 1
    directWrite(key, r)
    //println("This allocCell is from " + self.path.name + " with context " + this.context + " with key " + key + " at " + r.hashCode())

  }*/

/*
  private def chooseEmptyCell(): BigInt =
  {
    allocated = allocated + 1
    cellstore.hashForKey(myNodeID, allocated)
  }
*/

 /* private def touchCell() = {
    stats.touches += 1
    val key = chooseActiveCell
    val cell = directRead(key)
    if (cell.isEmpty) {
      stats.misses += 1
      //add hashkey, value pair to sender.groupID[ActorRef]
    } else {
      val r = cell.get
      if (r.next != r.prev + 1) {
        stats.errors += 1
        r.prev = 0
        r.next = 1
      } else {
        r.next += 1
        r.prev += 1
      }
      directWrite(key, r)
    }
  }*/

 /* private def chooseActiveCell(): BigInt = {
    val chosenNodeID =
      if (generator.nextInt(100) <= localWeight)
        myNodeID
        //println("In genServ chooseActiveCell else " + chosenNodeID)
      else
        generator.nextInt(numNodes - 1)
        //println("In genServ chooseActiveCell else " + chosenNodeID)

    val cellSeq = generator.nextInt(allocated)
    cellstore.hashForKey(chosenNodeID, cellSeq)
  }
*/
/*
  private def rwcheck(key: BigInt, value: GenericCell) = {
    directWrite(key, value)
    val returned = read(key)
    if (returned.isEmpty)
      println("rwcheck failed: empty read")
    else if (returned.get.next != value.next)
      println("rwcheck failed: next match")
    else if (returned.get.prev != value.prev)
      println("rwcheck failed: prev match")
    else
      println("rwcheck succeeded")
  }

  private def read(key: BigInt): Option[GenericCell] = {
    val result = cellstore.read(key)
    if (result.isEmpty) None else
      Some(result.get.asInstanceOf[GenericCell])
  }

  private def write(key: BigInt, value: GenericCell, dirtyset: AnyMap): Option[GenericCell] = {
    val coercedMap: AnyMap = dirtyset.asInstanceOf[AnyMap]
    val result = cellstore.write(key, value, coercedMap)
    if (result.isEmpty) None else
      Some(result.get.asInstanceOf[GenericCell])
  }
*/

  private def directRead(key: BigInt): Any = {
    val result = cellstore.directRead(key)
    if (result.isEmpty) None else
      Some(result.get.asInstanceOf[GenericCell])
  }

  private def directWrite(key: BigInt, value: Any): Any = {
    val result = cellstore.directWrite(key, value)
    if (result.isEmpty) None else
      Some(result.get.asInstanceOf[GenericCell])
  }

/*
  private def push(dirtyset: AnyMap) = {
    cellstore.push(dirtyset)
  }
 */
}

object GenericServer {
  def props(myNodeID: Int, numNodes: Int, storeServers: Seq[ActorRef], burstSize: Int): Props = {
    Props(classOf[GenericServer], myNodeID, numNodes, storeServers, burstSize)
  }

}

