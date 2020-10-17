package project.main;

import project.model.Model;
import project.model.AnimationPainterBuilder;
import project.model.swingvisualizer.SwingAnimationPainterBuilder;

/**
 * A static class to demonstrate the visualization aspect of
 * simulation.
 */
public class Main {
  private Main() {}
  public static void main(String[] args) {
    {
      AnimationPainterBuilder b = new SwingAnimationPainterBuilder();
      Model m = new Model(b, 3, 2);
      m.run(500);
      m.run(500);
      m.dispose();
    }
    {
      AnimationPainterBuilder b = new SwingAnimationPainterBuilder();
      Model m = new Model(b, 1, 1);
      m.run(1000);
      m.dispose();
    }
    System.exit(0);
  }
}

