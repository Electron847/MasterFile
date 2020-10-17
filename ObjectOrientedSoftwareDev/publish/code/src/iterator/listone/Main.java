package iterator.listone;
import java.util.NoSuchElementException;
  
/* public */
interface List {
  public Iterator newIterator();
}

/* public */
class ListF {
  private ListF() {}
  public static final List nil = new Nil(); /* Singleton */
  public static final List cons(int hd, List tl) /* Factory */ {
    return new Cons(hd, tl);
  }
}

/* public */
interface Iterator {
  public boolean hasNext();
  public int next();
}

/*
 *************************************************************************
 * List classes.
 *************************************************************************
 */
class Nil implements List {
  Nil() {}
  public String toString() { return "nil"; }
  public Iterator newIterator() { return new NullIterator(); }
}

class Cons implements List {
  final int _hd;
  final List _tl;
  Cons(int hd, List tl) { _hd = hd; _tl = tl; }
  public String toString() { return _hd + "::" + _tl.toString(); }
  public Iterator newIterator() { return new ListIterator(this); }
}

class NullIterator implements Iterator {
  NullIterator() { }
  public boolean hasNext() { return false; }
  public int next() { throw new NoSuchElementException(); }
}

class ListIterator implements Iterator {
  private List _node;
  ListIterator(List node) { _node = node; }
  public boolean hasNext() {
    return _node != ListF.nil;
  }
  public int next() {
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
    List test = ListF.cons(1, ListF.cons(2, ListF.cons(3, ListF.nil)));
    System.out.println(test);

    int sum=0;
    for (Iterator i = test.newIterator(); i.hasNext(); )
      sum += i.next();
    System.out.println(sum);

    List rev=ListF.nil;
    for (Iterator i = test.newIterator(); i.hasNext(); )
      rev = ListF.cons(i.next(),rev);
    System.out.println(rev);
  }
}
