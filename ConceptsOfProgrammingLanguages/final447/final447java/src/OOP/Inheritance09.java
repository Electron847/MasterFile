package OOP;

class E {
    int f (int x) {
        System.out.format ("E.f (%d)%n", x);
        return (x == 0) ? g () : f (x - 1);
    }

    int g () {
        System.out.println ("E.g ()");
        return 0;
    }

    void h () {
        System.out.println ("E.h ()");
    }
}


class F extends E {
    int f (int x) {
        System.out.format ("F.f (%d)%n", x);
        return this.f (x);
    }

    int g () {
        System.out.println ("F.g ()");
        E r = this;
        r.h ();
        return 0;
    }

    void h () {
        System.out.println ("F.h ()");
        super.h ();
    }
}


public class Inheritance09 {
    public static void main (String[] args) {
        E x = new F ();
        x.f (3);
    }
}