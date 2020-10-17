package state.four;

interface I {
  public int f();
  public int g();
  public void changeDirection();
}

class C implements I {
  private CState _state = new CStateMinus(this);
  int _i;
  int _j;
  public int f() {
    return _state.f();
  }
  public int g() {
    return _state.g();
  }
  public void changeDirection() {
    _state = _state.next();
  }
}

interface CState {
  public int f();
  public int g();
  public CState next();
}
class CStateMinus implements CState {
  C _x;
  CStateMinus(C x) { _x = x; }
  public int f() {
    _x._i -= 32;
    return _x._i;
  }
  public int g() {
    _x._j -= 27;
    return _x._j;
  }
  public CState next() {
    return new CStatePlus(_x);
  }
}
class CStatePlus implements CState {
  C _x;
  CStatePlus(C x) { _x = x; }
  public int f() {
    _x._i += 26;
    return _x._i;
  }
  public int g() {
    _x._j += 42;
    return _x._j;
  }
  public CState next() {
    return new CStateMinus(_x);
  }
}

