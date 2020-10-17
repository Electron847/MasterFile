package util;

import junit.framework.Assert;
import junit.framework.TestCase;

public class SubscriptionsTEST extends TestCase {
  private static class Counter {
    private Subscriptions<Counter,String> _oh = new Subscriptions<Counter,String>(this);
    public void addSubscriber(Subscriber<Counter,String> subscriber) {
      _oh.addSubscriber(subscriber);
    }
    public void notifySubscribers(String data) {
      _oh.notifySubscribers(data);
    }
    
    int _j;
    public int get() {
      return _j;
    }
    public void inc() {
      _j++;
      _oh.setChanged();
    }
    public String toString() {
      return "Counter(" + _j + ")";
    }
  }

  public SubscriptionsTEST(String name) {
    super(name);
  }
  public void testA () {
    Counter c = new Counter();
    c.addSubscriber(new Subscriber<Counter,String>() {
      public void update(Counter sender, String data) {
        System.out.println ("update(" + sender + "," + data + ")");
      }});
    c.inc();
    c.inc();
    c.notifySubscribers("Dog");
  }
  private String output;
  public void testB () {
    Counter c = new Counter();
    c.addSubscriber(new Subscriber<Counter,String>() {
      public void update(Counter sender, String data) {
        output = ("update(" + sender + "," + data + ")");
      }});
    c.inc();
    c.inc();
    c.notifySubscribers("Dog");
    Assert.assertEquals("update(Counter(2),Dog)",output);
  }
}
