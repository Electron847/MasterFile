import com.sun.tools.javac.util.ListBuffer;
import sun.tools.tree.Node;
import java.util.*;


public class Main {

    static class A           { int x; }
    static class B extends A { int y; }
    static class C extends B { String z; }

 /*   static B convert (A p) {
        B result = (B) p;
        return result;
    }
*/

    static void f (B p) {
       // p.y = 77;
        A q = p;
        q.x = 15;
        System.out.println("Hello it's " + q.x + " and " + ((B) q).y);
       // System.out.println("Hello it's " + q.x + " and " + ((B) q).y); //can't reach the .z C extension variable

    }

    static A g (B p) {
        A q2 = p;
        System.out.println(q2.x);
        System.out.println(((B) q2).y);
        return q2;
    }

    static A h (C p) {
        A q = p;
        System.out.println("Now this time it's " + q.x + " and " + ((C) q).y + " also " + ((C) q).z);
        return q;
    }




//
//    static class Node
//    {
//        int item;
//        Node next;
//
//        public Node (int item, Node next)
//        {
//            this.item = item;
//            this.next = next;
//        }
//    }
//
//    static int sumWhile (Node Anything)
//    {
//        int result = 0;
//        while (Anything != null)
//        {
//            result += Anything.item;
//            Anything = Anything.next;
//        }
//        return result;
//    }

    public static void main(String[] args) {

        A myA = new A();
        B r = new B();
        r.y = 100;
        f(r);
        g(r);

        C myC = new C();
        myC.z = "Dopalicious";
        f(myC);
        h(myC);


      /*  ArrayList listOfNodes = new ArrayList();
        Node Anything3 = null;
        listOfNodes.add(Anything3);
        Node Anything2 = new Node(7, Anything3);
        listOfNodes.add(Anything2);
        Node Anything1 = new Node(6, Anything2);
        listOfNodes.add(Anything1);
        Node Anything = new Node(5, Anything1);
        listOfNodes.add(Anything);


        System.out.println(sumWhile(Anything));

        ListBuffer newList = new ListBuffer();
        newList.add(5);
        newList.add(10);
        newList.add(22);
        newList.add(null);

        System.out.println(newList.element());

        Node deadNode = null;
        for (int j = 0; j <= newList.length(); j++)
        {
            Node nextNode = new Node(((Integer) newList.next()), deadNode);
            Node myNode = new Node(((Integer) newList.element()), nextNode);
            System.out.println(myNode.item);

        }*/






       // Node newNode = null;
        //for (int i = newList.size() - 1; i >= 0; i--)
      //  {
         //   newNode = new Node(((Integer) newList.element()), ((Node) newList.next()));
         //   System.out.println(newNode.item);
      //  }
        System.out.println("Hello World!");
    }

}




