package shop.data;

import shop.command.RerunnableCommand;
import shop.command.UndoableCommand;

import java.util.WeakHashMap;

/**
 * A static class for accessing data objects.
 */
public class Data {
  private Data() {}
  private static final WeakHashMap<String,Video> vidMap = new WeakHashMap<>();
  /**
   * @return a new Inventory.
   */
  static public final Inventory newInventory() {
    return new InventorySet();
  }

  /**
   * Factory method for Video objects.
   * @return video object
   * @param title and
   * @param director director are "trimmed" to remove leading and final space.
   * @param year for
   * @throws IllegalArgumentException if Video invariant violated.
   * @return new video object
   */
  static public Video newVideo(String title, int year, String director) {

    String vidKey = title.trim() + " (" + year + ") : " + director.trim();
    Video video;
    if (vidMap.containsKey(vidKey))
    {
      video = vidMap.get(vidKey);
    }
    else {
      video = new VideoObj(title, year, director);
      vidMap.put(vidKey,video);
    }
    return video;
  }

  /**
   * @return a command to add or remove copies of a video from the inventory.
   * <p>The returned command has the following behavior:</p>
   * <ul>
   * <li>If a video record is not already present (and change is
   * positive), a record is created.</li>
   * <li>If a record is already present, <code>numOwned</code> is
   * modified using <code>change</code>.</li>
   * <li>If <code>change</code> brings the number of copies to be less
   * than one, the record is removed from the inventory.</li>
   * </ul>
   * @param inventory inventoryset
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
   * @param video the video to be checked out.
   * @param inventory inventorySet
   */
  static public UndoableCommand newOutCmd(Inventory inventory, Video video) {
    return new CmdOut( (InventorySet)inventory,video);
  }
  
  /**
   * @return a command to check in a video.
   * @param video the video to be checked in.
   * @param inventory inventory set
   */
  static public UndoableCommand newInCmd(Inventory inventory, Video video) {
    return new CmdIn( (InventorySet)inventory,video);
  }
  
  /**
   * @param inventory inventory set
   * @return a command to remove all records from the inventory.
   */
  static public UndoableCommand newClearCmd(Inventory inventory) {
    if (!(inventory instanceof InventorySet))
      throw new IllegalArgumentException();
    return new CmdClear((InventorySet) inventory);
  }

  /**
   * @param inventory inventory set
   * @return a command to undo that will undo the last successful UndoableCommand.
   */
  static public RerunnableCommand newUndoCmd(Inventory inventory) {
    if (!(inventory instanceof InventorySet))
      throw new IllegalArgumentException();
    return ((InventorySet)inventory).getHistory().getUndo();
  }

  /**
   * @param inventory inventory set
   * @return a command to redo that last successfully undone command.
   */
  static public RerunnableCommand newRedoCmd(Inventory inventory) {
	    if (!(inventory instanceof InventorySet))
	        throw new IllegalArgumentException();
	      return ((InventorySet)inventory).getHistory().getRedo();
  }
}  
