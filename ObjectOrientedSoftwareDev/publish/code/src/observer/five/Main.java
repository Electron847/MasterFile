package observer.five;
public class Main {
  public static void main(String[] argv) {
    IntObserver o = new IntObserver();
    Int c = new Int(o);
    Runnable r1 = new M(c);
    Runnable r2 = new N(c);
    for (int i=0; i<10000; i++) {
      r1.run();
      r2.run();
    }
  }
}
class IntObserver {
  private int _numOps;
  public void update() {
    System.out.println (++_numOps);
  }
}
class Int {
  private IntObserver _o;
  public Int(IntObserver o) { _o = o; }
  private int _v;
  public void inc() { _v++; _o.update(); }
  public void dec() { _v--; _o.update(); }
}
class M implements Runnable {
  private Int _c;
  public M(Int c) { _c = c; }
  public void run() {
    _c.inc();
    _c.inc();
    _c.dec();
  }
}
class N implements Runnable {
  private Int _c;
  public N(Int c) { _c = c; }
  public void run() {
    for (int i=0; i<50; i++) {
      if (i%3==0) {
        _c.dec();
      } else {
        _c.inc();
      }
    }
  }
}
