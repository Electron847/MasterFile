package singleton.classloading;

/**
 * This example shows that you need to be careful to ensure that
 * singletons are not accessed before they are created!
 */
class S {
  private static S _instance;
  public static int numInstances;
  public static S getInstance() {
    if (_instance == null) {
      _instance = new S();
    }
    return _instance;
  }
  
  private S() {
    S.numInstances++;
    B.doNothing();  // B is loaded here, during A's constructor
  }
}

class B {
  // B caches the only instance of S
  public static S a = S.getInstance();
  public static void doNothing() {}
}

class Main {
  public static void main(String[] args) {
    System.out.println(S.getInstance() == B.a); // false!
    System.out.println(S.numInstances);         // 2!
  }
}
