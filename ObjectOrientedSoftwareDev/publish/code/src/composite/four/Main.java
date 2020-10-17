package composite.four;
public class Main {
  public static void main(String[] args) {
    Expr onePtwo = ExprFactory.newPlus
      (ExprFactory.newConst(1), ExprFactory.newConst(2));
    
    ExprBuilder eb = ExprFactory.newMultBuilder(); 
    eb.add(ExprFactory.newConst(3));
    eb.add(ExprFactory.newConst(4));
    eb.add(onePtwo);
    Expr multiplies = eb.toExpr();
    Expr m = ExprFactory.newMinus (onePtwo, multiplies);

    System.out.println(m);
    System.out.println("Value: " + m.eval());
  }
}
