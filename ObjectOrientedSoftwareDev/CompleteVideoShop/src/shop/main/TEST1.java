package shop.main;

//import junit.framework.Assert;
import junit.framework.TestCase;
import shop.command.Command;
import shop.data.Data;
import shop.data.Record;
import shop.data.Video;
import shop.data.Inventory;
import org.junit.Assert;
import static java.util.Comparator.comparing;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;


public class TEST1 extends TestCase {
  private Inventory _inventory = Data.newInventory();
  public TEST1(String name) {
    super(name);
  }
  private void check(Video v, int numOwned, int numOut, int numRentals) {
    Record r = _inventory.get(v);
    assertEquals(numOwned, r.numOwned());
    assertEquals(numOut, r.numOut());
    assertEquals(numRentals, r.numRentals());
  }
    
  public void test100() {
    Command clearCmd = Data.newClearCmd(_inventory);
    clearCmd.run();
    Video v1 = Data.newVideo("Title1", 2000, "Director1");
    assertEquals(0, _inventory.size());
    assertTrue(Data.newAddCmd(_inventory, v1, 5).run());
    assertEquals(1, _inventory.size());
    assertTrue(Data.newAddCmd(_inventory, v1, 5).run());
    assertEquals(1, _inventory.size());
    check(v1,10,0,0);
  }
  
  private void expect(Video v, String s) {
	    assertEquals(s,_inventory.get(v).toString());
	  }
  private void expect(Record r, String s) {
	    assertEquals(s,r.toString());
	  }
  public void testVideoConstructor() {
	  String title0 = "TitleSuper";
	  String title1 = "BB";
	  String title2 = "CC";
	  String director0 = "OO1";
	  String director1 = "dd1";
	  String director2 = "dd1";
	  int year = 2002;
	  
	  Video v0 = Data.newVideo(title0, year, director0);
	  assertSame(title0, v0.title());
	  assertEquals(year, v0.year());
	  assertSame(director0, v0.director());

	  Video v3 = Data.newVideo(title1, year, director1);
	  assertSame(title1, v3.title());
	  assertEquals(year, v3.year());
	  assertSame(director1, v3.director());
			    
  }
  
  public void testYearRange() {
	    try {
	    	Data.newVideo("X", 1800, "Y");
	        fail();
	      } catch (IllegalArgumentException e) { }
	      try {
	    	  Data.newVideo("X", 5000, "Y");
	        fail();
	      } catch (IllegalArgumentException e) { }
	      try {
	    	  Data.newVideo("X", 1801, "Y");
	        Data.newVideo("X", 4999, "Y");
	      } catch (IllegalArgumentException e) {
	        fail();
	      }
	    
  }
  
  public void testTitleProperties() {
	   try {
		   Data.newVideo(null, 2002, "Y");
		      fail();
		    } catch (IllegalArgumentException e) { }
		    try {
		    	Data.newVideo("", 2002, "Y");
		      fail();
		    } catch (IllegalArgumentException e) { }
		    try {
		    	Data.newVideo(" ", 2002, "Y");
		      fail();
		    } catch (IllegalArgumentException e) { }
		  }
  
  public void testInOut() {
	   Command clearCmd = Data.newClearCmd(_inventory);
	    clearCmd.run();

	    Video v1 = Data.newVideo("Title1", 2000, "Director1");
	    assertEquals(0, _inventory.size());
	    assertTrue(Data.newAddCmd(_inventory, v1, 5).run());
	    assertEquals(1, _inventory.size());
	    Video v2 = Data.newVideo("Title2", 2002, "Director2");
	    assertTrue(Data.newAddCmd(_inventory, v2, 5).run());
	    Video v3 = Data.newVideo("Title3", 2003, "Director3");
	    assertTrue(Data.newAddCmd(_inventory, v3, 5).run());
	    assertTrue(Data.newOutCmd(_inventory, v1).run());
	    expect(v1,"Title1 (2000) : Director1   [5,1,1]");
	    assertTrue(Data.newInCmd(_inventory, v1).run());
	    expect(v1,"Title1 (2000) : Director1   [5,0,1]");

	  
	  
  }
  
