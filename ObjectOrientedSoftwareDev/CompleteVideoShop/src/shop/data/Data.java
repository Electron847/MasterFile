package shop.data;

import java.util.WeakHashMap;

import shop.command.RerunnableCommand;
import shop.command.UndoableCommand;

/**
 * A static class for accessing data objects.
 */
public class Data {
  private Data() {}
  private static final WeakHashMap<String,Video> videoMap= new WeakHashMap();

  /**
   * @return a new Inventory.
   */
  static public final Inventory newInventory() {
    return new InventorySet();
  }

  /**
   * Factory method for Video objects.
   * @param title title
   * @param year year
   * @param director director
   * @return
   * @throws IllegalArgumentException if Video invariant violated.
   */
  static public Video newVideo(String title, int year, String director) {

      if (  (title == null)
              || (director == null)
              || (year <= 1800)
              || (year >= 5000)) {
          throw new IllegalArgumentException();
      }
        title = title.trim();
        director = director.trim();
      if (  ("".equals(title))
              || ("".equals(director))) {
          throw new IllegalArgumentException();
      }
	  	String key = title.trim() + " (" + year + ") : " + director.trim();


	  	Video v;
	  	if(videoMap.containsKey(key)){
	  		System.out.println("Yes, WeakHashMap contains the key");
	  	  v = videoMap.get(key);
	  	}else {
	  	  v =  new VideoObj(title.trim(),year,director.trim());
	  	  videoMap.put(key,v);
	  	}
    return v;
  }

  /**
   * @return a command to add or remove copies of a video from the inventory.
   * @param inventory inventorySet
   * @param video the video to be added.
   * @param change the number of copies to add (or remove if negative).
   */
  static public UndoableCommand newAddCmd(Inventory inventory, Video video, int change) {
    if (!(inventory instanceof InventorySet))
      throw new IllegalArgumentException();
    return new CmdAdd((InventorySet) inventory, video, change);
  }

  /**
   * @return a command to check out a video.
   * @param video the video to be checked out
   * @param inventory inventorySet
   */
  static public UndoableCommand newOutCmd(Inventory inventory, Video video) {
    return new CmdOut( (InventorySet)inventory,video);
  }
  
  /**
   * @return a command to check in a video.
   * @param video the video to be checked in
   * @param inventory inventorySet
   */
  static public UndoableCommand newInCmd(Inventory inventory, Video video) {
    return new CmdIn( (InventorySet)inventory,video);
  }
  
  /**
   * @param inventory inventorySet
   * @return a command to remove all records from the inventory.
   */
  static public UndoableCommand newClearCmd(Inventory inventory) {
    if (!(inventory instanceof InventorySet))
      throw new IllegalArgumentException();
    return new CmdClear((InventorySet) inventory);
  }

  /**
   * @param inventory inventoryset
   * @return a command to undo that will undo the last successful UndoableCommand.
   */
  static public RerunnableCommand newUndoCmd(Inventory inventory) {
    if (!(inventory instanceof InventorySet))
      throw new IllegalArgumentException();
    return ((InventorySet)inventory).getHistory().getUndo();
  }

  /**
   * @param inventory inventoryset
   * @return a command to redo that last successfully undone command.
   */
  static public RerunnableCommand newRedoCmd(Inventory inventory) {
	    if (!(inventory instanceof InventorySet))
	        throw new IllegalArgumentException();
	      return ((InventorySet)inventory).getHistory().getRedo();
  }
}  
