package horstmann.ch09_queue3;
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
   public synchronized E remove() 
         throws InterruptedException
   { 
      while (size == 0) wait();
      E r = elements.get(head); 
      head++;
      size--;
      if (head == elements.size()) 
         head = 0; 
      notifyAll();
      return r; 
   } 

   /** 
       Appends an object at the tail. 
       @param newValue the object to be appended 
   */ 
   public synchronized void add(E newValue) 
         throws InterruptedException
   { 
      while (size == elements.size()) wait();
      elements.set(tail,newValue); 
      tail++;
      size++;
      if (tail == elements.size()) 
         tail = 0; 
      notifyAll();
   } 

   private ArrayList<E> elements; 
   private int head; 
   private int tail; 
   private int size;
}
