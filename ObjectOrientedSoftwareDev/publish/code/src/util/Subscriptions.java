package util;
import java.util.ArrayList;
import java.util.List;

/**
 * A helper class which can be used to manage an object's subscriptions.
 * 
 * @param <T> the type of the publisher
 * @param <U> the type of the optional data argument
 * @see Subscriber
 */
public final class Subscriptions<T, U> {
	private final T publisher;
	private final List<Subscriber<T, U>> subscribers;
	private boolean changed = false;

	/**
	 * Initializes a new instance of the {@link Subscriptions} class with
	 * zero subscribers.
	 * 
	 * @param publisher the object being publisher
	 * @throws IllegalArgumentException if publisher is <code>null</code>
	 */
	public Subscriptions(T publisher) {
		if (publisher == null)
			throw new IllegalArgumentException("publisher cannot be null");
		this.publisher = publisher;
		this.subscribers = new ArrayList<Subscriber<T, U>>();
	}

	/**
	 * Adds an subscriber to the set of subscribers for the publisher object,
	 * provided that it is not the same as some subscriber already in the set.
	 * 
	 * @param subscriber
	 * @throws IllegalArgumentException if the parameter subscriber is <code>null</code>
	 */
	public void addSubscriber(Subscriber<T, U> subscriber) {
		if (subscriber == null)
			throw new IllegalArgumentException("subscriber cannot be null");
		if (!subscribers.contains(subscriber)) {
			subscribers.add(subscriber);
		}
	}

	/**
	 * Indicates that the publisher object has no longer changed, or that it has
	 * already notified all of its subscribers of its most recent change, so that
	 * {@link #hasChanged()} will now return <code>false</code>. This method
	 * is called automatically by the {@link #notifySubscribers} methods.
	 * 
	 */
	public void clearChanged() {
		changed = false;
	}

	/**
	 * Returns the number of subscribers of publisher object.
	 * 
	 * @return the number of subscribers of publisher object
	 */
	public int countSubscribers() {
		return subscribers.size();
	}

	/**
	 * Deletes an subscriber from the set of subscribers. Passing <code>null</code>
	 * to this method will have no effect.
	 * 
	 * @param subscriber the {@link Subscriber} to be deleted
	 */
	public void deleteSubscriber(Subscriber<T, U> subscriber) {
		subscribers.remove(subscriber);
	}

	/**
	 * Clears the subscriber list so that the publisher object no longer has any
	 * subscribers.
	 */
	public void deleteSubscribers() {
		this.subscribers.clear();
	}

	/**
	 * Returns <code>true</code> if the publisher object has changed;
	 * otherwise, <code>false</code>.
	 * 
	 * @return <code>true</code> if the publisher object has changed;
	 *         otherwise, <code>false</code>
	 */
	public boolean hasChanged() {
		return changed;
	}

	/**
	 * If this object has changed, as indicated by the {@link #hasChanged()},
	 * then notify all of its subscribers and then clear the changed state. This
	 * method is equivalent to calling <code>notifySubscribers(null)</code>.
	 */
	public void notifySubscribers() {
		notifySubscribers(null);
	}

	/**
	 * If this object has changed, as indicated by the {@link #hasChanged()},
	 * then notify all of its subscribers and then clear the changed state.
	 * 
	 * @param data optional event specific data which will be passed to the subscribers
	 */
	public void notifySubscribers(U data) {
		if (hasChanged()) {
			for (Subscriber<T, U> subscriber : subscribers) {
				subscriber.update(publisher, data);
			}
			clearChanged();
		}
	}

	/**
	 * Flags the publisher object as having changed.
	 */
	public void setChanged() {
		changed = true;
	}
}
