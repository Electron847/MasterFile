package project.model;

import java.util.List;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import project.animator.Animator;
import project.animator.AnimationPainter;

/**
 * An example to model for a simple visualization.
 * The model contains roads organized in a matrix.
 * See {@link #Model(AnimationPainterBuilder, int, int)}.
 */
public class Model extends Observable implements AnimationPainter {
  private List<Agent> _agents;
  private Animator _animator;
  private AnimationPainter _painter;
  private boolean _disposed;
  private int _time;

  /** Creates a model to be visualized using the <code>builder</code>.
   *  If the builder is null, no visualization is performed.
   *  The number of <code>rows</code> and <code>columns</code>
   *  indicate the number of {@link Light}s, organized as a 2D
   *  matrix.  These are separated and surrounded by horizontal and
   *  vertical {@link Road}s.  For example, calling the constructor with 1
   *  row and 2 columns generates a model of the form:
   *  <pre>
   *     |  |
   *   --@--@--
   *     |  |
   *  </pre>
   *  where <code>@</code> is a {@link Light}, <code>|</code> is a
   *  vertical {@link Road} and <code>--</code> is a horizontal {@link Road}.
   *  Each road has one {@link Car}.
   *
   *  <p>
   *  The {@link AnimationPainterBuilder} is used to set up an {@link
   *  AnimationPainter}.
   *  {@link AnimationPainterBuilder#getAnimator()} is registered as
   *  an observer of this model.
   *  Callbacks to {@link AnimationPainter#paint(Object)} delegate to {@link
   *  AnimationPainterBuilder#getAnimationPainter()}.
   *  <p>
   */
  public Model(AnimationPainterBuilder builder, int rows, int columns) {
    if (rows < 0 || columns < 0 || (rows == 0 && columns == 0)) {
      throw new IllegalArgumentException();
    }
    if (builder == null) {
      builder = new NullAnimationPainterBuilder();
    }
    _agents = new ArrayList<Agent>();
    setup(builder, rows, columns);
    _painter = builder.getAnimationPainter();
    _animator = builder.getAnimator();
    super.addObserver(_animator);
  }

  /**
   * When updating observers, an {@link Animator} will call back
   * here to update the display; this method delegates to the
   * {@link AnimationPainter} created by the {@link
   * AnimationPainterBuilder} passed to the constructor.
   */
  public void paint(Object arg) {
    if (_disposed)
      throw new IllegalStateException();
    _painter.paint(arg);
  }

  /**
   * Run the simulation for <code>duration</code> model seconds.
   */
  public void run(int duration) {
    if (_disposed)
      throw new IllegalStateException();
    for (int i=0; i<duration; i++) {
      _time++;
      Agent[] agents_copy = _agents.toArray(new Agent[0]);
      for (Agent a : agents_copy) {
        a.run(_time);
      }
      super.setChanged();
      super.notifyObservers();
    }
  }

  /**
   * Throw away this model.
   */
  public void dispose() {
    _animator.dispose();
  }

  /**
   * Construct the model, establishing correspondences with the visualizer.
   */
  private void setup(AnimationPainterBuilder builder, int rows, int columns) {
    List<Road> roads = new ArrayList<Road>();
    Light[][] intersections = new Light[rows][columns];
    Boolean reverse;

    // Add Lights
    for (int i=0; i<rows; i++) {
      for (int j=0; j<columns; j++) {
        intersections[i][j] = new Light();
        builder.addLight(intersections[i][j], i, j);
        _agents.add(intersections[i][j]);
      }
    }

    // Add Horizontal Roads
    boolean eastToWest = false;
    for (int i=0; i<rows; i++) {
      for (int j=0; j<=columns; j++) {
        Road l = new Road();
        builder.addHorizontalRoad(l, i, j, eastToWest);
        roads.add(l);
      }
      eastToWest = !eastToWest;
    }

    // Add Vertical Roads
    boolean southToNorth = false;
    for (int j=0; j<columns; j++) {
      for (int i=0; i<=rows; i++) {
        Road l = new Road();
        builder.addVerticalRoad(l, i, j, southToNorth);
        roads.add(l);
      }
      southToNorth = !southToNorth;
    }

    // Add Cars
    for (Road l : roads) {
      Car car = new Car();
      _agents.add(car);
      l.accept(car);
    }
  }
}
