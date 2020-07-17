package shop.data;

import java.util.Map;
import java.util.Stack;
import java.util.HashMap;
import java.util.Comparator;
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import shop.command.Command;
import shop.command.UndoableCommand;
import shop.command.CommandHistory;
import shop.command.CommandHistoryFactory;

/**
 * Implementation of Inventory interface.
 * @see Data
 */
final class InventorySet implements Inventory {
  private Map<Video,Record> _data;
  private final CommandHistory _history;

  InventorySet() {
    // TODO
    _data = new HashMap<Video,Record>();
   _history= CommandHistoryFactory.newCommandHistory();
     
  }

  /**
   * If <code>record</code> is null, then delete record for <code>video</code>;
   * otherwise replace record for <code>video</code>.
   */
  void replaceEntry(Video video, Record record) {
    _data.remove(video);
    if (record != null)
      _data.put(video,((RecordObj)record).copy());
  }

  /**
   * Overwrite the map.
   */
  void replaceMap(Map<Video,Record> data) {
    _data = data;
  }

  public int size() {
    // TODO
    return _data.size();
  }

  public Record get(Video v) {
    // TODO
    return _data.get(v);
  }

  public Iterator<Record> iterator() {
    return Collections.unmodifiableCollection(_data.values()).iterator();
  }
  
  public Collection toCollection() {
		  	 List<Record> _cdata = new ArrayList<Record>(_data.values());
		  	 
		  	Collections.sort(_cdata, new Comparator<Record>() {
				@Override
				public int compare(Record r1, Record r2) {
					return r1.video().compareTo(r2.video());
				}
				
			});
				return _cdata;
}

  public Iterator<Record> iterator(Comparator<Record> comparator) {
    // TODO
	  
	  	 List<Record> _cdata = new ArrayList<Record>(_data.values());
	  	 
	  	 
	  	Collections.sort(_cdata, comparator);
	  	Iterator<Record> cit = _cdata.iterator();;

	  	
	  	System.out.println("Print iteratorWithComparator from InventorySet" + _cdata);
	  	 


			return cit;


  }

  /**
   * Add or remove copies of a video from the inventory.
   * If a video record is not already present (and change is
   * positive), a record is created. 
   * If a record is already present, <code>numOwned</code> is
   * modified using <code>change</code>.
   * If <code>change</code> brings the number of copies to be zero,
   * the record is removed from the inventory.
   * @param video the video to be added.
   * @param change the number of copies to add (or remove if negative).
   * @return A copy of the previous record for this video (if any)
   * @throws IllegalArgumentException if video null, change is zero, if attempting to remove more copies than are owned, or if attempting to remove copies that are checked out. 
   */
  Record addNumOwned(Video video, int change) {
    if (video == null || change == 0)
      throw new IllegalArgumentException();
    
    RecordObj r = (RecordObj) _data.get(video);
    if (r == null && change < 1) {
      throw new IllegalArgumentException();
    } else if (r == null) {
      _data.put(video, new RecordObj(video, change, 0, 0));
    } else if (r.numOwned+change < r.numOut) {
      throw new IllegalArgumentException();
    } else if (r.numOwned+change < 1) {
      _data.remove(video);
    } else {
      _data.put(video, new RecordObj(video, r.numOwned + change, r.numOut, r.numRentals));
    }
    return r;
  }

  /**
   * Check out a video.
   * @param video the video to be checked out.
   * @return A copy of the previous record for this video
   * @throws IllegalArgumentException if video has no record or numOut
   * equals numOwned.
   */
  Record checkOut(Video video) {
    // TODO
//	  System.out.println("video: "+ video);
//	  System.out.println("video.hashCode(): "+video.hashCode());
//	  System.out.println("_data.get(video): "+ (Record)_data.get(video));
//	  Collection ctest = toCollection();

	  if(_data.containsKey(video)) {
		  Record holdv1 = (Record)_data.get(video);
		  int rnumOwned = 0;
		  int rnumOut = 0;
		  int rnumRentals = 0;
		  if(holdv1.numOwned() > 0 && holdv1.numOut() < holdv1.numOwned()) {
			rnumOwned = holdv1.numOwned();
			rnumOut = holdv1.numOut() +1;
			rnumRentals = holdv1.numRentals() +1;
		  	Record r0 = new RecordObj(video, rnumOwned,rnumOut,rnumRentals);
		  	_data.replace(video, r0);
		  	return holdv1;
		  }
	  }	
	  return null;  //when video doesn't exist.
  }
  
  /**
   * Check in a video.
   * @param video the video to be checked in.
   * @return A copy of the previous record for this video
   * @throws IllegalArgumentException if video has no record or numOut
   * non-positive.
   */
  Record checkIn(Video video) {
    // TODO

	  int rnumOwned = 0;
	  int rnumOut = 0;
	  int rnumRentals = 0;
	  if(_data.containsKey(video)) {
		  Record holdv1 = (Record)_data.get(video);
		  if(holdv1.numOwned() > 0 && holdv1.numOut() > 0) {
			  rnumOwned = holdv1.numOwned();
			  rnumOut = holdv1.numOut() - 1;
			  rnumRentals = holdv1.numRentals();
			  Record r0 = new RecordObj(video, rnumOwned,rnumOut,rnumRentals);
			  _data.replace(video, r0);
//			  return r0;
			  return holdv1;
//			  traverseMap(_idata);
		  }
	  }
	  return null;//when video doesn't exist.
  }
  
  /**
   * Remove all records from the inventory.
   * @return A copy of the previous inventory as a Map
   */
  Map<Video,Record> clear() {
    // TODO
	  Map<Video,Record> _hata;
	  _hata = new HashMap<Video,Record>();
	  _hata.putAll(_data);
	  _data.clear();
    return  _hata;
  }

  /**
   * Return a reference to the history.
   */
  CommandHistory getHistory() {
    // TODO
    return _history;
  }
  
  public String toString() {
    StringBuilder buffer = new StringBuilder();
    buffer.append("Database:\n");
    Iterator i = _data.values().iterator();
    while (i.hasNext()) {
      buffer.append("  ");
      buffer.append(i.next());
      buffer.append("\n");
    }
    return buffer.toString();
  }


  /**
   * Implementation of Record interface.
   *
   * <p>This is a utility class for Inventory.  Fields are immutable and
   * package-private.</p>
   *
   * <p><b>Class Invariant:</b> No two instances may reference the same Video.</p>
   *
   * @see Record
   */
  private static final class RecordObj implements Record {
    final Video video; // the video
    final int numOwned;   // copies owned
    final int numOut;     // copies currently rented
    final int numRentals; // total times video has been rented
    
    RecordObj(Video video, int numOwned, int numOut, int numRentals) {
      this.video = video;
      this.numOwned = numOwned;
      this.numOut = numOut;
      this.numRentals = numRentals;
    }
    RecordObj copy() {
      return new RecordObj(video, numOwned, numOut, numRentals);
    }
    public Video video() {
      return video;
    }
    public int numOwned() {
      return numOwned;
    }
    public int numOut() {
      return numOut;
    }
    public int numRentals() {
      return numRentals;
    }
    public boolean equals(Object thatObject) {
      return video.equals(((Record)thatObject).video());
    }
    public int hashCode() {
      return video.hashCode();
    }
    public String toString() {
      StringBuilder buffer = new StringBuilder();
      buffer.append(video);
      buffer.append(" [");
      buffer.append(numOwned);
      buffer.append(",");
      buffer.append(numOut);
      buffer.append(",");
      buffer.append(numRentals);
      buffer.append("]");
      return buffer.toString();
    }
  }
}
