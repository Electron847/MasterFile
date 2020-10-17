package basics.mutablebasic;
import basics.immutabledata.Pair;
public class Main {
  private Main() {}
  static public void main (final String[] args) {
    final PairBuilder<Integer,String> pb1 = new PairBuilder<Integer,String>();
    pb1.setFirst(42);
    pb1.setSecond("dog");
    System.out.println(pb1);
    final Pair<Integer,String> p1 = pb1.toPair();
    System.out.println(p1);
    
    final PairBuilder<Integer,String> pb2 = new PairBuilder<Integer,String>();
    final Pair<Integer,String> p2 = pb2.toPair();
  }
}
final class PairBuilder<S extends Comparable<S>, T extends Comparable<T>> {
  private S _x;
  private T _y;
  public PairBuilder() { }
  public void setFirst(S x) { _x = x; }
  public void setSecond(T y) { _y = y; }
  public Pair<S,T> toPair() {
    if (_x == null || _y == null)
      throw new NullPointerException();
    return new Pair<S,T>(_x,_y);
  }
}    
