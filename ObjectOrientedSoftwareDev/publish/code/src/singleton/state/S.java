package singleton.state;
public class S {
  private S() {}
  static private SState _state;
  static {
    if ("linux".equals(System.getProperty("os.name"))) {
      _state = new SLinux();
    } else {
      _state = new SOther();
    }
  }
  static public int inc() { return _state.inc(); }

  static private interface SState {
    public int inc();
  }
  static private class SLinux implements SState { 
    private int i;
    public int inc() {return ++i;}
  }
  static private class SOther implements SState {
    private int i;
    public int inc() {return --i;}
  }
}
