package shop.data;

/***
 * <p>Public view of an inventory record.</p>
 *
 * <p>Records are mutable, but cannot be changed outside the package.</p>
 * 
 * <p>This interface should not be implemented outside the package.</p>
 * 
 * <p><code>equals</code> and <code>hashCode</code> delegate to the
 * underlying Video object.</p>
 * @see Data
 */
public interface Record {
  /***
   *
   *
   *
   * @return the video
   */
  public Video video();
  /***
   * @return the number of copies of the video that are in the inventory.
   * <p><b>Invariant:</b> <code>numOwned() greater than 0</code>.</p>
   */
  public int numOwned();
  /***
   * @return the number of copies of the video that are currently checked out.
   * <p><b>Invariant:</b> <code>numOut() less than or equal to numOwned()</code>.</p>
   */
  public int numOut();
  /***
   * @return the total number of times this video has ever been checked out.
   * <p><b>Invariant:</b> <code>numRentals() greater than or equal to numOut()</code>.</p>
   */
  public int numRentals();
  /***
   *  @return a string representation of the object in the following format:
   * <code>"video [numOwned,numOut,numRentals]"</code>.
   */
  public String toString();
}
