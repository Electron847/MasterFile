package util;
/**
 * A generic subscriber which a class can implement when it wants to be informed
 * of changes in published objects.
 * 
 * @param <T> the type of the publisher object
 * @param <U> the type of the optional data argument
 * @see Subscriptions
 */
public interface Subscriber<T, U> {
  /**
   * This method is called whenever the published object is changed.
   * 
   * @param publisher the object which was the source of the notification.
   * @param data an optional data parameter which encapsulates any
   *   additional data about the event
   */
  void update(T publisher, U data);
}
