package music;
interface Event {
  public void play();
}
class Note implements Event {
  int _d;
  double _f;
  public Note(int duration, double factor) {
    _d = duration;
    _f = factor;
  }
  public void play() {
    Music.scalePitch(_f);
    Music.play(_d);
    Music.scalePitch(1.0/_f);
  }
}
class Rest implements Event {
  int _d;
  public Rest(int duration) {
    _d = duration;
  }
  public void play() {
    Music.rest(_d);
  }
}
