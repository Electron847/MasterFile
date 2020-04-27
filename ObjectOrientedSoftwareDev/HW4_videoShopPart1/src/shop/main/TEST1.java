package shop.main;

import junit.framework.Assert;
import junit.framework.TestCase;
import shop.command.Command;
import shop.data.Data;
import shop.data.Record;
import shop.data.Video;
import shop.data.Inventory;
import java.util.Iterator;

// TODO:
// write an integration test that tests the data classes.
// add in some videos, check out, check in, delete videos, etc.
// check that errors are reported when necessary.
// check that things are going as expected.
@SuppressWarnings("deprecation")
public class TEST1 extends TestCase {
  private Inventory _inventory = Data.newInventory();
  public TEST1(String name) {
    super(name);
  }
  private void expect(Video v, String s) {
    Assert.assertEquals(s,_inventory.get(v).toString());
  }
  private void expect(Record r, String s) {
    Assert.assertEquals(s,r.toString());
  }
  public void test1() {
    Command clearCmd = Data.newClearCmd(_inventory);
    clearCmd.run();

    Video v1 = Data.newVideo("Title1", 2000, "Director1");
    Assert.assertEquals(0, _inventory.size());
    Assert.assertTrue(Data.newAddCmd(_inventory, v1, 5).run());
    Assert.assertEquals(1, _inventory.size());
    Assert.assertTrue(Data.newAddCmd(_inventory, v1, 5).run());
    Assert.assertEquals(1, _inventory.size());
//     System.out.println(_inventory.get(v1));
    expect(v1,"Title1 (2000) : Director1 [10,0,0]");

    Video v2 = Data.newVideo("Title2", 2001, "Director2");
    Assert.assertTrue(Data.newAddCmd(_inventory, v2, 1).run());
    Assert.assertEquals(2, _inventory.size());
    expect(v2,"Title2 (2001) : Director2 [1,0,0]");

    Assert.assertFalse(Data.newAddCmd(_inventory, null, 5).run());
    Assert.assertEquals(2, _inventory.size());

    Assert.assertTrue(Data.newOutCmd(_inventory, v2).run());
    expect(v2,"Title2 (2001) : Director2 [1,1,1]");

    Assert.assertTrue(Data.newInCmd(_inventory, v2).run());
    expect(v2,"Title2 (2001) : Director2 [1,0,1]");

    Assert.assertTrue(Data.newAddCmd(_inventory, v2, -1).run());
    Assert.assertEquals(1, _inventory.size());
    expect(v1,"Title1 (2000) : Director1 [10,0,0]");

    Command outCmd = Data.newOutCmd(_inventory, v1);
    Assert.assertTrue(outCmd.run());
    Assert.assertTrue(outCmd.run());
    Assert.assertTrue(outCmd.run());
    Assert.assertTrue(outCmd.run());
    expect(v1,"Title1 (2000) : Director1 [10,4,4]");

    Assert.assertTrue(Data.newInCmd(_inventory, v1).run());
    expect(v1,"Title1 (2000) : Director1 [10,3,4]");

    Assert.assertTrue(Data.newAddCmd(_inventory, v2, 5).run());
    Assert.assertEquals(2, _inventory.size());
    expect(v2,"Title2 (2001) : Director2 [5,0,0]");
    expect(v1,"Title1 (2000) : Director1 [10,3,4]");

    Iterator<Record> it = _inventory.iterator(new java.util.Comparator<Record>() {
      public int compare (Record r1, Record r2) {
        return r2.numRentals() - r1.numRentals();
      }
    });
    expect(it.next(),"Title1 (2000) : Director1 [10,3,4]");
    expect(it.next(),"Title2 (2001) : Director2 [5,0,0]");
    Assert.assertFalse(it.hasNext());
  }
  public void test2(){
    Command clearCmd = Data.newClearCmd(_inventory);
    clearCmd.run();
    assert(_inventory.size()==0);

    Video video1 = Data.newVideo("Video1", 2001, "Director1");
    assert(0== _inventory.size());
    assert(Data.newAddCmd(_inventory, video1, 1).run());
    assert(1== _inventory.size());
    assert(Data.newAddCmd(_inventory, video1, 1).run());
    assert(1== _inventory.size());

    Video video2 = Data.newVideo("Video2", 2002, "Director2");
    assert(1== _inventory.size());
    assert(Data.newAddCmd(_inventory, video2, 2).run());
    assert(2== _inventory.size());

    Video video3 = Data.newVideo("Video3", 2003, "Director3");
    assert(2== _inventory.size());
    assert(Data.newAddCmd(_inventory, video3, 3).run());
    assert(3== _inventory.size());

    Video video4 = Data.newVideo("Video4", 2004, "Director4");
    assert(3== _inventory.size());
    assert(Data.newAddCmd(_inventory, video4, 4).run());
    assert(4== _inventory.size());

    clearCmd.run();
    assert(_inventory.size()==0);

    try{video1 = Data.newVideo(null, 2001, "Director1");}
    catch( IllegalArgumentException e ) {}
    assert(_inventory.size()==0);

    try{video1 = Data.newVideo("", 2001, "Director1");}
    catch( IllegalArgumentException e ) {}
    assert(_inventory.size()==0);

    try{video1 = Data.newVideo("     ", 2001, "Director1");}
    catch( IllegalArgumentException e ) {}
    assert(_inventory.size()==0);

    try{video1 = Data.newVideo("Video1", 1, "Director1");}
    catch( IllegalArgumentException e ) {}
    assert(_inventory.size()==0);

    try{video1 = Data.newVideo("Video1", 1111111, "Director1");}
    catch( IllegalArgumentException e ) {}
    assert(_inventory.size()==0);

    try{video1 = Data.newVideo("Video1", 2001, "");}
    catch( IllegalArgumentException e ) {}
    assert(_inventory.size()==0);

    try{video1 = Data.newVideo("Video1", 2001, "    ");}
    catch( IllegalArgumentException e ) {}
    assert(_inventory.size()==0);

    try{video1 = Data.newVideo("Video1", 2001, null);}
    catch( IllegalArgumentException e ) {}
    assert(_inventory.size()==0);

    video1 = Data.newVideo("Video1", 2001, "Director1");
    assert(0== _inventory.size());
    assert(Data.newAddCmd(_inventory, video1, 5).run());
    assert(1== _inventory.size());

    assert(1== _inventory.size());
    assert(Data.newOutCmd(_inventory, video1).run());
    assert(_inventory.get(video1).numOut()==1);
    assert(Data.newOutCmd(_inventory, video1).run());
    assert(_inventory.get(video1).numOut()==2);
    assert(Data.newOutCmd(_inventory, video1).run());
    assert(_inventory.get(video1).numOut()==3);
    assert(Data.newOutCmd(_inventory, video1).run());
    assert(_inventory.get(video1).numOut()==4);
    assert(Data.newOutCmd(_inventory, video1).run());
    assert(_inventory.get(video1).numOut()==5);
    assert!(Data.newOutCmd(_inventory, video1).run());
    assert(_inventory.get(video1).numOut()==5);

    assert(Data.newInCmd(_inventory, video1).run());
    assert(_inventory.get(video1).numOut()==4);
    assert(Data.newInCmd(_inventory, video1).run());
    assert(_inventory.get(video1).numOut()==3);
    assert(Data.newInCmd(_inventory, video1).run());
    assert(_inventory.get(video1).numOut()==2);
    assert(Data.newInCmd(_inventory, video1).run());
    assert(_inventory.get(video1).numOut()==1);
    assert(Data.newInCmd(_inventory, video1).run());
    assert(_inventory.get(video1).numOut()==0);
    assert!(Data.newInCmd(_inventory, video1).run());
    assert(_inventory.get(video1).numOut()==0);
  }
}
