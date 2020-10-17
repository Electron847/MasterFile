package subclass.ex4;

public class M {
  public static void main(String[] argv) {
    (new B()).m();
  }
}
class A {
  private void p() {System.out.println("A.p");}
  void m() {
    System.out.println("A.m");
    this.p(); // which p?
  }
}
class B extends A {
  private void p() {System.out.println("B.p");}
}
