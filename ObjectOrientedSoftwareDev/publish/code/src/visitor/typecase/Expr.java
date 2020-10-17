package visitor.typecase;
import enumeration.Op;

public interface Expr { }

class Const implements Expr {
  public final int c;
  public Const(int c) {
    this.c = c;
  }
}

class BinOp implements Expr {
  public final Expr l;
  public final Expr r;
  public final Op op;

  public BinOp(Expr l, Op op, Expr r) {
    if ((l == null) || (op == null) || (r == null)) {
      throw new IllegalArgumentException();
    }
    this.op = op;
    this.l = l;
    this.r = r;
  }
}
