package music;
import java.util.List;
import java.util.LinkedList;
class EventGroup implements Event {
  List<Event> _events = new LinkedList<Event>();
  public void add(Event e) {
    _events.add(e);
  }
  
  public void play() {
    for (Event e : _events) {
      e.play();
    }
  }
}
