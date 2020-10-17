package composite.two;
public class ExprFactory {
  private ExprFactory() {}
  static public Expr newConst(int v) {
    return new Const(v);
  }
  static public Expr newPlus(Expr l, Expr r) {
    return new Plus(l, r);
  }
  static public Expr newMinus(Expr l, Expr r) {
    return new Minus(l, r);
  }
  static public Expr newMult(Expr l, Expr r) {
    return new Mult(l, r);
  }
  static public Expr newQuot(Expr l, Expr r) {
    return new Quot(l, r);
  }

  private static class Const implements Expr {
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

  private static class Plus implements Expr {
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

  private static class Minus implements Expr {
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

  private static class Mult implements Expr {
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

  private static class Quot implements Expr {
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
}
