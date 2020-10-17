package basics.testing;

import junit.framework.Assert;
import junit.framework.TestCase;
import basics.immutabledata.Pair;

public class PairTEST extends TestCase {
  public PairTEST(String name) {
    super(name);
  }
  /**
   *  Constructor should throw IllegalArgumentException if either
   *  argument is null.
   */
  public void testConstructor() {
    try {
      new Pair<String,String>(null,"cat");
      // constructor should have thrown exception
      Assert.fail();
    } catch (IllegalArgumentException e) { }
    try {
      new Pair<String,String>("dog",null);
      // constructor should have thrown exception
      Assert.fail();
    } catch (IllegalArgumentException e) { }
    try {
      new Pair<String,String>(null,null);
      // constructor should have thrown exception
      Assert.fail();
    } catch (IllegalArgumentException e) { }
    try {
      new Pair<String,String>("dog","cat");
    } catch (IllegalArgumentException e) {
      // constructor should have succeeded
      Assert.fail();
    }
  }

  /**
   *  first() and second() should return references to the objects
   *  given to the constructor.
   */
  public void testFirstAndSecond() {
    Integer i = 42;
    Integer j = 91;
    Pair<Integer,Integer> p = new Pair<Integer,Integer>(i, j);
    Assert.assertSame(i, p.first());
    Assert.assertSame(j, p.second());
  }

  /**
   *  Here is a weaker version of the above test; this version allows
   *  Pair() to clone its arguments.
   */
  public void testFirstAndSecond_WeakerVersion() {
    Integer i = 42;
    Integer j = 91;
    Pair<Integer,Integer> p = new Pair<Integer,Integer>(i, j);
    Assert.assertEquals(i, p.first());
    Assert.assertEquals(j, p.second());
  }

  public void testToString() {
    Pair<Integer,Integer> p = new Pair<Integer,Integer>(42, 91);
    Assert.assertEquals("Pair(42,91)", p.toString());
  }

  public void testEquals() {
    Pair<Integer,Integer> p1 = new Pair<Integer,Integer>(42, 91);
    Pair<Integer,Integer> p2 = new Pair<Integer,Integer>(42, 91);
    Pair<Integer,Integer> p3 = new Pair<Integer,Integer>(43, 91);
    Pair<Integer,Integer> p4 = new Pair<Integer,Integer>(42, 92);
    Assert.assertTrue(p1.equals(p1));
    Assert.assertTrue(p1.equals(p2));
    Assert.assertFalse(p1.equals(p3));
    Assert.assertFalse(p1.equals(p4));
    Assert.assertFalse(p1.equals(new Object()));
  }

  public void testHashCode() {
    Pair<Integer,Integer> p1 = new Pair<Integer,Integer>(42, 91);
    Pair<Integer,Integer> p2 = new Pair<Integer,Integer>(42, 91);
    Pair<Integer,Integer> p3 = new Pair<Integer,Integer>(43, 91);
    Pair<Integer,Integer> p4 = new Pair<Integer,Integer>(42, 92);
    Assert.assertEquals(p1.hashCode(), p1.hashCode());
    Assert.assertEquals(p1.hashCode(), p2.hashCode());
    Assert.assertTrue(p1.hashCode() != p3.hashCode());
    Assert.assertTrue(p1.hashCode() != p4.hashCode());
  }

  public void testCompareTo() {
    Pair<Integer,Integer> p1 = new Pair<Integer,Integer>(42, 91);
    Pair<Integer,Integer> p2 = new Pair<Integer,Integer>(42, 91);
    Pair<Integer,Integer> p3 = new Pair<Integer,Integer>(43, 91);
    Pair<Integer,Integer> p4 = new Pair<Integer,Integer>(42, 92);
    Assert.assertTrue(0 == p1.compareTo(p1));
    Assert.assertTrue(0 == p1.compareTo(p2));
    Assert.assertTrue(0 >  p1.compareTo(p3));
    Assert.assertTrue(0 >  p1.compareTo(p4));
    Assert.assertTrue(0 == p2.compareTo(p1));
    Assert.assertTrue(0 <  p3.compareTo(p1));
    Assert.assertTrue(0 <  p4.compareTo(p1));
    // Using generic types, the following will not compile.
    // try {
    //   p1.compareTo(new Object());
    //   // compareTo should have thrown exception
    //   Assert.fail();
    // } catch (ClassCastException e) { }
  }
}
