package basics.objectclass;
// A static class
public class Main {
  private Main() {}
  static public void main (String[] args) {
    Circle c = new Circle();
    String s = ((c==null) ? "null" : c.toString());
    System.out.println(s);
  }
}
// An object class
final class Circle extends Object {
  public Circle() { super(); }
  public String toString() { return "Circle"; }
}
