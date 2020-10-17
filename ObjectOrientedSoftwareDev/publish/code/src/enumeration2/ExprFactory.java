package enumeration2;

public class ExprFactory {
  private ExprFactory() {}
  static public Expr newConst(int v) {
    return new Const(v);
  }
  static public Expr newPlus(Expr l, Expr r) {
    return new BinOp(l, Op.ADD, r);
  }
  static public Expr newMinus(Expr l, Expr r) {
    return new BinOp(l, Op.SUB, r);
  }
  static public Expr newMult(Expr l, Expr r) {
    return new BinOp(l, Op.MUL, r);
  }
  static public Expr newQuot(Expr l, Expr r) {
    return new BinOp(l, Op.DIV, r);
  }
}
