package shop.data;

import shop.command.Command;

/***
 * A static class for accessing data objects.
 */
public class Data {
  private Data() {}
  /***
   *
   * @return a new Inventory.
   */
  static public final Inventory newInventory() {
    return new InventorySet();
  }

    /***
     *
     * @param title is the movie's title
     * @param year is the movie's year of release
     * @param director is the movie's director
     * @return returns a video objects data
     * @throws IllegalArgumentException if Video invariant violated.
     */

  static public Video newVideo(String title, int year, String director) {
      if(title==null||title.isEmpty()||(title.trim().isEmpty())){
          throw new IllegalArgumentException("Title is null or empty");
      }
      if (year>=5000||year<=1800){
          throw new IllegalArgumentException("Year is too much or too little");
      }
      if(director==null||director.isEmpty()||(director.trim().isEmpty())){
          throw new IllegalArgumentException("Title is null or empty");
      }
      String tit=title.replaceAll("\\s", "");
      String dir=director.replaceAll("\\s", "");
      Video vid= new VideoObj(tit, year, dir);
      return vid;
  }


   /***
    *
    * @return  a command to add or remove copies of a video from the inventory.
    * <p>The returned command has the following behavior:</p>
    * <ul>
    * <li>If a video record is not already present (and change is
    * positive), a record is created.</li>
    * <li>If a record is already present, <code>numOwned</code> is
    * modified using <code>change</code>.</li>
    * <li>If <code>change</code> brings the number of copies to be less
    * than one, the record is removed from the inventory.</li>
    * </ul>
    * @param video the video to be added.
    * @param change the number of copies to add (or remove if negative).
    * @param inventory inventorySet to add new command to
    *
    */
  static public Command newAddCmd(Inventory inventory, Video video, int change) {
    if (!(inventory instanceof InventorySet))
      throw new IllegalArgumentException();
    return new CmdAdd((InventorySet) inventory, video, change);
  }

  /***
   * @return eturns a command to check out a video.
   * @param video the video to be checked out.
   * @param inventory the inventorySet to be checking out a video from
   */
  static public Command newOutCmd(Inventory inventory, Video video) {
      if (!(inventory instanceof InventorySet))
          throw new IllegalArgumentException();
      return new CmdOut((InventorySet) inventory, video);
  }
  
  /***
   * @return returns a command to check in a video.
   * @param video the video to be checked in.
   * @param inventory the inventorySet to be checking out a video to
   */
  static public Command newInCmd(Inventory inventory, Video video) {
      if (!(inventory instanceof InventorySet))
          throw new IllegalArgumentException();
      return new CmdIn((InventorySet) inventory, video);
  }
  
  /***
   * @param inventory the InventorySet to be cleared
   * @return returns a command to clear all videos
   */
  static public Command newClearCmd(Inventory inventory) {
    if (!(inventory instanceof InventorySet))
      throw new IllegalArgumentException();
    return new CmdClear((InventorySet) inventory);
  }
}  
