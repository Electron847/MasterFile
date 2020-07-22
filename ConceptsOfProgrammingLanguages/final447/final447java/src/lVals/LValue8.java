package lVals;

import java.util.ArrayList;

class C {
    int x;
    int y;
    C (int x, int y) {
        this.x = x;
        this.y = y;
    }
}

// Yes, all object access via pointers/ references in Java

public class LValue8 {
    static void f (int x) {
        ArrayList<C> list = new ArrayList<> ();
        list.add (new C (5, 5));
        list.add (new C (6, 6));
        list.add (new C (7, 7));
        list.add (new C (8, 8));
        System.out.println(list.get(x++).x);
    }
    public static void main (String[] args) {
        f(3);
    }
}