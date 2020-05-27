package shop.command;
import java.util.Stack;

public final class CommandHistoryObj implements CommandHistory {
  Stack<UndoableCommand> _undoStack = new Stack<UndoableCommand>();
  Stack<UndoableCommand> _redoStack = new Stack<UndoableCommand>();
  RerunnableCommand _undoCmd = new RerunnableCommand() {
      public boolean run () {
        boolean result = !_undoStack.empty();
        if (result) {
          UndoableCommand c = _undoStack.pop();
          _redoStack.push(c);
          c.undo();
        }
        return result;
      }
    };
  RerunnableCommand _redoCmd = new RerunnableCommand() {
      public boolean run () {
        boolean result = !_redoStack.empty();
        if (result) {
          UndoableCommand c = _redoStack.pop();
          _undoStack.push(c);
          c.redo();
        }
        return result;
      }
    };

  /***
   *
   * @param cmd the command to be run.
   */

  public void add(UndoableCommand cmd) {
    _undoStack.push(cmd);
    _redoStack.clear();
  }
  
  public RerunnableCommand getUndo() {
    return _undoCmd;
  }
  
  public RerunnableCommand getRedo() {
    return _redoCmd;
  }

  /***
   *
   * @return testing peek at undo
   */
  UndoableCommand topUndoCommand() {
    if (_undoStack.empty())
      return null;
    else
      return _undoStack.peek();
  }

  /***
   *
   * @return testing peek at redo
   */
  UndoableCommand topRedoCommand() {
    if (_redoStack.empty())
      return null;
    else
      return _redoStack.peek();
  }
}
