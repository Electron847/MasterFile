package packageinterface.main;
import packageinterface.i.I;

public class Main {
  public static void main(String[] args) {
    I x = I.f.newI();
    I y = I.f.newI();
    x.publicMethod(y);
    I.f.pseudoStaticFunction(x);
  }
}
