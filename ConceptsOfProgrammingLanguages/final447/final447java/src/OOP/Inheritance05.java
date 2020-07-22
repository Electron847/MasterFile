package OOP;

class H {
    int f (int x) {
        System.out.format ("H.f (%d)%n", x);
        if (x == 0) {
            return g ();
        } else {
            return f (x - 1);
        }
    }

    int g () {
        System.out.println ("H.g ()");
        return 0;
    }
}

class I {
    H h = new H ();

    int f (int x) {
        System.out.format ("I.f (%d)%n", x);
        return h.f (x);
    }

    int g () {
        System.out.println ("I.g ()");
        return 0;
    }
}

public class Inheritance05 {
    public static void main (String[] args) {
        new I ().f (3);
    }
}