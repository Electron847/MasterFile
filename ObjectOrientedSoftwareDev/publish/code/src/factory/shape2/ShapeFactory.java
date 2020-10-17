package factory.shape2;
public class ShapeFactory {
  private ShapeFactory() {}
  static public Shape newInstance(String selector) {
    if ("Circle".equals(selector)) return new Circle();
    if ("Square".equals(selector)) return new Square();
    throw new IllegalArgumentException();
  }
}
