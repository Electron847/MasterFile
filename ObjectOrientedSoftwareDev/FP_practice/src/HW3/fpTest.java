package HW3;

import org.junit.Test;

import static junit.framework.TestCase.assertTrue;

import java.util.LinkedList;
import java.util.List;

public class fpTest {

    @Test
    public void testMap() {
        LinkedList<Integer> l = new LinkedList<>();
        for (int i=0; i < 10; i++) l.addFirst(i+1);
        //List<Integer> u = fpPractice.map(l, (Integer x) -> {return x+1;});
    }


    @Test
    public void testFoldRight() {
        LinkedList<Integer> l = new LinkedList<>();
        for (int i=0; i < 10; i++) l.addFirst(i+1);
        assertTrue(55 ==
                fpPractice.foldR(0, l, (Integer x, Integer y) -> {return x+y;}));
    }

    @Test
    public void testFilter() {
        LinkedList<Integer> l = new LinkedList<>();
        for (int i=0; i < 10; i++) l.addFirst(i+1);

        Iterable<Integer>i = fpPractice.filter(l, (Integer x ) -> {return x%2 == 0;});
        int u = 0;
        for (Integer a: i) u++;
        assertTrue(u==5);
    }

    @Test
    public void testMinVal() {
        LinkedList<Integer> l = new LinkedList<>();
        for (int i=0; i < 10; i++) l.addFirst(i+1);

        assertTrue(1==fpPractice.minVal(l, (Integer x, Integer y) -> {return x-y;} ));
    }

    @Test
    public void testMinPos() {
        LinkedList<Integer> l = new LinkedList<>();
        for (int i=0; i < 10; i++) l.addFirst(i+1);

        assertTrue(9==fpPractice.minPos(l));
    }

}