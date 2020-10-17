package state.three;

interface CState {
  public int f(C x);
  public int g(C x);

  public static final CState MINUS = new CStateMinus();
  public static final CState PLUS = new CStatePlus();
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

