package factory.shape3.main;
import factory.shape3.Shape;
import factory.shape3.ShapeFactory;
public class Main {
  public static void main (String[] args) {
    Shape[] a = new Shape[2];
    a[0] = ShapeFactory.newInstance("Circle");
    a[1] = ShapeFactory.newInstance("Square");
  }
}
