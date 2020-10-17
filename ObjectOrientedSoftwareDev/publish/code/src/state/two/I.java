package state.two;

interface I {
  public int f();
  public int g();
  public void changeDirection();
}

class C implements I {
  private CState[] _states = new CState[] { new CStateMinus(), new CStatePlus() };
  private int _index;
  int _i;
  int _j;
  public int f() {
    return _states[_index].f(this);
  }
  public int g() {
    return _states[_index].g(this);
  }
  public void changeDirection() {
    _index = (_index+1) % 2;
  }
}

interface CState {
  public int f(C x);
  public int g(C x);
}
class CStateMinus implements CState {
  public int f(C x) {
    x._i -= 32;
    return x._i;
  }
  public int g(C x) {
    x._j -= 27;
    return x._j;
  }
}
class CStatePlus implements CState {
  public int f(C x) {
    x._i += 26;
    return x._i;
  }
  public int g(C x) {
    x._j += 42;
    return x._j;
  }
}

