package basics.classLoading;
public class Main {
  private Main() {}
  // The following is a static initializer.
  // It will execute the first time a class is used.
  static { System.out.println("Main initialized"); }
  static public void main (String[] args) {
    System.out.print("1. ");  C.f();
    System.out.print("2. ");  C.f();
    System.out.print("3. ");  D.g();
    System.out.print("4. ");  D.g();
  }
}
class C {
  private C() {}
  static { System.out.print("C initialized; "); }
  static void f() { System.out.println("C.f()"); }
}
class D {
  private D() {}
  static { System.out.print("D initialized; "); }
  static void g() { System.out.println("D.g()"); }
}
