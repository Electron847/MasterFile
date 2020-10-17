package template.series1;

interface Series {
  void next();
  void print();
}
class SeriesFactory {
  private SeriesFactory() {}
  public static Series newArith() { return new ArithSeries(); }
  public static Series newGeom()  { return new GeomSeries(); }
}
class ArithSeries implements Series {
  int _x;
  int _y = 1;
  public void next()  { _x++; _y = _y+2; }
  public void print() { System.out.println("x=" + _x + "; y=" + _y); }
}
class GeomSeries implements Series {
  int _x;
  int _y = 1;
  public void next()  { _x++; _y = _y*2; }
  public void print() { System.out.println("x=" + _x + "; y=" + _y); }
}

public class Main {
  public static void main(String[] args) {
    Series x = SeriesFactory.newGeom();
    x.next();
    x.next();
    x.print();
  }
}
