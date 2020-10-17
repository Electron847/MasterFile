package composite.one;
public interface Expr {
  int eval();
}

final class Const implements Expr {
  private final int _v;
  public Const(int v) {
    _v = v;
  }
  public int eval() {
    return _v;
  }
  public String toString() {
    return Integer.toString(_v);
  }
}

final class Plus implements Expr {
  private final Expr _l;
  private final Expr _r;
  public Plus(Expr l, Expr r) {
    if ((l == null) || (r == null)) {
      throw new IllegalArgumentException();
    }
    _l = l;
    _r = r;
  }
  public int eval() {
    return _l.eval() + _r.eval();
  }
  public String toString() {
    return _l.toString() + " " + _r.toString() + " +";
  }
}

final class Minus implements Expr {
  private final Expr _l;
  private final Expr _r;
  public Minus(Expr l, Expr r) {
    if ((l == null) || (r == null)) {
      throw new IllegalArgumentException();
    }
    _l = l;
    _r = r;
  }
  public int eval() {
    return _l.eval() - _r.eval();
  }
  public String toString() {
    return _l.toString() + " " + _r.toString() + " -";
  }
}

final class Mult implements Expr {
  private final Expr _l;
  private final Expr _r;
  public Mult(Expr l, Expr r) {
    if ((l == null) || (r == null)) {
      throw new IllegalArgumentException();
    }
    _l = l;
    _r = r;
  }
  public int eval() {
    return _l.eval() * _r.eval();
  }
  public String toString() {
    return _l.toString() + " " + _r.toString() + " *";
  }
}

final class Quot implements Expr {
  private final Expr _l;
  private final Expr _r;
  public Quot(Expr l, Expr r) {
    if ((l == null) || (r == null)) {
      throw new IllegalArgumentException();
    }
    _l = l;
    _r = r;
  }
  public int eval() {
    return _l.eval() / _r.eval();
  }
  public String toString() {
    return _l.toString() + " " + _r.toString() + " /";
  }
}
