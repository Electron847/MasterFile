package state.three;

interface I {
  public int f();
  public int g();
  public void changeDirection();
}

class C implements I {
  private CState _state = CState.MINUS;
  private int _index;
  int _i;
  int _j;
  public int f() {
    return _state.f(this);
  }
  public int g() {
    return _state.g(this);
  }
  public void changeDirection() {
    _state = (_state==CState.MINUS) ? CState.PLUS : CState.MINUS;
  }
}

