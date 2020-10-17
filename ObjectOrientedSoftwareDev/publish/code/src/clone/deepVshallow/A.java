package clone.deepVshallow;
class A implements Cloneable {
  private int _i;
  private A _a;

  public A(int i, A a) { _i = i; _a = a; }
  public A(int i)      { this(i,null); }

  public Object shallow_copy() {
    return new A(_i, _a);
  }

  public Object shallow_clone() throws CloneNotSupportedException {
    return super.clone();
  }

  public boolean shallow_equals(Object o) {
    if (!(this.getClass().equals(o.getClass())))
      return false;
    A that = (A) o;
    return (_i == that._i) && (_a == that._a);
  }

  public Object deep_copy() {
    return new A(_i, (_a==null) ? null : (A) _a.deep_copy());
  }

  public Object deep_clone() throws CloneNotSupportedException {
    A result = (A) super.clone();
    result._a = (_a==null) ? null : (A) _a.deep_clone();
    return result;
  }

  public boolean deep_equals(Object o) {
    if (!(this.getClass().equals(o.getClass())))
      return false;
    A that = (A) o;
    return (_i == that._i)
      && ((_a==null) ? (that._a==null) : _a.deep_equals(that._a));
  }
}
