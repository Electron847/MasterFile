package singleton.lazy;
public class S {
  private S() {}
  static private SState _state;
  static public int inc() {
    if (_state == null) {
      if ("linux".equals(System.getProperty("os.name"))) {
        _state = new SLinux();
      } else {
        _state = new SOther();
      }
    }
    return _state.inc();
  }

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
