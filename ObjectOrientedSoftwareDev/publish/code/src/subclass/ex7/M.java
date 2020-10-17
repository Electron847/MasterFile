package subclass.ex7;

public class M {
  public static void main(String[] argv) {
    new B(1);
    new C();
  }
}
class A {
  A(int i) {System.out.println("A(int)");}
}
class B extends A {
  B(int i) {
    super(i);
    System.out.println("B(int)");
  }
}
class C extends B {
  // The following will not compile!
//  C() {System.out.println("C");}
  C() {
    super(0);
    System.out.println("C");
  }
}
