package subclass.ex9;

public class M {
  public static void main(String[] argv) {
    O o = new X();
    I i = o.getI();
    i.m();
  }
}
interface I { public void m(); }
class O {
  void p() {System.out.println("O.p");}
  I getI() {
    return new I() {
      public void m() {
        O.this.p(); // static or dynamic binding?
      }};
  }
}
class X extends O {
  void p() {System.out.println("X.p");}
}
