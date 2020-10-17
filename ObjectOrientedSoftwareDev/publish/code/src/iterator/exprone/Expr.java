package iterator.exprone;
import java.util.Iterator;
import java.util.NoSuchElementException;
import enumeration.Op;

public interface Expr {
  int evaluate();
  Iterator postorderIterator();
}

class Const implements Expr {
  private final int _v;
  public Const(int v) {
    _v = v;
  }

  public int evaluate() {
    return _v;
  }
  public Iterator postorderIterator() {
    return new LeafIterator(_v);
  }
}

class BinOp implements Expr {
  private final Expr _l;
  private final Expr _r;
  private final Op _op;

  public BinOp(Expr l, Op op, Expr r) {
    if ((l == null) || (op == null) || (r == null)) {
      throw new IllegalArgumentException();
    }
    _op = op;
    _l = l;
    _r = r;
  }

  public int evaluate() {
    return _op.eval(_l.evaluate(), _r.evaluate());
  }
  public Iterator postorderIterator() {
    return new PostorderIterator
      (_op, _l.postorderIterator(), _r.postorderIterator());
  }
}


class PostorderIterator implements Iterator {
  private Object _v;
  private final Iterator _l;
  private final Iterator _r;

  PostorderIterator(Object v, Iterator l, Iterator r) {
    _v = v;
    _l = l;
    _r = r;
  }
  public boolean hasNext() {
    return _r.hasNext() || _l.hasNext() || (_v != null);
  }
  public Object next() {
    if (_l.hasNext()) {
      return _l.next();
    } else if (_r.hasNext()) {
      return _r.next();
    } else if (_v != null) {
      Object v = _v;
      _v = null;
      return v;
    }
    throw new NoSuchElementException();
  }
  public void remove() {
    throw new UnsupportedOperationException();
  }
}

class LeafIterator implements Iterator {
  private Object _v;

  public LeafIterator(Object v) {
    _v = v;
  }
  public boolean hasNext() {
    return (_v != null);
  }
  public Object next() {
    if (_v != null) {
      Object v = _v;
      _v = null;
      return v;
    }
    throw new NoSuchElementException();
  }
  public void remove() {
    throw new UnsupportedOperationException();
  }
}
