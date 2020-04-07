import java.util.Map;
import java.util.HashMap;
import java.util.Collection;
import java.util.ArrayList;


final class InventorySet {
  private final Map<VideoObj,Record> data = new HashMap<VideoObj,Record>();

  InventorySet() { }

  /***
   * Return the number of Records.
   */


  public int size() {
    return data.size();
  }

  /***
   *  Return a copy of the record for a given Video; if not present, return <code>null</code>.
   */

  public Record get(VideoObj v)
  {
    Record r = data.get(v);
    if (r == null)
      return null;
    return r.copy();
  }

  /***
   * Return a copy of the records as a collection.
   * Neither the underlying collection, nor the actual records are returned.
   */
  public Collection<Record> toCollection()
  {
    // Recall that an ArrayList is a Collection.
    ArrayList<Record> result = new ArrayList<Record>(data.size());
    for (Record r : data.values()) {
      result.add(r.copy());
    }
    return result;
  }

  /***
   * Add or remove copies of a video from the inventory.
   * If a video record is not already present (and change is
   * positive), a record is created.
   * If a record is already present, <code>numOwned</code> is
   * modified using <code>change</code>.
   * If <code>change</code> brings the number of copies to be zero,
   * the record is removed from the inventory.
   * @param video the video to be added.
   * @param change the number of copies to add (or remove if negative).
   * @throws IllegalArgumentException if video null, change is zero,
   *  if attempting to remove more copies than are owned, or if
   *  attempting to remove copies that are checked out.
   * <p><b>Postcondition:</b> changes the record for the video</p>
   */
  public void addNumOwned(VideoObj video, int change)
  {
    if (video == null || change == 0)
      throw new IllegalArgumentException();

    Record r = data.get(video);
    if (r == null && change < 1) {
      throw new IllegalArgumentException();
    } else if (r == null) {
      data.put(video, new Record(video, change, 0, 0));
    } else if (r.numOwned+change < r.numOut) {
      throw new IllegalArgumentException();
    } else if (r.numOwned+change < 1) {
      data.remove(video);
    } else {
      r.numOwned += change;
    }
  }

  /***
   * Check out a video.
   * @param video the video to be checked out.
   * @throws IllegalArgumentException if video has no record or numOut
   * equals numOwned.
   * <p><b>Postcondition:</b> changes the record for the video</p>
   */

  public void checkOut(VideoObj video)
  {
    Record r = data.get(video);
    if (r == null || r.numOut == r.numOwned)
      throw new IllegalArgumentException();
    r.numOut++;
    r.numRentals++;
  }

  /***
   * Check in a video.
   * @param video the video to be checked in.
   * @throws IllegalArgumentException if video has no record or numOut
   * non-positive.
   * <p><b>Postcondition:</b> changes the record for the video</p>
   */
  public void checkIn(VideoObj video)
  {
    Record r = data.get(video);
    if (r == null || r.numOut < 0) {
      throw new IllegalArgumentException("Video has no record or positive numOut");
    }
    r.numOut--;
  }

  /***
   * Remove all records from the inventory.
   * <p><b>Postcondition:</b> <code>size() == 0</code></p>
   */
  public void clear() {
    data.clear();
  }

  /***
   * Return the contents of the inventory as a string.
   */
  public String toString() {
    StringBuilder buffer = new StringBuilder();
    buffer.append("Database:\n");
    for (Record r : data.values()) {
      buffer.append("  ");
      buffer.append(r);
      buffer.append("\n");
    }
    return buffer.toString();
  }
}