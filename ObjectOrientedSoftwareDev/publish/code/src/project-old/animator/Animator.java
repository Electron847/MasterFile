package project.animator;

import java.util.Observable;
import java.util.Observer;
  
/**
 * An interface for displaying simulations.
 * This interface specializes the {@link Observer} interface.
 * Calls to {@link Observer#update(Observable o, Object arg1)} will
 * result in a callback to
 * {@link AnimationPainter#paint(Object arg2)} on <code>o</code>:
 * <ul>
 * <li>
 * If <code>o</code> is not an instance of {@link AnimationPainter},
 * then <code>update</code> thows an {@link IllegalStateException}.
 * </li>
 * <li>
 * The argument <code>arg1</code> of <code>update</code> is ignored.
 * </li>
 * <li>
 * The argument <code>arg2</code> of <code>paint</code> is provided by
 * the <code>Animator</code>.  <code>arg2</code> is intended to
 * provide an object for displaying the informtion, such as an
 * <code>OutputStream</code> or a <code>Graphics</code>.
 * </li>
 * <li>
 * This callback is executed in the swing thread.
 * </li>
 * </ul>
 */
public interface Animator extends Observer {
  public void dispose();
}


