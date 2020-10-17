package observer.slider;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
public class Main {
  public static void main(String[] args) {
    new M().run();
  }
}

class M implements Runnable {
  static int MIN = 0;
  static int MAX = 100;
  static int INIT = 50;
  int x = INIT;
  public void run () {
    JSlider s = new JSlider(JSlider.VERTICAL, M.MIN, M.MAX, M.INIT);
    /* ... */
    s.addChangeListener(new ChangeListener() {
        public void stateChanged(ChangeEvent e) {
          JSlider source = (JSlider)e.getSource();
          if (!source.getValueIsAdjusting()) {
            // the next two lines are redundant
            x = (int)source.getValue();
            M.this.x = (int)source.getValue(); 
          }
        }
      });
  }
}
