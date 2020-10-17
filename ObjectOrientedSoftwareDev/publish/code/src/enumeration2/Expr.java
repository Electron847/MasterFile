package enumeration2;

public interface Expr {
  void printPostorder();
  int evaluate();
}

class Const implements Expr {
  private final int _v;
  public Const(int v) {
    _v = v;
  }

  public int evaluate() {
    return _v;
  }
  public void printPostorder() {
    System.out.print(_v + " ");
  }
}

class BinOp implements Expr {
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

  public int evaluate() {
    return _op.eval(_l.evaluate(), _r.evaluate());
  }
  public void printPostorder() {
    _l.printPostorder();
    _r.printPostorder();
    System.out.print(_op + " ");
  }
}
