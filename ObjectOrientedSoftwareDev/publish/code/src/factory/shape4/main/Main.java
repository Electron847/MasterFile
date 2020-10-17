package factory.shape4.main;
import factory.shape4.Shape;
import factory.shape4.ShapeFactory;
public class Main {
  public static void main (String[] args) {
    Shape[] a = new Shape[2];
    a[0] = ShapeFactory.newCircle(1);
    a[1] = ShapeFactory.newSquare(1);
  }
}
