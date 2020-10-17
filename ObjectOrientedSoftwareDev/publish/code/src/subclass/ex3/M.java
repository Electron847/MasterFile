package subclass.ex3;

public class M {
  public static void main(String[] argv) {
    (new C()).m();
  }
}
class A {
  void p() {System.out.println("A.p");}
}
class B extends A {
  void p() {System.out.println("B.p");}
  void m() {
    System.out.println("B.m");
    this.p();  // static or dynamic binding?
    super.p(); // static or dynamic binding?
  }
}
class C extends B {
  void p() {System.out.println("C.p");}
}
