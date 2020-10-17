package composite.three;
public class Main {
  public static void main(String[] args) {
    Expr one = ExprFactory.newConst(1);
    Expr onePtwo = ExprFactory.newPlus
      (ExprFactory.newConst(1), ExprFactory.newConst(2));
    Expr threeMfour = ExprFactory.newMult
      (ExprFactory.newConst(3), ExprFactory.newConst(4));
    Expr m = ExprFactory.newMinus (onePtwo, threeMfour);
    Expr n = ExprFactory.newQuot (m, ExprFactory.newConst(5));

    System.out.println(n);
    System.out.println("Value: " + n.eval());
  }
}
