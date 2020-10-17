package factory.shape3;
import java.awt.Graphics;
public interface Shape {
  void paint(Graphics g);
}
class Ellipse implements Shape {
  public void paint(Graphics g) { /* ... */ }
  // ...
}
class Rectangle implements Shape {
  public void paint(Graphics g) { /* ... */ }
  // ...
}
