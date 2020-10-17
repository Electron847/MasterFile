package observer.six;
import java.util.List;
import java.util.ArrayList;

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

interface Observer {
  public void update();
}

class IntObserver implements Observer {
  private int _numOps;
  public void update() {
    System.out.println (++_numOps);
  }
}
class Int {
  private List<Observer> _observers = new ArrayList<Observer>();
  private boolean _changed = false;
  public void addObserver(Observer o) {
    _observers.add(o);
  }
  private void setChanged() {
    _changed = true;
  }
  private void notifyObservers() {
    if (_changed) {
      for (Observer o : _observers) {
        o.update();
      }
      _changed = false;
    }
  }
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
