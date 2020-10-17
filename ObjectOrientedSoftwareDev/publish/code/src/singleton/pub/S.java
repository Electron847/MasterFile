package singleton.pub;
public interface S {
  public static final S instance = 
    ("linux".equals(System.getProperty("os.name"))) ? new SLinux() : new SOther();
  public int inc();
}
final class SLinux implements S { 
  private int i;
  public int inc() {return ++i;}
}
final class SOther implements S {
  private int i;
  public int inc() {return --i;}
}
