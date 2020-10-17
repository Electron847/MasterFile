package state.one;

interface I {
  public int f();
  public int g();
  public void changeDirection();
}

class C implements I {
  private boolean _state;
  private int _i;
  private int _j;
  public int f() {
    if (_state)
      _i += 26;
    else
      _i -= 32;
    return _i;
  }
  public int g() {
    if (_state)
      _j += 42;
    else
      _j -= 27;
    return _j;
  }
  public void changeDirection() {
    _state = !_state;
  }
}
