package basics.inner.three;
public class Main {
  private Main() {}
  static public void main (final String[] args) {
    C mc1 = new C(42);  
    C mc2 = new C(36);
    mc1.f();
    mc2.f();
  }
}

interface Print { void print(); }

class C {
  int cx;
  C(int x) { cx = x; }
  class P implements Print {
    int py = 27;
    public void print() {
      System.out.println(" cx=" + Integer.toString(C.this.cx) +
                         " py=" + Integer.toString(this.py));
    }
  }
  void f() {
    Print p = new P();
    p.print();
  }
}
