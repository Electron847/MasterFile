package visitor.typecase;
import enumeration.Op;

public class Main {
  public static void main (String[] args) {
    Expr one = new Const(1);
    Expr onePtwo = new BinOp (new Const(1), Op.ADD, new Const(2));
    Expr threeMfour = new BinOp (new Const(3), Op.MUL, new Const(4));
    Expr m = new BinOp (onePtwo, Op.SUB, threeMfour);
    Expr n = new BinOp (m, Op.DIV, new Const(5));
    
    System.out.println (PostorderPrint.run(n));
    System.out.println ("Value: " + Eval.run(n));
  }
}

class PostorderPrint {
  private PostorderPrint() {}
  static private StringBuilder b = new StringBuilder();
  static private StringBuilder runConst(int c) {
    b.append(c + " ");
    return b;
  }
  static private StringBuilder runBinOp(Expr l, Op op, Expr r) {
    run(l); run(r); b.append(op + " ");
    return b;
  }
  static public StringBuilder run(Expr e) {
    if (e instanceof BinOp) {
      BinOp x = (BinOp) e;
      return runBinOp(x.l, x.op, x.r);
    } else {
      Const x = (Const) e;
      return runConst(x.c);
    }
  }
}

class Eval {
  private Eval() {}
  static private Integer runConst(int c) {
    return c;
  }
  static private Integer runBinOp(Expr l, Op op, Expr r) {
    return op.eval(run(l), run(r));
  }
  static public Integer run(Expr e) {
    if (e instanceof BinOp) {
      BinOp x = (BinOp) e;
      return runBinOp(x.l, x.op, x.r);
    } else {
      Const x = (Const) e;
      return runConst(x.c);
    }
  }
}

