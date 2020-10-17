package iterator.exprtwo;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.List;
import java.util.ArrayList;
import java.util.Stack;
import java.util.Set;
import java.util.HashSet;
import enumeration.Op;

public interface Expr {
  public int evaluate();
  public Iterator preorderIterator();
  public Iterator postorderIterator();
  public Iterator breadthIterator();
}

abstract class AbsExpr implements Expr {
  public abstract int evaluate();
  public Iterator preorderIterator() {
    PreorderIterator i = new PreorderIterator();
    i.addNode(this);
    return i;
  }
  public Iterator postorderIterator() {
    PostorderIterator i = new PostorderIterator();
    i.addNode(this);
    return i;
  }
  public Iterator breadthIterator() {
    BreadthIterator i = new BreadthIterator();
    i.addNode(this);
    return i;
  }
  abstract Object acceptPreOrder(PreorderIterator i);
  abstract Object acceptPostOrder(PostorderIterator i);
  abstract Object acceptBreadth(BreadthIterator i);
}

class Const extends AbsExpr {
  private final int _v;
  public Const(int v) {
    _v = v;
  }
  public int evaluate() {
    return _v;
  }
  Object acceptPreOrder(PreorderIterator i) {
    return _v;
  }
  Object acceptPostOrder(PostorderIterator i) {
    i.markVisited(this);
    return _v;
  }
  Object acceptBreadth(BreadthIterator i) {
    i.markVisited(this);
    return _v;
  }
}

class BinOp extends AbsExpr {
  private final AbsExpr _l;
  private final AbsExpr _r;
  private final Op _op;
  public BinOp(AbsExpr l, Op op, AbsExpr r) {
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
  Object acceptPreOrder(PreorderIterator i) {
    i.addNode(_r);
    i.addNode(_l);
    return _op;
  }
  Object acceptPostOrder(PostorderIterator i) {
    i.markVisited(this);
    if (i.visited(_r)) {
      return _op;
    } else {
      i.addNode(this);
      if (i.visited(_l))
        return _r.acceptPostOrder(i);
      else
        return _l.acceptPostOrder(i);
    }
  }
  Object acceptBreadth(BreadthIterator i) {
    if (i.visited(this)) {
      i.addNode(_l);
      i.addNode(_r);
      return i.next();
    } else {
      i.markVisited(this);
      i.addNode(this);
      return _op;
    }
  }
}

class PreorderIterator implements Iterator {
  private Stack<AbsExpr> _s = new Stack<AbsExpr>();
  public boolean hasNext() {
    return ! _s.empty();
  }
  void addNode(AbsExpr e) {
    _s.push(e);
  }
  public Object next() {
    if (hasNext())
      return (_s.pop()).acceptPreOrder(this);
    throw new NoSuchElementException();
  }
  public void remove() {
    throw new UnsupportedOperationException();
  }
}

class PostorderIterator implements Iterator {
  private Set<AbsExpr> _v = new HashSet<AbsExpr>();
  private Stack<AbsExpr> _s = new Stack<AbsExpr>();
  public boolean hasNext() {
    return ! _s.empty();
  }
  boolean visited(AbsExpr e) {
    return _v.contains(e);
  }
  void markVisited(AbsExpr e) {
    _v.add(e);
  }
  void addNode(AbsExpr e) {
    _s.push(e);
  }
  public Object next() {
    if (hasNext())
      return (_s.pop()).acceptPostOrder(this);
    throw new NoSuchElementException();
  }
  public void remove() {
    throw new UnsupportedOperationException();
  }
}

class BreadthIterator implements Iterator {
  private Set<AbsExpr> _v = new HashSet<AbsExpr>();
  private List<AbsExpr> _l = new ArrayList<AbsExpr>();
  public boolean hasNext() {
    return ! _l.isEmpty();
  }
  boolean visited(AbsExpr e) {
    return _v.contains(e);
  }
  void markVisited(AbsExpr e) {
    _v.add(e);
  }
  void addNode(AbsExpr e) {
    _l.add(e);
  }
  public Object next() {
    if (hasNext()) {
      AbsExpr e = _l.get(0);
      _l.remove(0);
      return e.acceptBreadth(this);
    }
    throw new NoSuchElementException();
  }
  public void remove() {
    throw new UnsupportedOperationException();
  }
}

