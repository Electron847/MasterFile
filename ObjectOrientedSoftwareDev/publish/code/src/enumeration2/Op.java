package enumeration2;

public enum Op {
  ADD("+") { public int eval(int x, int y) { return x + y; } },
  SUB("-") { public int eval(int x, int y) { return x - y; } },
  MUL("*") { public int eval(int x, int y) { return x * y; } },
  DIV("/") { public int eval(int x, int y) { return x / y; } };

  private final String _name;
  private Op(String name) { _name = name; }

  public String toString() { return _name; }
  public abstract int eval(int x, int y);
}

