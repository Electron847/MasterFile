package subclass.exD;

public class M {
  public static void main(String[] argv) {
    (new B()).m();
  }
}
class B extends subclass.exD.otherPackage.A {
  public void p() {System.out.println("B.p");}
}
