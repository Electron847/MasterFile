package singleton.lazy.main;
import singleton.lazy.S;
public class Main {
  public static void main (String[] args) {
    System.out.println(S.inc());
    System.out.println(S.inc());
  }
}
