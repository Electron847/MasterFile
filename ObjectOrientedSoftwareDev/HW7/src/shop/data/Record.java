package shop.data;

/**
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
  /**
   * @return the video.
   * <p><b>Invariant:</b> <code>video() != null</code>.</p>
   */
  public Video video();
  /**
   * @return the number of copies of the video that are in the inventory.
   *
   */
  public int numOwned();
  /**
   * @return the number of copies of the video that are currently checked out.
   *
   */
  public int numOut();
  /**
   * @return the total number of times this video has ever been checked out.
   *
   */
  public int numRentals();
  /**
   * Delegates to video.
   * @return false if thatObject is not a Record created by this package.
   */
  public boolean equals(Object thatObject);
  /**
   * Delegates to video.
   */
  public int hashCode();
  /**
   *  @return a string representation of the object in the following format:
   * <code>"video [numOwned,numOut,numRentals]"</code>.
   */
  public String toString();
}
