package composite.three;

public interface Expr {
  Object accept(ExprVisitor v);
}
interface ExprVisitor {
  Object visitConst(int c);
  Object visitPlus(Expr l, Expr r);
  Object visitMinus(Expr l, Expr r);
  Object visitMult(Expr l, Expr r);
  Object visitQuot(Expr l, Expr r);
}

class Const implements Expr {
  private final int _c;
  public Const(int c) {
    _c = c;
  }
  public Object accept(ExprVisitor v) {
    return v.visitConst(_c);
  }
}

class Plus implements Expr {
  private final Expr _l;
  private final Expr _r;

  public Plus(Expr l, Expr r) {
    if ((l == null) || (r == null)) {
      throw new IllegalArgumentException();
    }
    _l = l;
    _r = r;
  }
  public Object accept(ExprVisitor v) {
    return v.visitPlus(_l, _r);
  }
}

class Minus implements Expr {
  private final Expr _l;
  private final Expr _r;

  public Minus(Expr l, Expr r) {
    if ((l == null) || (r == null)) {
      throw new IllegalArgumentException();
    }
    _l = l;
    _r = r;
  }
  public Object accept(ExprVisitor v) {
    return v.visitMinus(_l, _r);
  }
}

class Mult implements Expr {
  private final Expr _l;
  private final Expr _r;

  public Mult(Expr l, Expr r) {
    if ((l == null) || (r == null)) {
      throw new IllegalArgumentException();
    }
    _l = l;
    _r = r;
  }
  public Object accept(ExprVisitor v) {
    return v.visitMult(_l, _r);
  }
}

class Quot implements Expr {
  private final Expr _l;
  private final Expr _r;

  public Quot(Expr l, Expr r) {
    if ((l == null) || (r == null)) {
      throw new IllegalArgumentException();
    }
    _l = l;
    _r = r;
  }
  public Object accept(ExprVisitor v) {
    return v.visitQuot(_l, _r);
  }
}
