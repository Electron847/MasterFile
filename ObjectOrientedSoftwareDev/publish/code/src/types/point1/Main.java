package types.point1;
import java.awt.Color;
final class CartesianPoint {
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
public class Main {
  private Main() {}
  public static void main(String[] args) {
    CartesianPoint p1 = new CartesianPoint(0,0,Color.RED);
    CartesianPoint q1 = new CartesianPoint(1,1,Color.BLUE);
  }
}
