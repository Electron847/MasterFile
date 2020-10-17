package singleton.state.main;
import singleton.state.S;
public class Main {
  public static void main (String[] args) {
    System.out.println(S.inc());
    System.out.println(S.inc());
  }
}
