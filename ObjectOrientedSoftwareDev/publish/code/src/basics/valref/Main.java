package basics.valref;
public class Main {
  private Main() {}
  static public void main (String[] args) {
    int vi = 27;  MutInt ri = new MutInt(42);
    int vj = vi;  MutInt rj = ri;
    vi += 1;      ri.plus(1);
    System.out.println(vi);
    System.out.println(vj);
    System.out.println(ri);
    System.out.println(rj);
  }
}
final class MutInt {
  private int _v;
  public MutInt(int v) { _v = v; }
  public String toString() { return "MutInt(" + _v + ")"; }
  public void plus(int z) { _v += z; }
}
