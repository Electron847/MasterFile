package enumeration;

public abstract class Op {
  private final String _name;
  Op(String name) { _name = name; }
  public String toString() { return _name; }

  public abstract int eval(int x, int y);

  public static final Op ADD = new OpAdd();
  public static final Op SUB = new OpSub();
  public static final Op MUL = new OpMul();
  public static final Op DIV = new OpDiv();
}

final class OpAdd extends Op {
  OpAdd() {  super("+"); }
  public int eval(int x, int y) { return x+y; }
}
final class OpSub extends Op {
  OpSub() {  super("-"); }
  public int eval(int x, int y) { return x-y; }
}
final class OpMul extends Op {
  OpMul() {  super("*"); }
  public int eval(int x, int y) { return x*y; }
}
final class OpDiv extends Op {
  OpDiv() {  super("/"); }
  public int eval(int x, int y) { return x/y; }
}
