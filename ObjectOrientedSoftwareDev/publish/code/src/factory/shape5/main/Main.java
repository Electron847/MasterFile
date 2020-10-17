package factory.shape5.main;
import factory.shape5.Shape;
import factory.shape5.ShapeFactory;
public class Main {
  public static void main (String[] args)
    throws ClassNotFoundException
  {
    ShapeFactory.addShape("Triangle", "shape.main.Triangle");
    Shape[] a = new Shape[3];
    a[0] = ShapeFactory.newShape("Circle");
    a[1] = ShapeFactory.newShape("Square");
    a[2] = ShapeFactory.newShape("Triangle");
    System.out.println(a[0]);
    System.out.println(a[1]);
    System.out.println(a[2]);
  }
}
