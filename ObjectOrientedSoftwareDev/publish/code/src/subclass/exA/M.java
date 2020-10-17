package subclass.exA;
class M {
  public static void main(String[] args) {
    B o = new B();
    System.out.println("o.x=" + o.x);
    o.m();
  }
}
class A {
  public int x = 0;
  public void m() {
    System.out.println("In A::m() x=" + this.x);
    this.p();
  }
  public void p() {
    System.out.println("In A::p() x=" + this.x);
  }
}
class B extends A {
  public int x = 42;
  public void p() {
    System.out.println("In B::p() x=" + this.x);
  }
}
