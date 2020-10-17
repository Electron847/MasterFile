#include <iostream>

class B {
public:
  virtual void dynamicMethod() {
    cout << "\tmaybe dynamic dispatch: B" << endl;
  }
  void staticMethod() {
    cout << "\talways static dispatch: B" << endl;
  }
  static void classMethod() {
    cout << "\tclass method:           B" << endl;
  }
};

class D : public B {
public:
  virtual void dynamicMethod() {
    cout << "\tmaybe dynamic dispatch: D" << endl;
  }
  void staticMethod() {
    cout << "\talways static dispatch: D" << endl;
  }
  static void classMethod() {
    cout << "\tclass method:           D" << endl;
  }
};

void valueFunction(B x) {
  // pass by value unboxes
  cout << "  call by value" << endl;
  x.dynamicMethod();
  x.staticMethod();
  x.classMethod();
}

void referenceFunction(B& x) {
  // pass by reference does not unbox
  cout << "  call by reference" << endl;
  x.dynamicMethod();
  x.staticMethod();
  x.classMethod();
}

void pointerFunction(B* xp) {
  // this is how pass by reference is implemented
  cout << "  call by pointer" << endl;
  xp->dynamicMethod();
  xp->staticMethod();
  xp->classMethod();
}

void main() {
  cout << "[compile-time D, run-time D, boxed]" << endl;
  D* yp = new D();
  yp->dynamicMethod();
  yp->staticMethod();
  yp->classMethod();
  valueFunction(*yp);
  referenceFunction(*yp);
  pointerFunction(yp);

  cout << endl;

  cout << "[compile-time B, run-time B, unboxed]" << endl;
  B z = *yp;
  z.dynamicMethod();
  z.staticMethod();
  z.classMethod();
  valueFunction(z);
  referenceFunction(z);
  pointerFunction(&z);

  cout << endl;

  cout << "[compile-time D, run-time D, unboxed]" << endl;
  D y = *yp;
  y.dynamicMethod();
  y.staticMethod();
  y.classMethod();
  valueFunction(y);
  referenceFunction(y);
  pointerFunction(&y);

  cout << endl;

  cout << "[compile-time B, run-time D, boxed]" << endl;
  B* zp = &y;
  zp->dynamicMethod();
  zp->staticMethod();
  zp->classMethod();
  valueFunction(*zp);
  referenceFunction(*zp);
  pointerFunction(zp);
}
  
