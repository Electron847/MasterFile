import akka.actor.{Actor, ActorRef, Props}

sealed trait KVStoreAPI
case class Put(key: BigInt, value: Any) extends KVStoreAPI
case class Get(key: BigInt) extends KVStoreAPI
//case class groupMembershipList(groupID: BigInt) extends KVStoreAPI

/**
 * KVStore is a local key-value store based on actors.  Each store actor controls a portion of
 * the key space and maintains a hash of values for the keys in its portion.  The keys are 128 bits
 * (BigInt), and the values are of type Any.
 */

class KVStore extends Actor {
  private val store = new scala.collection.mutable.HashMap[BigInt, Any]
  //val groupID = BigInt(Math.abs(self.hashCode()))



  //println("printing in KVStore to see what store is outside receive : " + store.keySet)


  override def receive = {

    case Put(key, cell) =>
      var actor = ActorRef
      sender ! store.put(key,cell)
      //println(actor + " printing in KVStore to see what store is inside receive : " + store.keys)


    //println(self.path.name + " has keyID " + key + " with cell value " + cell.hashCode() + " in store " + this.store.keys)
      //println(self.path.name + " has size " + this.store.size + " and groupID " + groupID + " storing keySet " + this.store.keySet)
    case Get(key) =>
      sender ! store.get(key)
  }

  //println("printing in KVStore to see what store is outside receive : " + store.keySet)

}

object KVStore {
  def props(): Props = {
     Props(classOf[KVStore])
  }
}
