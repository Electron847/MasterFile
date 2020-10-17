package horstmann.ch09_queue2;
import java.util.concurrent.locks.*;
import java.util.ArrayList;

/** 
    A first-in, first-out bounded collection of objects. 
*/ 
public class BoundedQueue<E>
{ 
   /** 
       Constructs an empty queue. 
       @param capacity the maximum capacity of the queue 
   */ 
   public BoundedQueue(int capacity) 
   { 
      elements = new ArrayList<E>(capacity); 
      head = 0; 
      tail = 0; 
      size = 0;
   } 

   /** 
       Removes the object at the head. 
       @return the object that has been removed from the queue
   */ 
   public E remove() throws InterruptedException
   { 
      queueLock.lock();
      try
      {         
         while (size == 0) 
            valueAvailableCondition.await();
         E r = elements.get(head); 
         head++;
         size--;
         if (head == elements.size()) 
            head = 0; 
         spaceAvailableCondition.signalAll();
         return r; 
      }
      finally
      {
         queueLock.unlock();
      }
   } 

   /** 
       Appends an object at the tail. 
       @param newValue the object to be appended 
   */ 
   public void add(E newValue) throws InterruptedException
   { 
      queueLock.lock();
      try
      {         
         while (size == elements.size()) 
            spaceAvailableCondition.await();
         elements.set(tail,newValue); 
         tail++;
         size++;
         if (tail == elements.size()) 
            tail = 0; 
         valueAvailableCondition.signalAll();
      }
      finally
      {
         queueLock.unlock();
      }
   } 

   private ArrayList<E> elements; 
   private int head; 
   private int tail; 
   private int size;

   private Lock queueLock = new ReentrantLock();
   private Condition spaceAvailableCondition 
         = queueLock.newCondition();
   private Condition valueAvailableCondition 
         = queueLock.newCondition();
}
