package HW2;

//import junit.*;
import junit.framework.Assert;
import junit.framework.TestCase;
//import org.junit.Test;

public class MyLinkedTest extends TestCase
{

    public void testDelete()
    {
        MyLinked list = new MyLinked();
        list.add(1);
        //System.out.println(list.first);
        assertTrue(!list.isEmpty());
        list.delete(0);
        assertTrue(list.isEmpty());
        int x = 0;
        while(x != 13)
        {
            list.add(x);
            x++;
        }
        list.delete(0);
        MyLinked.Node a = list.first;
        while(a.next != null)
        {
            assertTrue(a.item != 12);
            a = a.next;
        }
        list.delete(10);
        a = list.first;
        while(a.next != null)
        {
            assertTrue(a.item != 1);
            a = a.next;
        }
        list.delete(4);
        a = list.first;
        while (a.next != null)
        {
            assertTrue(a.item != 7);
            a = a.next;
        }
    }

    public void testReverse()
    {
        MyLinked list = new MyLinked();
        list.reverse();
        list.add(1);
        list.reverse();
        list.add(2);
        list.reverse();
        MyLinked.Node myNode = list.first;
        while(myNode.next != null)
        {
            assert(myNode.item <= myNode.next.item);
            myNode = myNode.next;
        }
        list.reverse();
        myNode = list.first;
        while(myNode.next != null)
        {
            assertTrue(myNode.item >= myNode.next.item);
            myNode = myNode.next;
        }
        for (double i = 3; i < 7; i++)
        {
            list.add(i);
            list.add(i);
        }

        myNode = list.first;
        while(myNode.next != null)
        {
            assertTrue(myNode.item >= myNode.next.item);
            myNode = myNode.next;
        }

    }

    public void testRemove()
    {
        MyLinked list = new MyLinked();
        list.remove(4);
        list.add(1);
        list.remove(4);
        MyLinked.Node myNode = list.first;
        while (myNode.next != null)
        {
            assertTrue(myNode.item != 4);
            myNode = myNode.next;
        }
        list.remove(1);
        for (double index = 1; index < 5; index++)
        {
            list.add(index);
            list.add(index);
        }
        for (double index = 1; index < 5; index++)
        {
            list.add(index);
            list.add(index);
            list.add(index);
            list.add(index);
            list.add(index);
        }
        list.remove(9);
        myNode = list.first;
        while(myNode.next != null)
        {
            assertTrue(myNode.item != 9);
            myNode = myNode.next;
        }
        list.remove(3);
        myNode = list.first;
        while(myNode.next != null)
        {
            assertTrue(myNode.item != 3);
            myNode = myNode.next;
        }
        list.remove(1);
        myNode = list.first;
        while(myNode.next != null)
        {
            assertTrue(myNode.item != 1);
            myNode = myNode.next;
        }
        list.remove(4);
        myNode = list.first;
        while(myNode.next != null)
        {
            assertTrue(myNode.item != 4);
            myNode = myNode.next;
        }
        list.remove(2);
        myNode = list.first;
        assertTrue(list.N == 0);

    }

}
