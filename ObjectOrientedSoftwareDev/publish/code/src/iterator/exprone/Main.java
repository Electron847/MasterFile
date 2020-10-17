package iterator.exprone;
import java.util.Iterator;
import enumeration.Op;

public class Main {
  public static void main (String[] args) {
    Expr one = new Const(1);
    Expr onePtwo = new BinOp (new Const(1), Op.ADD, new Const(2));
    Expr threeMfour = new BinOp (new Const(3), Op.MUL, new Const(4));
    Expr m = new BinOp (onePtwo, Op.SUB, threeMfour);
    Expr n = new BinOp (m, Op.DIV, new Const(5));
    
    for (Iterator i = n.postorderIterator(); i.hasNext(); )
      System.out.print (i.next() + " ");
    System.out.println ("");
    
    System.out.println ("Value: " + n.evaluate());
  }
}
