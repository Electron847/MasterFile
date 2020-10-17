package subclass.exB;

public class M {
  public static void main(String[] argv) {
    (new B()).m(); // does this cause an infinite loop?
  }
}
class A {
  void m() {System.out.println("A.m");}
}
class B extends A {
  void m() {
    System.out.println("B.m");
    super.m(); 
  }
}
