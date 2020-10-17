package state.five;

interface I {
  public int f();
  public int g();
  public void changeDirection();
}

class C implements I {
  private State _state = new StateMinus();
  private int _i;
  private int _j;
  public int f() {
    return _state.f();
  }
  public int g() {
    return _state.g();
  }
  public void changeDirection() {
    _state = _state.next();
  }

  interface State {
    public int f();
    public int g();
    public State next();
  }
  class StateMinus implements State {
    public int f() {
      _i -= 32;
      return _i;
  }
    public int g() {
      _j -= 27;
      return _j;
    }
    public State next() {
      return new StatePlus();
    }
  }
  class StatePlus implements State {
    public int f() {
      _i += 26;
      return _i;
    }
    public int g() {
      _j += 42;
      return _j;
    }
    public State next() {
      return new StateMinus();
    }
  }
}

