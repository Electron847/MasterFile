package types.point4;
import java.awt.Color;
interface Colored {
  Color getColor();
}
interface Point {
  double getX();
  double getY();
}
final class CartesianPoint implements Colored, Point {
  
  private final double _x;
  private final double _y;
  private final Color _color; 
  public CartesianPoint(double x, double y, Color color) {
    _x = x;
    _y = y;
    _color = color;
  }
  public double getX() { return _x; }
  public double getY() { return _y; }
  public Color getColor() {
    System.out.println("It's Cartesian!");
    return _color;
  }
  
}
final class PolarPoint implements Colored, Point {
  
  private final double _theta;
  private final double _r;
  private final Color _color; 
  public PolarPoint(double theta, double r, Color color) {
    _theta = theta;
    _r = r;
    _color = color;
  }
  public double getX() { return _r*Math.cos(_theta); }
  public double getY() { return _r*Math.sin(_theta); }
  public Color getColor() {
    System.out.println("It's Polar!");
    return _color;
  }
  
}
public class Main {
  private Main() {}
  public static void main(String[] args) {
    CartesianPoint p1 = new CartesianPoint(0,0,Color.RED);
    CartesianPoint q1 = new CartesianPoint(1,1,Color.BLUE);
    PolarPoint r1 = new PolarPoint(0,0,Color.RED);

    Point p2 = p1;
    Point q2 = q1;
    Point r2 = r1;

    Colored p3 = p1;
    Colored q3 = q1;
    Colored r3 = r1;
  }
}
