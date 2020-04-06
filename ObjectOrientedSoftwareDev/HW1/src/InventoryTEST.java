import junit.framework.Assert;
import junit.framework.TestCase;
import java.util.Iterator;
import java.util.Set;
import java.util.HashSet;

// TODO: complete the tests
public class InventoryTEST extends TestCase {
  public InventoryTEST(String name) {
    super(name);
  }

  InventorySet s = new InventorySet();
  final VideoObj v1 = new VideoObj( "A", 2000, "B" );
  final VideoObj v2 = new VideoObj( "B", 2000, "B" );

  public void testSize() {
                                 Assert.assertEquals( 0, s.size() );
          s.addNumOwned(v1,  1); Assert.assertEquals( 1, s.size() );
          s.addNumOwned(v1,  2); Assert.assertEquals( 1, s.size() );
          s.addNumOwned(v2,  1); Assert.assertEquals( 2, s.size() );
          s.addNumOwned(v2, -1); Assert.assertEquals( 1, s.size() );
          s.addNumOwned(v1, -3); Assert.assertEquals( 0, s.size() );
    try { s.addNumOwned(v1, -3); } catch ( IllegalArgumentException e ) {}
                                 Assert.assertEquals( 0, s.size() );
  }

  public void testAddNumOwned() {
                                    Assert.assertEquals( 0, s.size() );
          s.addNumOwned(v1, 1);     Assert.assertEquals( v1, s.get(v1).video );
                                    Assert.assertEquals( 1, s.get(v1).numOwned );
    // TODO: complete this test
  }

  public void testCheckOutCheckIn() {
    // TODO
  }

  public void testClear() {
    // TODO
  }

  public void testGet() {
    // TODO
  }

  public void testToCollection() {
    // Be sure to test that changing records in the returned
    // collection does not change the original records in the
    // inventory.  ToCollection should return a COPY of the records,
    // not the records themselves.
    // TODO
  }
}
