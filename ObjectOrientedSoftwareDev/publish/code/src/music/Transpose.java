package music;
class Transpose implements Event {
  Event _e;
  double _f;
  public Transpose(Event e, double factor) {
    _e = e;
    _f = factor;
  }
  public void play() {
    Music.scalePitch(_f);
    _e.play();
    Music.scalePitch(1.0/_f);
  }
}
  
