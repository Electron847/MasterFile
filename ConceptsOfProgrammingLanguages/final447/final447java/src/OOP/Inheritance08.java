package OOP;

class C {
    int f (int x) {
        System.out.format ("C.f (%d)%n", x);
        return (x == 0) ? g () : f (x - 1);
    }

    int g () {
        System.out.println ("C.g ()");
        return 0;
    }

    void h () {
        System.out.println ("C.h ()");
    }
}


class D extends C {
    int f (int x) {
        System.out.format ("D.f (%d)%n", x);
        return super.f (x);
    }

    int g () {
        System.out.println ("D.g ()");
        C r = this;
        r.h ();
        return 0;
    }

    void h () {
        System.out.println ("D.h ()");
        super.h ();
    }
}


public class Inheritance08 {
    public static void main (String[] args) {
        C x = new D ();
        x.f (3);
    }
}