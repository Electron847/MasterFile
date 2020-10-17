package singleton.staticClass;
public class S {
  static private int i;
  
  private S() {}
  static public int inc() { return i++; }
}
