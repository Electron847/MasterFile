package types.instanceOf;
class C {}
class Main {
  private Main() {}
  public static void f1(Object x) {
    if (x instanceof C)
      System.out.println("C");
    else
      System.out.println("not C");
  }
  public static void f2(Object x) {
    boolean printed = false;
    try {
      C y = (C) x;
    } catch (ClassCastException e) {
      System.out.println("C");
      printed = true;
    } finally {
      if (!printed)
        System.out.println("not C");
    }
  }
  public static void main(String[] args) {
    Main.f1(new C());
    Main.f1(new Object());
    Main.f2(new C());
    Main.f2(new Object());
  }
}
