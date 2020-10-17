package basics.functions;
public class Main {
  private Main() {}
  static public void main (final String[] args) {
    Fun.f();
    Fun.g(2);
  }
}
class Fun {
  private Fun() {}
  static void f() { System.out.println("Fun.f()"); }
  static void g(final int x) {
    System.out.println("Fun.g()");
    if (x>0) {
      final int y = x-1;
      Fun.g(y);
    }
  }
}
