package clone.factory.main;
import clone.factory.A;
class B extends A implements Cloneable {
  public Object clone() throws CloneNotSupportedException {
    return super.clone();
  }
}
public class Main {
  public static void main(String[] args) throws CloneNotSupportedException {
    B x = new B();
    A y = (A) x.clone();
    B z = (B) x.clone();
  }
}
