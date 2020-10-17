package strategy.ftoc;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

/* This example is from Robert C. Martin */
public class Main {
  public static void main(String[] args) {
    (new App(new FtoC())).run();
  }
}
final class App {
  private AppStrategy _s;
  public App(AppStrategy s) {
    _s = s;
  }
  public void run() {
    _s.init();
    while (!_s.done())
      _s.idle();
    _s.cleanup();
  }
}
/* public */
interface AppStrategy {
  public void init();
  public void idle();
  public void cleanup();
  public boolean done();
}
final class FtoC implements AppStrategy {
  private boolean _isDone = false;
  private BufferedReader _br;

  public boolean done() {
    return _isDone;
  }
  public void init() {
    _br = new BufferedReader(new InputStreamReader(System.in));
  }
  public void idle() {
    String fstring = readLine();
    if (fstring == null || fstring.length() == 0) {
      _isDone = true;
    } else {
      double f = Double.parseDouble(fstring);
      double c = 5.0/9.0*(f-32);
      System.out.println("F=" + f + ", C=" + c);
    }
  }
  public void cleanup() {
    System.out.println("FtoC exit");
  }
  private String readLine() {
    try { return _br.readLine(); }
    catch(IOException e) { return null; }
  }
}
