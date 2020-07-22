package OOP;

interface J {
    int f (int x);
    int g (int x);
}

class K implements J {
    J back = this;

    public int f (int x) {
        System.out.format ("K.f (%d)%n", x);
        if (x == 0) {
            return back.g (x - 1);
        } else {
            this.g(x-1);   //addition
            return back.f (x - 1);
        }
    }

    public int g (int x) {
        System.out.format ("K.g (%d)%n", x);
        return 0;
    }
}

class L implements J {
    K k;

    L () {
        k = new K ();
        k.back = this;
    }

    public int f (int x) {
        System.out.format ("L.f (%d)%n", x);
        return k.f (x);
    }

    public int g (int x) {
        System.out.println ("L.g ()");
        return 0;
    }
}

public class Inheritance06 {
    public static void main (String[] args) {
        new L ().f (3);
    }
}
