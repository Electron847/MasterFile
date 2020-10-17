package composite.four;
import java.util.List;
import java.util.LinkedList;

public class ExprFactory {
  private ExprFactory() {}
  static public Expr newConst(int v) {
    return new Const(v);
  }
  static public Expr newPlus(Expr l, Expr r) {
    return new BinOp(l, new OpAdd(), r);
  }
  static public Expr newMinus(Expr l, Expr r) {
    return new BinOp(l, new OpSub(), r);
  }
  static public Expr newMult(Expr l, Expr r) {
    return new BinOp(l, new OpMul(), r);
  }
  static public Expr newQuot(Expr l, Expr r) {
    return new BinOp(l, new OpDiv(), r);
  }
  static public ExprBuilder newPlusBuilder() {
    return new NaryOpBuilder(new OpAdd(), new Const(0));
  }
  static public ExprBuilder newMultBuilder() {
    return new NaryOpBuilder(new OpMul(), new Const(1));
  }
}

final class Const implements Expr {
  private final int _v;
  public Const(int v) {
    _v = v;
  }
  public int eval() {
    return _v;
  }
  public String toString() {
    return Integer.toString(_v);
  }
}
  
final class BinOp implements Expr {
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
  public int eval() {
    return _op.eval(_l.eval(), _r.eval());
  }
  public String toString() {
    return _l.toString() + " " + _r.toString() + " " + _op.toString();
  }
}

final class NaryOp implements Expr {
  private final Expr[] _args;
  private final Expr _zero;
  private final Op _op;
  public NaryOp(Expr[] args, Op op, Expr zero) {
// Don't need to test these, since the builder checks them.
//     if ((args == null) || (op == null) || (zero == null))
//       throw new IllegalArgumentException();
//     for (int i=0; i<args.length; i++)
//       if (args[i] == null)
//         throw new IllegalArgumentException();
    _op = op;
    _args = args;
    _zero = zero;
  }
  public int eval() {
    int result = _zero.eval();
    for (int i=0; i<_args.length; i++)
      result = _op.eval(result, _args[i].eval());
    return result;
  }
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("[");
    for (int i=0; i<_args.length; i++) {
      sb.append(_args[i].toString());
      if (i+1<_args.length)
        sb.append(", ");
    }
    sb.append("]");
    sb.append(_op.toString());
    sb.append(" ");
    return sb.toString();
  }
}

final class NaryOpBuilder implements ExprBuilder {
  private final List<Expr> _args;
  private final Expr _zero;
  private final Op _op;
  public NaryOpBuilder(Op op, Expr zero) {
    if ((op == null) || (zero == null))
      throw new IllegalArgumentException();
    _args = new LinkedList<Expr>();
    _op = op;
    _zero = zero;
  }
  public void add(Expr e) {
    if (e == null)
      throw new IllegalArgumentException();
    _args.add(e);
  }
  public Expr toExpr() {
    return new NaryOp(_args.toArray(new Expr[0]), _op, _zero);
  }
}

interface Op {
  public abstract int eval(int x, int y);
}
final class OpAdd implements Op {
  public String toString() { return "+"; }
  public int eval(int x, int y) { return x+y; }
}
final class OpSub implements Op {
  public String toString() { return "-"; }
  public int eval(int x, int y) { return x-y; }
}
final class OpMul implements Op {
  public String toString() { return "*"; }
  public int eval(int x, int y) { return x*y; }
}
final class OpDiv implements Op {
  public String toString() { return "/"; }
  public int eval(int x, int y) { return x/y; }
}
