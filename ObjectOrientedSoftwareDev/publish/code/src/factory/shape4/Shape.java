package factory.shape4;
import java.awt.Graphics;
public interface Shape {
  void paint(Graphics g);
}
class Ellipse implements Shape {
  Ellipse(int radius1, int radius2) { /* ... */ }
  public void paint(Graphics g) { /* ... */ }
  // ...
}
class Rectangle implements Shape {
  Rectangle(int height, int width) { /* ... */ }
  public void paint(Graphics g) { /* ... */ }
  // ...
}
