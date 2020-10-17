package clone.factory;
public class IFactory {
  private IFactory() {}
  public static I newI() { return new B(); }
}

final class B implements I {
  B() { }
}
