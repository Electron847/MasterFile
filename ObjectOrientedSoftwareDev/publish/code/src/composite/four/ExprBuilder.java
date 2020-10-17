package composite.four;
public interface ExprBuilder {
  Expr toExpr();
  void add(Expr e);
}
