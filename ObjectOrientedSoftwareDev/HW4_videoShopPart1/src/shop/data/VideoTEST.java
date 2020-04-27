package shop.data;

import org.junit.Test;

import junit.framework.Assert;
import junit.framework.TestCase;

@SuppressWarnings("deprecation")
public class VideoTEST extends TestCase {
  public VideoTEST(String name) {
    super(name);
  }
  public void testHashCode() {
    Assert.assertEquals
            (-875826552,
                    new VideoObj("None", 2009, "Zebra").hashCode());
    Assert.assertEquals
            (-1391078111,
                    new VideoObj("Blah", 1954, "Cante").hashCode());
  }

  public void testEquals() {
    String title = "A";
    int year = 2009;
    String director = "Zebra";
    VideoObj a = new VideoObj(title,year,director);
    Assert.assertTrue( a.equals(a) );
    Assert.assertTrue( a.equals( new VideoObj(title, year, director) ) );
    Assert.assertTrue( a.equals( new VideoObj(new String(title), year, director) ) );
    Assert.assertTrue( a.equals( new VideoObj(title, year, new String(director)) ) );
    Assert.assertFalse( a.equals( new VideoObj(title+"1", year, director) ) );
    Assert.assertFalse( a.equals( new VideoObj(title, year+1, director) ) );
    Assert.assertFalse( a.equals( new VideoObj(title, year, director+"1") ) );
    Assert.assertFalse( a.equals( new Object() ) );
    Assert.assertFalse( a.equals( null ) );
  }

  public void testCompareTo() {
    String title = "A", title2 = "B";
    int year = 2009, year2 = 2010;
    String director = "Zebra", director2 = "Zzz";
    VideoObj a = new VideoObj(title,year,director);
    VideoObj b = new VideoObj(title2,year,director);
    Assert.assertTrue( a.compareTo(b) < 0 );
    Assert.assertTrue( a.compareTo(b) == -b.compareTo(a) );
    Assert.assertTrue( a.compareTo(a) == 0 );

    b = new VideoObj(title,year2,director);
    Assert.assertTrue( a.compareTo(b) < 0 );
    Assert.assertTrue( a.compareTo(b) == -b.compareTo(a) );

    b = new VideoObj(title,year,director2);
    Assert.assertTrue( a.compareTo(b) < 0 );
    Assert.assertTrue( a.compareTo(b) == -b.compareTo(a) );

    b = new VideoObj(title2,year2,director2);
    Assert.assertTrue( a.compareTo(b) < 0 );
    Assert.assertTrue( a.compareTo(b) == -b.compareTo(a) );

    try {
      a.compareTo(null);
      fail();
    } catch ( NullPointerException e ) {}
  }
  @Test
  public void testToString() {
    VideoObj obj = new VideoObj("Hello", 1999, "Hey");
    Object str = new String();
    str = obj.toString();
    Object str2 = new String();
    str2 = "Hello (1999) : Hey";
    assert (str.equals(str2));
    str2 = "Hello (1996) : Hey";
    assert !(str.equals(str2));
  }
}
