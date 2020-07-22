package lVals;

import java.util.ArrayList;

// no, temporary integer
public class LValue7 {
    static void f (int x) {
        ArrayList<Integer> list = new ArrayList<> ();
        list.add (5);
        list.add (6);
        list.add (7);
        list.add (8);
        System.out.println(list.get(x));
    }
    public static void main (String[] args) {
        f(1);
    }
}
