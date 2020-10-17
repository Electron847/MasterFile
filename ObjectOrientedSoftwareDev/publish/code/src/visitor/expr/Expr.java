package visitor.expr;
import enumeration.Op;

public interface Expr {
  <T> T accept(ExprVisitor<T> v);
}

class Const implements Expr {
  private final int _c;
  public Const(int c) {
    _c = c;
  }
  public <T> T accept(ExprVisitor<T> v) {
    return v.visitConst(_c);
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
  public <T> T accept(ExprVisitor<T> v) {
    return v.visitBinOp(_l, _op, _r);
  }
}
