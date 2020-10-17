package factory.shape2;
import java.awt.Graphics;
public interface Shape {
  void paint(Graphics g);
}
class Circle implements Shape {
  public void paint(Graphics g) { /* ... */ }
  // ...
}
class Square implements Shape {
  public void paint(Graphics g) { /* ... */ }
  // ...
}
