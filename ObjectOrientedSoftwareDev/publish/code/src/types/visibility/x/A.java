package types.visibility.x;
public class A {
    private int x0;
            int x1;
  protected int x2;
     public int x3;

  int fx(A that) {
    return this.x0 + this.x1 + this.x2 + this.x3
         + that.x0 + that.x1 + that.x2 + that.x3;
  }
}

class B {
  int gx(A that) {
    return /*that.x0 + */that.x1 + that.x2 + that.x3;
  }
}
