package factory.shape3;
public class ShapeFactory {
  private ShapeFactory() {}
  static public Shape newInstance(String selector) {
    if ("Ellipse".equals(selector))   return new Ellipse();
    if ("Circle".equals(selector))    return new Ellipse();
    if ("Rectangle".equals(selector)) return new Rectangle();
    if ("Square".equals(selector))    return new Rectangle();
    throw new IllegalArgumentException();
  }
}
