package observer.seven;
import java.util.Observer;
import java.util.Observable;
public class Main {
  public static void main(String[] argv) {
    Int c = new Int();
    c.addObserver(new IntObserver());
    Runnable r1 = new M(c);
    Runnable r2 = new N(c);
    for (int i=0; i<10000; i++) {
      r1.run();
      r2.run();
    }
  }
}
class IntObserver implements Observer {
  private int _numOps;
  public void update(Observable o, Object arg) {
    System.out.println (++_numOps);
  }
}
class Int extends Observable {
  private int _v;
  public void inc() { _v++; setChanged(); notifyObservers(); }
  public void dec() { _v--; setChanged(); notifyObservers(); }
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
