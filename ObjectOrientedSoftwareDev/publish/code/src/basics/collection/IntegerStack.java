package basics.collection;
import java.util.List;
import java.util.ArrayList;
import java.util.EmptyStackException;
final public class IntegerStack {
  final private List<Integer> _l;
  public IntegerStack() { _l = new ArrayList<Integer>(); }
  public boolean isEmpty() { return _l.isEmpty(); }
  public void push(Integer x) {
    if (x == null)
      throw new IllegalArgumentException();
    _l.add(x);
  }
  public Integer pop() {
    if (_l.isEmpty())
      throw new EmptyStackException();
    return _l.remove(_l.size()-1);
  }
}