  public void testHashCode() {
	   assertEquals
	     (-875826552,
	    		 Data.newVideo("None", 2009, "Zebra").hashCode());
	   assertEquals
	      (-1391078111,
	    		  Data.newVideo("Blah", 1954, "Cante").hashCode());
	   System.out.println("HashCode: " +Data.newVideo("Blah", 1954, "Cante").hashCode());
	   
  }
  
  public void testEquals() { 
	    String title = "A";
	    int year = 2009;
	    String director = "Zebra";
	    Video a = Data.newVideo(title,year,director);
	    assertTrue( a.equals(a) );
	    assertTrue( a.equals( Data.newVideo(title, year, director) ) );
	    assertFalse( a.equals( Data.newVideo(title, year+1, director) ) );
	    assertFalse( a.equals( Data.newVideo(title, year, director+"1") ) );
	    assertFalse( a.equals( new Object() ) );
	    assertFalse( a.equals( null ) );
	  }
  
  public void testCompareTo() { 
	    String title = "A", title2 = "B";
	    int year = 2009, year2 = 2010;
	    String director = "Zebra", director2 = "Zzz";
	    Video a = Data.newVideo(title,year,director);
	    Video b = Data.newVideo(title2,year,director);
	    assertTrue( a.compareTo(b) < 0 );
	    assertTrue( a.compareTo(b) == -b.compareTo(a) );
	    assertTrue( a.compareTo(a) == 0 );

	    b = Data.newVideo(title,year2,director);
	    assertTrue( a.compareTo(b) < 0 );
	    assertTrue( a.compareTo(b) == -b.compareTo(a) );

	    b = Data.newVideo(title,year,director2);
	    assertTrue( a.compareTo(b) == -b.compareTo(a) );

	    b = Data.newVideo(title2,year2,director2);
	    assertTrue( a.compareTo(b) == -b.compareTo(a) );
  }
	    
	    public void testToString() { 
	        String s = Data.newVideo("A",2000,"B").toString();
	        System.out.println("Print video s1: " + s);
	        assertEquals( s, "A (2000) : B  " );
	        s = Data.newVideo(" A ",2000," B ").toString();
	        System.out.println("Print video s2: " + s);
	        assertEquals( s, "A (2000) : B  " );
	      }

	    public void testToCollection() {
	    	   Command clearCmd = Data.newClearCmd(_inventory);
	    	    clearCmd.run();
 	    
	    	    Video v1 = Data.newVideo("Title1", 2000, "Director1");
	    	    assertEquals(0, _inventory.size());
	    	    assertTrue(Data.newAddCmd(_inventory, v1, 5).run());
	    	    assertEquals(1, _inventory.size());
	    	    Video v2 = Data.newVideo("Title2", 2002, "Director2");
	    	    assertTrue(Data.newAddCmd(_inventory, v2, 5).run());
	    	    Video v3 = Data.newVideo("Title3", 2003, "Director3");
	    	    assertTrue(Data.newAddCmd(_inventory, v3, 5).run());
	    	
	        List<Record> cdata = new ArrayList<>(_inventory.toCollection());
	    }

