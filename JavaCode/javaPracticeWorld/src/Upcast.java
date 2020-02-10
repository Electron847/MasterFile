public class Upcast {

    static class A           { int x; }
    static class B extends A { int y; }

    void f (B p) {
        A q = p;
    }
}
