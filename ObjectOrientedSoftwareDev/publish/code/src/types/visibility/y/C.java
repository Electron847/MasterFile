package types.visibility.y;
import  types.visibility.x.A;
class C extends A {
  int fy(C that) {
    return /*this.x0 + this.x1 + */this.x2 + this.x3
         + /*that.x0 + that.x1 + */that.x2 + that.x3;
  }
}

class D {
  int gy(A that) {
    return /*that.x0 + that.x1 + that.x2 + */that.x3;
  }
}
