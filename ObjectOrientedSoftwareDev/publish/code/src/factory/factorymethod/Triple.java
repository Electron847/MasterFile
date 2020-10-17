package factory.factorymethod;
import java.util.Iterator;
import java.util.ArrayList;
interface Triple<E> extends Iterable<E> { }
class FieldTriple<E> implements Triple<E> {
  private E _one; E _two; E _three;
  public FieldTriple(E one, E two, E three) {
    _one = one; _two = two; _three = three;
  }
  public Iterator<E> iterator() { return new TheIterator(); }

  private class TheIterator implements Iterator<E> {
    private int _i;
    public boolean hasNext() { return _i < 3; }
    public E next() {
      _i++;
      switch (_i) {
        case 1: return _one;
        case 2: return _two;
        case 3: return _three;
      }
      throw new java.util.NoSuchElementException();
    }
    public void remove() { throw new UnsupportedOperationException(); }
  }
}
// Arrays do not play nicely with generics, so use ArrayList
class ArrayTriple<E> implements Triple<E> {
  private ArrayList<E> _a = new ArrayList<E>();
  public ArrayTriple(E one, E two, E three) {
    _a.add(0,one); _a.add(1,two); _a.add(2,three);
  }
  public Iterator<E> iterator() { return new TheIterator(); }
  
  private class TheIterator implements Iterator<E> {
    private int _i = -1;
    public boolean hasNext() { return _i < 2; }
    public E next() {
      _i++;
      if (_i <= 2)
        return _a.get(_i);
      else
        throw new java.util.NoSuchElementException();
    }
    public void remove() { throw new UnsupportedOperationException(); }
  }
}
