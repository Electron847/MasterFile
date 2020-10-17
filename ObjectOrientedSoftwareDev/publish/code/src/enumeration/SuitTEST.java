package enumeration;

import junit.framework.Assert;
import junit.framework.TestCase;


/*
 * see http://junit.sourceforge.net/javadoc/index.html
 */
public class SuitTEST extends TestCase {
  public SuitTEST(String name) {
    super(name);
  }

  public void testCompareTo() {
    Assert.assertTrue(Suit.CLUBS.compareTo(Suit.CLUBS) == 0);
    Assert.assertTrue(Suit.CLUBS.compareTo(Suit.DIAMONDS) == -1);
    Assert.assertTrue(Suit.CLUBS.compareTo(Suit.HEARTS) == -2);
    Assert.assertTrue(Suit.CLUBS.compareTo(Suit.SPADES) == -3);
  }
}
