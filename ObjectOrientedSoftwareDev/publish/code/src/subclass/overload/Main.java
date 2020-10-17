package subclass.overload;

class A {
  public void m(String s) { System.out.println("A"); }
}
class B extends A { 
  public void m(Object o) { System.out.println("B"); }
}

class Main {
  private Main() {}
  public static void main(String[] args) {
    String y = "?";
    Object x = y;
    B b = new B();
    A a = b;
    a.m(y);
    // a.m(x);
    b.m(y);
    b.m(x);
  }
}
