package util;

public class Logger {
  private Logger() { }
  private static boolean _on = true;
  public static void on () { _on = true; }
  public static void off () { _on = false; }
  public static void println (String s) {
    if (_on) {
      System.out.println (s);
    }
  }
}