	    public void testIteratorComparator() {
	    	   Command clearCmd = Data.newClearCmd(_inventory);
	    	    clearCmd.run();
	    	    
	    	    Video v1 = Data.newVideo("Title1", 2000, "Director1");
	    	    assertEquals(0, _inventory.size());
	    	    assertTrue(Data.newAddCmd(_inventory, v1, 5).run());
	    	    assertEquals(1, _inventory.size());
	    	    Video v2 = Data.newVideo("Title2", 2002, "Director2");
	    	    assertTrue(Data.newAddCmd(_inventory, v2, 5).run());
	    	    Video v3 = Data.newVideo("Title3", 2003, "Director3");
	    	    assertTrue(Data.newAddCmd(_inventory, v3, 5).run());
	    	    assertTrue(Data.newAddCmd(_inventory, v2, 5).run());
	    	    Video v4 = Data.newVideo("Title4", 2004, "Director4");
	    	    assertTrue(Data.newAddCmd(_inventory, v4, 5).run());
	    	    
	    	    Comparator<Record> comparator2 = new Comparator<Record>() {
	                @Override
	                public int compare(Record v1, Record v2) {
	                    return v1.video().compareTo(v2.video());
	                }
	            };
	            Iterator<Record> vr2 = _inventory.iterator(comparator2);
		        while(vr2.hasNext()) {
		        	System.out.println("Print inventory IteratorComparator vr2: "+ vr2.next());
		        }	    	
	            
		        Comparator<Record> comparator = comparing(Record::video);
		        Iterator<Record> vr = _inventory.iterator(comparator);
		        HashSet<Record> sh = new HashSet<Record>(); 
		        Record vrv = null;
		        while(vr.hasNext()) {
		        	vrv = vr.next();
		        	sh.add(vrv);
		        	System.out.println("Print inventory IteratorComparator: "+ vrv);
		        	 assertEquals(4, _inventory.size());
		        }
		        System.out.println("Print HashSet vrv: "+ sh);
		        
 
			}

	    public void testIteratorHashSetComparator() {
	    	   Command clearCmd = Data.newClearCmd(_inventory);
	    	    clearCmd.run();
   	    
	    	    Video v1 = Data.newVideo("Title1", 2000, "Director1");
	    	    assertEquals(0, _inventory.size());
	    	    assertTrue(Data.newAddCmd(_inventory, v1, 5).run());
	    	    assertEquals(1, _inventory.size());
	    	    Video v2 = Data.newVideo("Title2", 2002, "Director2");
	    	    assertTrue(Data.newAddCmd(_inventory, v2, 5).run());
	    	    Video v3 = Data.newVideo("Title3", 2003, "Director3");
	    	    assertTrue(Data.newAddCmd(_inventory, v3, 5).run());
	    	    Video v4 = Data.newVideo("Title4", 2004, "Director4");
	    	    assertTrue(Data.newAddCmd(_inventory, v4, 5).run());
		        //Test HashSet/InventorySet

				HashSet<String> h = new HashSet<String>(); 

				// Adding elements into HashSet usind add() 
				h.add("Title1 (2000) : Director1  [5,0,0]"); 
				h.add("Title2 (2002) : Director2  [5,0,0]"); 
				h.add("Title3 (2003) : Director3  [5,0,0]"); 
				h.add("Title4 (2004) : Director4  [5,0,0]");// adding duplicate elements 

				// Displaying the HashSet 
				System.out.println("IteratorHashSetComparator - Print HashSet:  " + h); 
 
				String hv = null;
				Iterator<String> hit = h.iterator();
				Record hldr = null;
				while (hit.hasNext()) {
					hv = hit.next();
					System.out.println("IteratorHashSetComparator- Print hashset h hv.toString(): " +hv);
					if (hv.toString().contains("Title1 (2000) : Director1  ")) {
						hldr =_inventory.get(v1);
						if(hldr.toString().contains(hv.toString())) {
							assertTrue(hldr.toString().contains(hv.toString()));
							hit.remove();
							System.out.println("IteratorHashSetComparator - HashhSet removed: " + hv.toString());
						}

					}else if(hv.toString().contains("Title2 (2002) : Director2  ")){
						assertTrue(hv.toString().contains("Title2 (2002) : Director2  "));
						hldr =_inventory.get(v2);
						if(hldr.toString().contains(hv.toString())) {
						assertTrue(hldr.toString().contains(hv.toString()));
						hit.remove();
						System.out.println("IteratorHashSetComparator - HashhSet removed: " + hv.toString());
						}

					}else if(hv.toString().contains("Title3 (2003) : Director3  ")){
						assertTrue(hv.toString().contains("Title3 (2003) : Director3  "));
						hldr =_inventory.get(v3);
						if(hldr.toString().contains(hv.toString())) {
							assertTrue(hldr.toString().contains(hv.toString()));
							hit.remove();
							System.out.println("IteratorHashSetComparator - HashhSet removed: " + hv.toString());
						}

					}else if(hv.toString().contains("Title4 (2004) : Director4  ")){
						assertTrue(hv.toString().contains("Title4 (2004) : Director4  "));
						hldr =_inventory.get(v4);
						if(hldr.toString().contains(hv.toString())) {
							assertTrue(hldr.toString().contains(hv.toString()));
							hit.remove();
						System.out.println("IteratorHashSetComparator - HashhSet removed: " + hv.toString());
						}

					}
					
					
			    }
				System.out.println("The size of the final size of HashSet h is: " + h.size()); 
				assertEquals(4, h.size());
	    	
	    }

