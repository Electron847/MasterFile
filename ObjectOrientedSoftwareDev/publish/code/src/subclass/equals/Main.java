package subclass.equals;
class C1 {
  int _i;
  public C1(int i) { _i = i; }
  public boolean equals(Object thatObject) {
    if (!(thatObject instanceof C1))
      return false;
    C1 that = (C1) thatObject;
    return that._i == this._i;
  }
}
class D1 extends C1 {
  int _j;
  public D1(int i, int j) { super(i); _j = j; }
  public boolean equals(Object thatObject) {
    if (!(thatObject instanceof D1))
      return false;
    D1 that = (D1) thatObject;
    return that._i == this._i && that._j == this._j;
  }
}
class C2 {
  int _i;
  public C2(int i) { _i = i; }
  public boolean equals(Object thatObject) {
    if ((thatObject == null) || (thatObject.getClass() != this.getClass()))
      return false;
    C2 that = (C2) thatObject;
    return that._i == this._i;
  }
}
class D2 extends C2{
  int _j;
  public D2(int i, int j) { super(i); _j = j; }
  public boolean equals(Object thatObject) {
    if ((thatObject == null) || (thatObject.getClass() != this.getClass()))
      return false;
    D2 that = (D2) thatObject;
    return that._i == this._i && that._j == this._j;
  }
}
public class Main {
  public static void main(String[] args) {
    C1 x1 = new C1(27);
    C1 y1 = new D1(27,42);
    System.out.println("x1==y1? " + (x1.equals(y1) ? "true" : "false"));
    System.out.println("y1==x1? " + (y1.equals(x1) ? "true" : "false"));

    C2 x2 = new C2(27);
    C2 y2 = new D2(27,42);
    System.out.println("x2==y2? " + (x2.equals(y2) ? "true" : "false"));
    System.out.println("y2==x2? " + (y2.equals(x2) ? "true" : "false"));
  }
}
