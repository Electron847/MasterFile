package shop.data;

import shop.command.RerunnableCommand;
import shop.command.UndoableCommand;

/**
 * A static class for accessing data objects.
 */
public class Data {
  private Data() {}
  /**
   * @return a new Inventory.
   */
  static public final Inventory newInventory() {
    return new InventorySet();
  }

  /**
   * Factory method for Video objects.
   * @param title title of video
   * @param year year video made
   * @param director director of video
   * @return new videoObject
   * Title and director are "trimmed" to remove leading and final space.
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
    return new VideoObj(title, year, director);
  }

  /**
   * @return a command to add or remove copies of a video from the inventory.
   * @param video the video to be added.
   * @param change the number of copies to add (or remove if negative).
   * @param inventory is the inventoryset used
   *
   */
  static public UndoableCommand newAddCmd(Inventory inventory, Video video, int change) {
    if (!(inventory instanceof InventorySet))
      throw new IllegalArgumentException();
    return new CmdAdd((InventorySet) inventory, video, change);
  }

  /**
   * @return a command to check out a video.
   * @param video the video to be checked out.
   * @param inventory inventoryset being used
   */
  static public UndoableCommand newOutCmd(Inventory inventory, Video video) {
    if (!(inventory instanceof InventorySet))
      throw new IllegalArgumentException();
    return new CmdOut((InventorySet) inventory, video);
  }
  
  /**
   * @return a command to check in a video.
   * @param video the video to be checked in.
   * @param inventory inventoryset being used
   */
  static public UndoableCommand newInCmd(Inventory inventory, Video video) {
    if (!(inventory instanceof InventorySet))
      throw new IllegalArgumentException();
    return new CmdIn((InventorySet) inventory, video);
  }
  
  /**
   * @return a command to remove all records from the inventory.
   * @param inventory inventoryset being used
   */
  static public UndoableCommand newClearCmd(Inventory inventory) {
    if (!(inventory instanceof InventorySet))
      throw new IllegalArgumentException();
    return new CmdClear((InventorySet) inventory);
  }

  /**
   * @return a command to undo that will undo the last successful UndoableCommand.
   * @param inventory invetnroyset being used
   */
  static public RerunnableCommand newUndoCmd(Inventory inventory) {
    if (!(inventory instanceof InventorySet))
      throw new IllegalArgumentException();
    return ((InventorySet)inventory).getHistory().getUndo();
  }

  /**
   * @return a command to redo that last successfully undone command.
   * @param inventory inventoryset being used
   */
  static public RerunnableCommand newRedoCmd(Inventory inventory) {
    if (!(inventory instanceof InventorySet))
      throw new IllegalArgumentException();
    return ((InventorySet)inventory).getHistory().getRedo();
  }
}  
