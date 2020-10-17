package simplebdd.test;

import simplebdd.bool.BoolPred;

public class TestBool {
  public static void main (String[] args) {
    new TestBool().run ();
  }
  
  final BoolPred x;
  final BoolPred y;
  final BoolPred z;
  TestBool () {
    this.x = BoolPred.factory.buildVar ("x");
    this.y = BoolPred.factory.buildVar ("y");
    this.z = BoolPred.factory.buildVar ("z");
  }
  
  void runtest (String s, BoolPred p, boolean expectedValue) {
    String trueString  = (expectedValue? "Good. " : "BAD!! ");
    String falseString = (expectedValue? "BAD!! " : "Good. ");
    if (p == BoolPred.T) {
      System.out.println 
        (trueString + s + " is always true");
    } else {
      System.out.println 
        (falseString + s + " is sometimes false");
    }
  }
  
  public void run () {
    System.out.println (BoolPred.T);
    System.out.println (BoolPred.functions.toGraphString(BoolPred.T));
    System.out.println (x);
    System.out.println (BoolPred.functions.toGraphString(x));
    System.out.println (x.not());
    System.out.println (BoolPred.functions.toGraphString(x.not()));
    System.out.println (x.ite(y,z));
    System.out.println (BoolPred.functions.toGraphString(x.ite(y,z)));
    System.out.println ("(y ? z : x)");
    System.out.println (BoolPred.functions.toGraphString(y.ite(z,x)));
    
    System.out.println ("x xor y xor z");
    System.out.println (BoolPred.functions.toGraphString(x.xor (y).xor (z)));
    System.out.println ("x and x");
    System.out.println (BoolPred.functions.toGraphString(x.and (x)));
    System.out.println ("x or y");
    System.out.println (BoolPred.functions.toGraphString(x.or (y)));
    System.out.println ("y or x");
    System.out.println (BoolPred.functions.toGraphString(y.or (x)));
    
    runtest (x.and (y) + "iff" + y.and (x),
             x.and (y).iff (y.and (x)), true);
    runtest ("", x.and (BoolPred.T).iff (x), true);
    runtest ("", x.xor (y.xor (z)).iff (x.xor (y).xor (z)), true);
  }
  
}
