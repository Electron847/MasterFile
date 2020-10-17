package singleton.pub4.main;
import singleton.pub4.S;
public class Main {
  public static void main (String[] args) {
    S s = S.get();
    System.out.println(s.inc());
    System.out.println(s.inc());
  }
}
