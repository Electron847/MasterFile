package composite.three;

public class ExprFactory {
  private ExprFactory() {}
  static public Expr newConst(int v) {
    return new Const(v);
  }
  static public Expr newPlus(Expr l, Expr r) {
    return new BinOp(l, new OpAdd(), r);
  }
  static public Expr newMinus(Expr l, Expr r) {
    return new BinOp(l, new OpSub(), r);
  }
  static public Expr newMult(Expr l, Expr r) {
    return new BinOp(l, new OpMul(), r);
  }
  static public Expr newQuot(Expr l, Expr r) {
    return new BinOp(l, new OpDiv(), r);
  }

  private static final class Const implements Expr {
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
  
  private static final class BinOp implements Expr {
    private final Expr _l;
    private final Expr _r;
    private final Op _op;
    public BinOp(Expr l, Op op, Expr r) {
      if ((l == null) || (op == null) || (r == null)) {
        throw new IllegalArgumentException();
      }
      _op = op;
      _l = l;
      _r = r;
    }
    public int eval() {
      return _op.eval(_l.eval(), _r.eval());
    }
    public String toString() {
      return _l.toString() + " " + _r.toString() + " " + _op.toString();
    }
  }

  private static interface Op {
    public abstract int eval(int x, int y);
  }
  private static final class OpAdd implements Op {
    public String toString() { return "+"; }
    public int eval(int x, int y) { return x+y; }
  }
  private static final class OpSub implements Op {
    public String toString() { return "-"; }
    public int eval(int x, int y) { return x-y; }
  }
  private static final class OpMul implements Op {
    public String toString() { return "*"; }
    public int eval(int x, int y) { return x*y; }
  }
  private static final class OpDiv implements Op {
    public String toString() { return "/"; }
    public int eval(int x, int y) { return x/y; }
  }
}

