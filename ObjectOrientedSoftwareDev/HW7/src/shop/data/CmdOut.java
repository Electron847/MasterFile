package shop.data;

import shop.command.UndoableCommand;


/**
 * Implementation of command to check out a video.
 * @see Data
 */
final class CmdOut implements UndoableCommand {
  private InventorySet _inventory;
  private Record _oldvalue;
  private Video _video;
  CmdOut(InventorySet inventory, Video video) {
    _inventory = inventory;
    _video = video;
  }
  public boolean run() {
    // TODO

      try {
        _oldvalue = _inventory.checkOut(_video);
        if(_oldvalue == null) return false;
        _inventory.getHistory().add(this);
        //System.out.println("ok");
        return true;
      } catch (IllegalArgumentException e) {
        //System.out.println("IAE");
        return false;
      } catch (ClassCastException e) {
        //System.out.println("CCE");
        return false;
      }
    }
  
  
  
  public void undo() {
    // TODO
//	  _inventory.checkIn(_video);
	  _inventory.replaceEntry(_video,_oldvalue);

  }
  public void redo() {
    // TODO
	  _inventory.checkOut(_video);
  }
}
