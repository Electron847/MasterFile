package singleton.pub2;
public interface S {
  public static final S instance = SFactory.newS();
  public int inc();
}
class SFactory {
  static S newS() {
    if ("linux".equals(System.getProperty("os.name")))
      return new SLinux();
    else
      return new SOther();
  }
}
final class SLinux implements S { 
  private int i;
  public int inc() {return ++i;}
}
final class SOther implements S {
  private int i;
  public int inc() {return --i;}
}
