package factory.shape5;
import java.util.List;
import java.util.ArrayList;
public class ShapeFactory {
  private ShapeFactory() {}
  static private List<Pair> _pairs;
  static private class Pair {
    String shapename;
    Class classname;
    Pair(String shapename, Class classname) {
      this.shapename = shapename;
      this.classname = classname;
    }
  }
  static public void addShape(String shapename, String classpath)
    throws ClassNotFoundException
  {
    _pairs.add(new Pair(shapename, Class.forName(classpath)));
  }
  
  static public Shape newShape(String selector) {
    try {
      for (Pair p : _pairs) {
        if (p.shapename.equals(selector))
          return (Shape) p.classname.newInstance();
      }
    } catch (InstantiationException e) {
      throw new IllegalArgumentException();
    } catch (IllegalAccessException e) {
      throw new IllegalArgumentException();
    }
    throw new IllegalArgumentException();
  }
  static { // initializer
    _pairs = new ArrayList<Pair>();
    try {
      addShape("Ellipse",   "shape.s5.Ellipse");
      addShape("Rectangle", "shape.s5.Rectangle");
      addShape("Circle",    "shape.s5.Ellipse");
      addShape("Square",    "shape.s5.Rectangle");
    } catch (ClassNotFoundException e) {
      throw new RuntimeException();
    }
  }
}
