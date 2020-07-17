package shop.main;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import shop.command.Command;
import shop.data.Data;
import shop.data.Inventory;
import shop.data.Record;
import shop.data.Video;
import shop.ui.Form;
import shop.ui.InterBuilder;
import shop.ui.UI;
import shop.ui.UIFactory;
import shop.ui.UIFormTest;
import shop.ui.UIMenuAction;

public class ActionClass extends Control{
	
ActionClass(Inventory inventory, UI ui) {
		super(inventory, ui);
	}

public enum FrmState implements UIFormTest{
	STRTST{
	    public boolean run(String input) {
	        return ! "".equals(input.trim());
	      }


	},
	YRTST{
	    public boolean run(String input) {
	        try {
	          int i = Integer.parseInt(input);
	          return i > 1800 && i < 5000;
	        } catch (NumberFormatException e) {
	          return false;
	        }
	      }
	}
}


public enum MenuState implements UIMenuAction{
    DFLTST{
	@Override
	public void run() {
	   _ui.displayError("doh!");
	  }

},
ADDRMV{
	@Override
	public void run() {
	    String[] result1=null;
		try {
			result1 = _ui.processForm(_getVideoForm2);
		} catch (Exception e) {
			e.printStackTrace();
		}
	    Video v = Data.newVideo(result1[0], Integer.parseInt(result1[1]), result1[2]);
	    InterBuilder<UIFormTest, Form> f = UIFactory.getUIFormBuilder();
	     f.add("Number of copies to add/remove", _numberTest);
	     String[] result2 = null;
		try {
			result2 = _ui.processForm(f.toUI(""));
		} catch (Exception e) {
			e.printStackTrace();
		}

	     Command c = Data.newAddCmd(_inventory, v, Integer.parseInt(result2[0]));
	     if (! c.run()) {
	          _ui.displayError("Command failed");
	      }
	  }		
},
CHKIN{
    public void run() {
        String[] result1 = null;
		try {
			result1 = _ui.processForm(_getVideoForm2);
		} catch (Exception e) {
			e.printStackTrace();
		}
        Video v = Data.newVideo(result1[0], Integer.parseInt(result1[1]), result1[2]);
        
        Command c = Data.newInCmd(_inventory, v);
        if (! c.run()) {
          _ui.displayError("Command failed");
        }  
    }
},
CHKOUT{
    public void run() {
          String[] result1 = null;
			try {
				result1 = _ui.processForm(_getVideoForm2);
			} catch (Exception e) {
				e.printStackTrace();
			}
          Video v = Data.newVideo(result1[0], Integer.parseInt(result1[1]), result1[2]);
          
          Command c = Data.newOutCmd(_inventory, v);
          if (! c.run()) {
            _ui.displayError("Command failed");
          }
      }
},
PRTINV{
    public void run() {
        _ui.displayMessage(_inventory.toString());
      }
},
CLRINV{
    public void run() {
        if (!Data.newClearCmd(_inventory).run()) {
          _ui.displayError("Command failed");
        }
      }
},
UNDO{
    public void run() {
        if (!Data.newUndoCmd(_inventory).run()) {
          _ui.displayError("Command failed");
        }
    }
},
REDO{
    public void run() {
        if (!Data.newRedoCmd(_inventory).run()) {
          _ui.displayError("Command failed");
        }
      }
},
TOPTEN{
    public void run() {
        // TODO
		  	 List<Record> _cdata = new ArrayList<Record>(_inventory.toCollection());
		  	 
		  	Collections.sort(_cdata, new Comparator<Record>() {
				@Override
				public int compare(Record r1, Record r2) {
					return Integer.compare(r1.numRentals(), r2.numRentals());
				}								
			});
		  	Collections.reverse(_cdata);
		  	String str = "";
		  	for (int i = 0; i < _cdata.size(); i++) {
		  	    Record aName = _cdata.get(i);
		  	    if(i<10) {
		  	    	str+=aName+"\n";
		  	    }
		  
		  	    
		  	}
		  	_ui.displayMessage(str);
      }
},
EXITX{
    public void run() {
        _state = EXIT;
      }
},
BOGUS{
    public void run() {
        Data.newAddCmd(_inventory, Data.newVideo("a", 2000, "m"), 1).run();
        Data.newAddCmd(_inventory, Data.newVideo("b", 2000, "m"), 2).run();
        Data.newAddCmd(_inventory, Data.newVideo("c", 2000, "m"), 3).run();
        Data.newAddCmd(_inventory, Data.newVideo("d", 2000, "m"), 4).run();
        Data.newAddCmd(_inventory, Data.newVideo("e", 2000, "m"), 5).run();
        Data.newAddCmd(_inventory, Data.newVideo("f", 2000, "m"), 6).run();
        Data.newAddCmd(_inventory, Data.newVideo("g", 2000, "m"), 7).run();
        Data.newAddCmd(_inventory, Data.newVideo("h", 2000, "m"), 8).run();
        Data.newAddCmd(_inventory, Data.newVideo("i", 2000, "m"), 9).run();
        Data.newAddCmd(_inventory, Data.newVideo("j", 2000, "m"), 10).run();
        Data.newAddCmd(_inventory, Data.newVideo("k", 2000, "m"), 11).run();
        Data.newAddCmd(_inventory, Data.newVideo("l", 2000, "m"), 12).run();
        Data.newAddCmd(_inventory, Data.newVideo("m", 2000, "m"), 13).run();
        Data.newAddCmd(_inventory, Data.newVideo("n", 2000, "m"), 14).run();
        Data.newAddCmd(_inventory, Data.newVideo("o", 2000, "m"), 15).run();
        Data.newAddCmd(_inventory, Data.newVideo("p", 2000, "m"), 16).run();
        Data.newAddCmd(_inventory, Data.newVideo("q", 2000, "m"), 17).run();
        Data.newAddCmd(_inventory, Data.newVideo("r", 2000, "m"), 18).run();
        Data.newAddCmd(_inventory, Data.newVideo("s", 2000, "m"), 19).run();
        Data.newAddCmd(_inventory, Data.newVideo("t", 2000, "m"), 20).run();
        Data.newAddCmd(_inventory, Data.newVideo("u", 2000, "m"), 21).run();
        Data.newAddCmd(_inventory, Data.newVideo("v", 2000, "m"), 22).run();
        Data.newAddCmd(_inventory, Data.newVideo("w", 2000, "m"), 23).run();
        Data.newAddCmd(_inventory, Data.newVideo("x", 2000, "m"), 24).run();
        Data.newAddCmd(_inventory, Data.newVideo("y", 2000, "m"), 25).run();
        Data.newAddCmd(_inventory, Data.newVideo("z", 2000, "m"), 26).run();
      }
},
DEFAULT{
	 public void run() {} 
},
YESX{
    public void run() {
        _state = EXITED;
      }	
},
NOX{
    public void run() {
        _state = START;
      }
},

}
}
