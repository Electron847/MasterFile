package singleton.pub4;
public abstract class S {
  private static S _instance;
  static {
    if ("linux".equals(System.getProperty("os.name")))
      _instance = new SLinux();
    else
      _instance = new SOther();
  }
  public static S get() {return _instance;}
  public abstract int inc();
}
final class SLinux extends S { 
  private int i;
  public int inc() {return ++i;}
}
final class SOther extends S {
  private int i;
  public int inc() {return --i;}
}
