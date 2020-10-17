package basics.mutabledata;
import basics.immutabledata.Pair;
public class Main {
  private Main() {}
  static public void main (final String[] args) {
    final MutPair<Integer,String> mp1 = new MutPair<Integer,String>();
    mp1.setFirst(42);
    mp1.setSecond("dog");
    System.out.println(mp1);
  }
}
