package basics.swap;
public class Main {
  private Main() {}
  static public void main (String[] args) {
    Integer x = 42;
    Integer y = 27;
    Main.swap(x,y);
    System.out.println(x);
    System.out.println(y);
  }
  static private void swap (Integer a, Integer b) {
    Integer t = a;
    a = b;
    b = t;
  }
}
