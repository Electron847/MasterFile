package subclass.ex2;

public class M {
  public static void main(String[] argv) {
    (new B()).m();
  }
}
class A {
  static void p() {System.out.println("A.p");}
  void m() {
    System.out.println("A.m");
    this.p(); // static or dynamic binding?
  }
}
class B extends A {
  static void p() {System.out.println("B.p");}
}
