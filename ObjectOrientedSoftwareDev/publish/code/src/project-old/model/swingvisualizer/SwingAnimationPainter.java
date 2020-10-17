package project.model.swingvisualizer;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import project.animator.AnimationPainter;
import project.model.Road;
import project.model.MP;
import project.model.Car;
import project.model.Light;

/**
 * Class for drawing the Model.
 */
class SwingAnimationPainter implements AnimationPainter {
  private List<Element<Road>> _roadElements;
  private List<Element<Light>> _lightElements;
  SwingAnimationPainter() {
    _roadElements = new ArrayList<Element<Road>>();
    _lightElements = new ArrayList<Element<Light>>();
  }    
  void addLight(Light x, Translator t) {
    _lightElements.add(new Element<Light>(x,t));
  }
  void addRoad(Road x, Translator t) {
    _roadElements.add(new Element<Road>(x,t));
  }

  public void paint(Object o) {
    if (!(o instanceof Graphics)) {
      throw new IllegalStateException();
    }
    Graphics g = (Graphics) o;
    
    // draw the background elements
    for (Element<Light> e : _lightElements) {
      if (e.x.getState()) {
        g.setColor(Color.BLUE);
      } else {
        g.setColor(Color.YELLOW);
      }
      XGraphics.fillOval(g, e.t, 0, 0, MP.carLength, VP.elementWidth);
    }
    g.setColor(Color.BLACK);
    for (Element<Road> e : _roadElements) {
      XGraphics.fillRect(g, e.t, 0, 0, MP.roadLength, VP.elementWidth);
    }

    // draw the foreground elements
    for (Element<Road> e : _roadElements) {
      for (Car d : e.x.getCars()) {
        g.setColor(d.getColor());
        XGraphics.fillOval(g, e.t, d.getPosition(), 0, MP.carLength, VP.elementWidth);
      }
    }
  }
}
