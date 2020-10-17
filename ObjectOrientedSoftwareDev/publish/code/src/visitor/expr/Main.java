package visitor.expr;
import enumeration.Op;

public class Main {
  public static void main (String[] args) {
    Expr one = new Const(1);
    Expr onePtwo = new BinOp (new Const(1), Op.ADD, new Const(2));
    Expr threeMfour = new BinOp (new Const(3), Op.MUL, new Const(4));
    Expr m = new BinOp (onePtwo, Op.SUB, threeMfour);
    Expr n = new BinOp (m, Op.DIV, new Const(5));
    
    System.out.println (n.accept(new PostorderToString()));
    System.out.println ("Value: " + n.accept(new Eval()));
  }
}

class Eval implements ExprVisitor<Integer> {
  public Integer visitConst(int c) {
    return c;
  }
  public Integer visitBinOp(Expr l, Op op, Expr r) {
    return op.eval(l.accept(this), r.accept(this));
  }
}

class PostorderToString implements ExprVisitor<StringBuilder> {
  StringBuilder b = new StringBuilder();
  public StringBuilder visitConst(int c) {
    b.append (c + " ");
    return b;
  }
  public StringBuilder visitBinOp(Expr l, Op op, Expr r) {
    l.accept(this); r.accept(this); b.append (op + " ");
    return b;
  }
}

class PreOrderToString implements ExprVisitor<StringBuilder> {
  StringBuilder b = new StringBuilder();
  public StringBuilder visitConst(int c) {
    b.append (c + " ");
    return b;
  }
  public StringBuilder visitBinOp(Expr l, Op op, Expr r) {
    b.append (op + " "); l.accept(this); r.accept(this);
    return b;
  }
}

class InOrderToString implements ExprVisitor<StringBuilder> {
  StringBuilder b = new StringBuilder();
  public StringBuilder visitConst(int c) {
    b.append (c + " ");
    return b;
  }
  public StringBuilder visitBinOp(Expr l, Op op, Expr r) {
    l.accept(this); b.append (op + " "); r.accept(this);
    return b;
  }
}
