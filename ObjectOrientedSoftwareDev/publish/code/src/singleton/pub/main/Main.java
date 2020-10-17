package singleton.pub.main;
import singleton.pub.S;
public class Main {
  public static void main (String[] args) {
    S s = S.instance;
    System.out.println(s.inc());
    System.out.println(s.inc());
  }
}