	    public void testIteratorListComparator() {
	    	   Command clearCmd = Data.newClearCmd(_inventory);
	    	    clearCmd.run();
 	    
	    	    Video v1 = Data.newVideo("Title1", 2000, "Director1");
	    	    assertEquals(0, _inventory.size());
	    	    assertTrue(Data.newAddCmd(_inventory, v1, 5).run());
	    	    assertEquals(1, _inventory.size());
	    	    Video v2 = Data.newVideo("Title2", 2002, "Director2");
	    	    assertTrue(Data.newAddCmd(_inventory, v2, 5).run());
	    	    Video v3 = Data.newVideo("Title3", 2003, "Director3");
	    	    assertTrue(Data.newAddCmd(_inventory, v3, 5).run());
	    	    Video v4 = Data.newVideo("Title4", 2004, "Director4");
	    	    assertTrue(Data.newAddCmd(_inventory, v4, 5).run());
	    	    
	    	List<String> ldata = new ArrayList<String>();
			ldata.add("Title1 (2000) : Director1  [5,0,0]"); 
			ldata.add("Title2 (2002) : Director2  [5,0,0]"); 
			ldata.add("Title3 (2003) : Director3  [5,0,0]"); 
			ldata.add("Title4 (2004) : Director4  [5,0,0]");
			
	        Iterator<String> lit =  ldata.iterator(); 
	        
	        List<Record> cdata = new ArrayList<Record>(_inventory.toCollection());
	        assertTrue(cdata instanceof Object);
	        
	        Collections.sort(cdata, new VideoComparator());
	        Iterator<Record> cit =  cdata.iterator();
	        String hldlit = null;
	        Record hldcit = null;
	        while(lit.hasNext()) {
	        	hldlit =  lit.next();
	        	assertNotNull(hldlit);
	        	System.out.println("IteratorListComparator - Print inventory : "+ hldlit);
	        	if(cit.hasNext()) {
	        		hldcit = cit.next();
	        		assertNotNull(hldcit);
	        		System.out.println("IteratorListComparator - Print inventory : "+ hldcit);
	        	}
	        	if(hldcit.toString().contains(hldlit)) {
	        		System.out.println("IteratorListComparator - Matched on lit/cit : "+ hldcit);
	        		assertEquals(hldcit.toString(), hldlit);
	        		lit.remove();
	        	}
	        }
	        System.out.println("IteratorListComparator - size of List: " + ldata.size());
	        assertEquals(4, ldata.size());
	    }    	 	    	
	    
	    
	    public void testIterator() {
	    	   Command clearCmd = Data.newClearCmd(_inventory);
	    	    clearCmd.run();
    	    
	    	    Video v1 = Data.newVideo("Title1", 2000, "Director1");
	    	    assertEquals(0, _inventory.size());
	    	    assertTrue(Data.newAddCmd(_inventory, v1, 5).run());
	    	    assertEquals(1, _inventory.size());
	    	    Video v2 = Data.newVideo("Title2", 2002, "Director2");
	    	    assertTrue(Data.newAddCmd(_inventory, v2, 5).run());
	    	    Video v3 = Data.newVideo("Title3", 2003, "Director3");
	    	    assertTrue(Data.newAddCmd(_inventory, v3, 5).run());
	    	    Video v4 = Data.newVideo("Title4", 2004, "Director4");
	    	    assertTrue(Data.newAddCmd(_inventory, v4, 5).run());
	    	    
	        Iterator<Record> vr =  _inventory.iterator();
	        assertNotNull(vr);
	        Record vrit = null;
	        while(vr.hasNext()) {
	        	vrit = vr.next();
	        }
	    }

