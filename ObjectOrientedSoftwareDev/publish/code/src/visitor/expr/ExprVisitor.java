package visitor.expr;
import enumeration.Op;

public interface ExprVisitor<T> {
  T visitConst(int c);
  T visitBinOp(Expr l, Op op, Expr r);
}

