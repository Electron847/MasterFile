package template.series3;

interface Series {
  void next();
  void print();
}
class SeriesFactory {
  private SeriesFactory() {}
  public static Series newArith() { return new ConcreteSeries(new OpAdd()); }
  public static Series newGeom()  { return new ConcreteSeries(new OpMul()); }
}
class ConcreteSeries implements Series {
  int _x;
  int _y = 1;
  public void next()  { _x++; _y = _op.eval(_y,2); }
  public void print() { System.out.println("x=" + _x + "; y=" + _y); }
  Op _op;
  ConcreteSeries(Op op) { _op = op; }
}
interface Op { public int eval(int x, int y); }
class OpAdd implements Op { public int eval(int x, int y) { return x+y; } }
class OpMul implements Op { public int eval(int x, int y) { return x*y; } }

public class Main {
  public static void main(String[] args) {
    Series x = SeriesFactory.newGeom();
    x.next();
    x.next();
    x.print();
  }
}