  public void test1() {

    Command clearCmd = Data.newClearCmd(_inventory);
    clearCmd.run();
    
    Video v1 = Data.newVideo("Title1", 2000, "Director1");
    assertEquals(0, _inventory.size());
    assertTrue(Data.newAddCmd(_inventory, v1, 5).run());
    assertEquals(1, _inventory.size());
    List<Record> cdata = new ArrayList<>(_inventory.toCollection());
    System.out.println("Print inventory cdata: "+ cdata);
    
    /**
     * Data is the Factory method because it creates.  In this case, we want to add
     * a video to the inventory.  We will invoke Data.newAddCmd() which will pass, the inventory data store
     * we want change, the video properties and the we want to add or remove.  The newAddCmd() will execute
     * instantiate an instance of the class CmdAdd.  This class does not have an add/remove method.  However, 
     * it can access InventorySet.addNumOwned(). This method in the past had a return type of "void".
     * The instance of CmdAdd is looking for a boolean return type.  So, may be we should change the void to
     * boolean(use the primitive) 
     */
    assertTrue(Data.newAddCmd(_inventory, v1, 5).run());
    assertEquals(1, _inventory.size());
    expect(v1,"Title1 (2000) : Director1   [10,0,0]");  //my code
    /**
     * An interface, Inventory, is a type and it is public; which means it is accessible by any other object.  
     * Data is also public and newInventory is a method of Data
     */
  }
  
  public void test11() {     //**********************test is original test1
	    Command clearCmd = Data.newClearCmd(_inventory);
	    clearCmd.run();
	    
	    Video v1 = Data.newVideo("Title1", 2000, "Director1");
	    assertEquals(0, _inventory.size());
	    assertTrue(Data.newAddCmd(_inventory, v1, 5).run());
	    assertEquals(1, _inventory.size());
	    assertTrue(Data.newAddCmd(_inventory, v1, 5).run());
	    assertEquals(1, _inventory.size());
	    expect(v1,"Title1 (2000) : Director1   [10,0,0]");  //my code
	    Video v2 = Data.newVideo("Title2", 2001, "Director2");
	    assertTrue(Data.newAddCmd(_inventory, v2, 1).run());
	    assertEquals(2, _inventory.size());
	    expect(v2,"Title2 (2001) : Director2   [1,0,0]");  //my code
	    assertEquals(2, _inventory.size());
	    assertTrue(Data.newOutCmd(_inventory, v2).run());
	    assertTrue(Data.newInCmd(_inventory, v2).run());
	    assertTrue(Data.newAddCmd(_inventory, v2, -1).run());
	    assertEquals(1, _inventory.size());
	    expect(v1,"Title1 (2000) : Director1   [10,0,0]");
	    Command outCmd = Data.newOutCmd(_inventory, v1);
	    assertTrue(outCmd.run());
	    assertTrue(outCmd.run());
	    assertTrue(outCmd.run());
	    assertTrue(outCmd.run());
	    expect(v1,"Title1 (2000) : Director1   [10,4,4]");  //my code
	    assertTrue(Data.newInCmd(_inventory, v1).run());
	    expect(v1,"Title1 (2000) : Director1   [10,3,4]");  //my code
	    assertTrue(Data.newAddCmd(_inventory, v2, 5).run());
	    assertEquals(2, _inventory.size());
	    expect(v2,"Title2 (2001) : Director2   [5,0,0]");

	    Iterator<Record> it = _inventory.iterator(new Comparator<Record>() {
	        public int compare (Record r1, Record r2) {
	          return r2.numRentals() - r1.numRentals();
	        }
	      });
	    expect(it.next(),"Title1 (2000) : Director1   [10,3,4]");  //my code
	    expect(it.next(),"Title2 (2001) : Director2   [5,0,0]");  //my code
	    assertFalse(it.hasNext());
	  }
}

class VideoComparator implements Comparator<Record>{
	public int compare (Record r1, Record r2) {
		return r1.video().compareTo(r2.video());
	}
}
