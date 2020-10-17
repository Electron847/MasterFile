package project.animator;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;
  
/**
 * A swing implementation of {@link Animator}.
 * In callbacks to {@link AnimationPainter#paint(Object arg)},
 * <code>arg</code> will be a {@link Graphics} object.
 * This callback is executed in the swing thread.
 * The main thread is paused for <code>delay</code> milliseconds
 * around the time of this callback.
 */
public class SwingAnimator implements Animator {
  // The following fields are manipulated by the main program thread
  private int _delay;
  
  // The following fields are manipulated by the swing thread
  private JFrame _frame; // Swing representation of an OS window
  private ContentPane _content; // A paintable component
  private boolean _disposed = false; // If true, then die
  private boolean _lock = false; // Used to synchronize the threads

  /**
   * @param name  The name to be displayed on the graphical window.
   * @param width The width of the display, in pixels.
   * @param height The height of the display, in pixels.
   * @param delay Time to pause after an update, in milliseconds.
   */
  public SwingAnimator(final String name, final int width, final int height, int delay) {
    _delay = delay;
    // Create a graphics window and display it
    SwingUtilities.invokeLater(new Runnable() {
        public void run() {
          _content = new ContentPane(width, height); // A paintable component for content
          _frame = new JFrame();  // An OS window
          _frame.setTitle(name);  // The title of the Frame
          _frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);  // End program if Frame is closed
          _frame.setContentPane(_content); // Associate the content with the Frame
          _frame.pack(); // Fix the layout of the Frame
          _frame.setVisible(true); // Display the Frame
        }});
  }

  /**
   * Throw away this visualization.
   */
  public void dispose() {
    SwingUtilities.invokeLater(new Runnable() {
        public void run() {
          _frame.dispose();
          _disposed = true;
        }});
  }

  /**
   * Update the display by calling back on {@link AnimationPainter#paint(Object)}.
   * Throws {@link IllegalStateException} if <code>o</code> does not
   * implement {@link AnimationPainter}.
   * The callback is executed in the swing thread.
   * The main thread is paused for <code>delay</code> milliseconds
   * around the time of this callback.
   */
  public void update(Observable o, Object arg) {
    if (_disposed || (! (o instanceof AnimationPainter)))
      throw new IllegalStateException();
    
    final AnimationPainter painter = (AnimationPainter) o;

    _lock = true;
    // Redraw the window
    SwingUtilities.invokeLater(new Runnable() {
        public void run() {
          // Cannot pass painter directly to content, since paint is called indirectly by swing
          _content.setPainter(painter);
          _content.repaint();
          _lock = false;
        }});
    
    // Delay the main thread
    try {
      while (_lock) {
        Thread.currentThread().sleep(_delay);
      }
    } catch (InterruptedException e) {}
  }

  /**
   * A component for painting.
   * All code is executed in the swing thread.
   */
  private static class ContentPane extends JPanel {
    private static final long serialVersionUID = 2008L;
    private int _width;
    private int _height;
    private AnimationPainter _painter;
    
    ContentPane(int width, int height) {
      _width = width;
      _height = height;
      setPreferredSize(new Dimension(width, height));
      setDoubleBuffered(true);
      setOpaque(true);
      setBackground(Color.WHITE);
    }
    
    void setPainter(AnimationPainter painter) {
      _painter = painter;
    }

    /**
     * This method will callback to {@link
     * AnimationPainter#paint(Object)}; this is
     * executed in the swing thread.
     */
    public void paint(Graphics g) {
      // This test is necessary because the swing thread may call this
      // method before the simulation calls SwingAnimator.update()
      if (_painter != null ) {
        // The clearRect is necessary, since JPanel is lightweight
        g.clearRect(0, 0, _width, _height);
        _painter.paint(g);
      }
    }
  }
}
