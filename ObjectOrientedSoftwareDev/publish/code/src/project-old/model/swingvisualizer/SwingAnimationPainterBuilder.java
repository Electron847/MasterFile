package project.model.swingvisualizer;

import project.animator.AnimationPainter;
import project.animator.Animator;
import project.animator.SwingAnimator;
import project.model.AnimationPainterBuilder;
import project.model.Road;
import project.model.MP;
import project.model.Light;

/** 
 * A class for building AnimationPainters and Animators.
 */
public class SwingAnimationPainterBuilder implements AnimationPainterBuilder {
  SwingAnimationPainter _painter;
  Animator _animator;
  public SwingAnimationPainterBuilder() {
    _painter = new SwingAnimationPainter();
    _animator = new SwingAnimator("Traffic Simulator", VP.displayWidth, VP.displayHeight, VP.displayDelay);
  }
  public AnimationPainter getAnimationPainter() {
    if (_painter == null) { throw new IllegalStateException(); }
    AnimationPainter returnValue = _painter;
    _painter = null;
    return returnValue;
  }
  public Animator getAnimator() {
    if (_animator == null) { throw new IllegalStateException(); }
    Animator returnValue = _animator;
    _animator = null;
    return returnValue;
  }
  private static double skipInit = VP.gap;
  private static double skipRoad = VP.gap + MP.roadLength;
  private static double skipCar = VP.gap + VP.elementWidth;
  private static double skipRoadCar = skipRoad + skipCar;
  public void addLight(Light d, int i, int j) {
    double x = skipInit + skipRoad + j*skipRoadCar;
    double y = skipInit + skipRoad + i*skipRoadCar;
    Translator t = new TranslatorWE(x, y, MP.carLength, VP.elementWidth, VP.scaleFactor);
    _painter.addLight(d,t);
  }
  public void addHorizontalRoad(Road l, int i, int j, boolean eastToWest) {
    double x = skipInit + j*skipRoadCar;
    double y = skipInit + skipRoad + i*skipRoadCar;
    Translator t = eastToWest ? new TranslatorEW(x, y, MP.roadLength, VP.elementWidth, VP.scaleFactor)
                              : new TranslatorWE(x, y, MP.roadLength, VP.elementWidth, VP.scaleFactor);
    _painter.addRoad(l,t);
  }
  public void addVerticalRoad(Road l, int i, int j, boolean southToNorth) {
    double x = skipInit + skipRoad + j*skipRoadCar;
    double y = skipInit + i*skipRoadCar;
    Translator t = southToNorth ? new TranslatorSN(x, y, MP.roadLength, VP.elementWidth, VP.scaleFactor)
                                : new TranslatorNS(x, y, MP.roadLength, VP.elementWidth, VP.scaleFactor);
    _painter.addRoad(l,t);
  }
}
