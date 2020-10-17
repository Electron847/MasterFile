package iterator.listfour;
import java.util.NoSuchElementException;
import java.util.ConcurrentModificationException;
  
/* public */
interface MutList {
  public Iterator newIterator();
  public void insert(int i); // insert i at head of list
  public void delete(int i); // delete first occurence of i in the list
}

/* public */
interface Iterator {
  public boolean hasNext();
  public int next();
}

/* public */
class MutListF {
  private MutListF() {}
  public static final MutList newI() {
    return new MutListObj();
  }
}

class MutListObj implements MutList, Changeable {
  private List _l = ListF.nil;
  private int _changeCount = 0;
  public int changeCount() { return _changeCount; }
  public Iterator newIterator() { return _l.newIterator(this); }
  public String toString() { return _l.toString(); }
  public void insert(int i) {
    _changeCount++;
    _l = _l.insert(i);
  }
  public void delete(int i) {
    _changeCount++;
    _l = _l.delete(i);
  }
}

/*
 *************************************************************************
 * Immutable List classes.
 *************************************************************************
 */
interface Changeable {
  public int changeCount();
}

interface List {
  public Iterator newIterator(Changeable parent);
  public List insert(int i);
  public List delete(int i);
}

class ListF {
  private ListF() {}
  public static final List nil = new Nil(); /* Singleton */
  public static final List cons(int hd, List tl) /* Factory */ {
    return new Cons(hd, tl);
  }
}

class Nil implements List {
  Nil() {}
  public String toString() { return "nil"; }
  public Iterator newIterator(Changeable parent) {
    return new NullIterator();
  }
  public List insert(int i) {
    return ListF.cons(i, this);
  }
  public List delete(int i) {
    throw new NoSuchElementException(); 
  }
}

class Cons implements List {
  final int _hd;
  final List _tl;
  Cons(int hd, List tl) { _hd = hd; _tl = tl; }
  public String toString() { return _hd + "::" + _tl.toString(); }
  public Iterator newIterator(Changeable parent) {
    return new ListIterator(this, parent);
  }
  public List insert(int i) {
    return ListF.cons(i, this);
  }
  public List delete(int i) {
    if (_hd == i) {
      return _tl;
    } else {
      List new_tl = _tl.delete(i);
      return ListF.cons(_hd, new_tl);
    }
  }
}

class NullIterator implements Iterator {
  NullIterator() { }
  public boolean hasNext() { return false; }
  public int next() { throw new NoSuchElementException(); }
}

class ListIterator implements Iterator {
  private List _node;
  private Changeable _parent;
  private int _changeCount; 
  ListIterator(List node, Changeable parent) {
    _node = node;
    _parent = parent;
    _changeCount = _parent.changeCount();
  }
  public boolean hasNext() {
    if (_changeCount != _parent.changeCount())
      throw new ConcurrentModificationException();
    return _node != ListF.nil;
  }
  public int next() {
    if (_changeCount != _parent.changeCount())
      throw new ConcurrentModificationException();
    if (! hasNext())
      throw new NoSuchElementException();
    int result = ((Cons)_node)._hd;
    _node = ((Cons)_node)._tl;
    return result;
  }
}

/*
 *************************************************************************
 * A test case.
 *************************************************************************
 */
public class Main {
  public static void main(String[] args) {
    MutList test = MutListF.newI();
    test.insert(3);
    test.insert(2);
    test.insert(1);
    System.out.println(test);

    int sum=0;
    for (Iterator i = test.newIterator(); i.hasNext(); )
      sum += i.next();
    System.out.println(sum);

    List rev=ListF.nil;
    for (Iterator i = test.newIterator(); i.hasNext(); )
      rev = ListF.cons(i.next(),rev);
    System.out.println(rev);

    for (Iterator i = test.newIterator(); i.hasNext(); )
      test.insert(4);
  }
}
