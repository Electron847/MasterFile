package singleton.pub3;
public abstract class S {
  private static S newS() {
    if ("linux".equals(System.getProperty("os.name")))
      return new SLinux();
    else
      return new SOther();
  }
  public static final S instance = newS();
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
