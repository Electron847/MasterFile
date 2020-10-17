package iterator.listtwo;
import java.util.NoSuchElementException;
  
/* public */
interface List {
  public void accept(IntIterator i);
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
interface IntIterator {
  public void run(int element);
}


/*
 *************************************************************************
 * List classes.
 *************************************************************************
 */
class Nil implements List {
  Nil() {}
  public String toString() { return "nil"; }
  public void accept(IntIterator i) { }
}

class Cons implements List {
  private final int _hd;
  private final List _tl;
  Cons(int hd, List tl) { _hd = hd; _tl = tl; }
  public String toString() { return _hd + "::" + _tl.toString(); }
  public void accept(IntIterator i) {
    i.run(_hd);
    _tl.accept(i);
  }
}

/*
 *************************************************************************
 * Internal Iterators.
 * Traversal controlled by the list.
 *************************************************************************
 */
class Sum implements IntIterator {
  public int value = 0;
  public void run(int hd) {
    value += hd;
  }
}

class Reverse implements IntIterator {
  public List value = ListF.nil;
  public void run(int hd) {
    value = ListF.cons(hd, value);
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

    Sum v1 = new Sum();
    test.accept(v1);
    System.out.println(v1.value);

    Reverse v3 = new Reverse();
    test.accept(v3);
    System.out.println(v3.value);
  }
}
