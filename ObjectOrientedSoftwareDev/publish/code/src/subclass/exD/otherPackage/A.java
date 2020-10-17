package subclass.exD.otherPackage;

public class A {
  void p() {System.out.println("A.p");}
  public void m() {
    System.out.println("A.m");
    this.p(); // which p?
  }
}
