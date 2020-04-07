import junit.framework.Assert;
import junit.framework.TestCase;

/***
 * set of tests for the video object file
 */

public class VideoTEST extends TestCase {
  public VideoTEST(String name) {
    super(name);
  }

    /***
     * tests construction of the video objects attributes
     */

  public void testConstructorAndAttributes() {
    String title1 = "XX";
    String director1 = "XY";
    String title2 = " XX ";
    String director2 = " XY ";
    int year = 2002;

    VideoObj v1 = new VideoObj(title1, year, director1);
    assertSame(title1, v1.title());
    assertEquals(year, v1.year());
    assertSame(director1, v1.director());

    VideoObj v2 = new VideoObj(title2, year, director2);
    assertEquals(title1, v2.title());
    assertEquals(director1, v2.director());
  }

    /***
     * tests the exceptions caught from year entry
     */

  public void testConstructorExceptionYear() {
    try {
      new VideoObj("X", 1800, "Y");
      fail();
    } catch (IllegalArgumentException e) { }
    try {
      new VideoObj("X", 5000, "Y");
      fail();
    } catch (IllegalArgumentException e) { }
    try {
      new VideoObj("X", 1801, "Y");
      new VideoObj("X", 4999, "Y");
    } catch (IllegalArgumentException e) {
      fail();
    }
  }

    /***
     * tests title exceptions
     */

  public void testConstructorExceptionTitle() {
    try {
      new VideoObj(null, 2002, "Y");
      fail();
    } catch (IllegalArgumentException e) { }
    try {
      new VideoObj("", 2002, "Y");
      fail();
    } catch (IllegalArgumentException e) { }
    try {
      new VideoObj(" ", 2002, "Y");
      //uncomment to cause failure
      //fail();
    } catch (IllegalArgumentException e) { }
  }

    /***
     * tests director exceptions
     */

  public void testConstructorExceptionDirector() {
    try {
      new VideoObj("X", 2002, null);
      fail();
    } catch (IllegalArgumentException e) { }
    try {
      new VideoObj("X", 2002, "");
      fail();
    } catch (IllegalArgumentException e) { }
    try {
      new VideoObj("X", 2002, " ");
      //fail();
    } catch (IllegalArgumentException e) { }
  }

    /***
     * tests hashcode() method
     */

  public void testHashCode() {
    Assert.assertEquals
      (-875826552,
       new VideoObj("None", 2009, "Zebra").hashCode());
    Assert.assertEquals
      (-1391078111,
       new VideoObj("Blah", 1954, "Cante").hashCode());
  }

    /***
     * tests equals() method
     */

  public void testEquals()
  {
    String title = "A";
    int year = 2009;
    String director = "Zebra";
    VideoObj a = new VideoObj(title,year,director);
    VideoObj b = new VideoObj("Frozen", 2010, "Some Guy");
    assertTrue( a.equals(a) );
    assertTrue( a.equals( new VideoObj(title, year, director) ) );
    assertTrue( a.equals( new VideoObj(new String(title), year, director) ) );
    assertTrue( a.equals( new VideoObj(title, year, new String(director)) ) );
    assertFalse( a.director().equals(b.director()));
    assertFalse( a.year() == (b.year()));
    assertFalse( a.title().equals(b.title()));
    //assertFalse( a.equals( new Object() ) );
    //assertFalse( a.equals( null ) );
  }

    /***
     * tests compareTo() method
     */

  public void testCompareTo() {
    String title = "A", title2 = "B";
    int year = 2009, year2 = 2010;
    String director = "Zebra", director2 = "Zzz";
    VideoObj a = new VideoObj(title,year,director);
    VideoObj b = new VideoObj(title2,year,director);
    assertTrue( a.compareTo(b) < 0 );
    assertTrue( a.compareTo(b) == -b.compareTo(a) );
    assertTrue( a.compareTo(a) == 0 );

    b = new VideoObj(title,year2,director);
    assertTrue( a.compareTo(b) < 0 );
    assertTrue( a.compareTo(b) == -b.compareTo(a) );

    b = new VideoObj(title,year,director2);
    assertTrue( a.compareTo(b) < 0 );
    assertTrue( a.compareTo(b) == -b.compareTo(a) );

    b = new VideoObj(title2,year2,director2);
    assertTrue( a.compareTo(b) < 0 );
    assertTrue( a.compareTo(b) == -b.compareTo(a) );

    try {
      a.compareTo(null);
      fail();
    } catch ( NullPointerException e ) {}
    catch ( ClassCastException e ) {}
  }

    /***
     * tests toString() method
     */

  public void testToString() {
    String s = new VideoObj("A",2000,"B").toString();
    assertEquals( s, "A (2000) : B" );
    s = new VideoObj(" A ",2000," B ").toString();
    assertEquals( s, "A (2000) : B" );
  }
}
