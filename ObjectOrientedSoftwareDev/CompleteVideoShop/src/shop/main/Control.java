package shop.main;

import shop.ui.Form;
import shop.ui.InterBuilder;
import shop.ui.MenuUI;
import shop.ui.UI;
import shop.ui.UIFactory;
import shop.ui.UIMenuAction;
import shop.ui.UIFormTest;
import shop.data.Inventory;
import shop.main.ActionClass.FrmState;
import shop.main.ActionClass.MenuState;

class Control {
  static final int EXITED = 0;
  static final int EXIT = 1;
  static final int START = 2;
  private static final int NUMSTATES = 10;
  private MenuUI[] _menus;
  static int _state;
  static UIFormTest _numberTest;
  private UIFormTest _stringTest;
  static  Form _getVideoForm2;
  static Inventory _inventory;
  static UI _ui;

  
  Control(Inventory inventory, UI ui) {
    _inventory = inventory;
    _ui = ui;
    _menus = new MenuUI[NUMSTATES];
    _state = START;
    addSTART(START);
    addEXIT(EXIT);
    FrmState yearTest = FrmState.YRTST;
      
    _numberTest = new UIFormTest() {
        public boolean run(String input) {
          try {
            Integer.parseInt(input);
            return true;
          } catch (NumberFormatException e) {
            return false;
          }
        }
      };
  
      _stringTest = FrmState.STRTST;
      InterBuilder<UIFormTest, Form> f = UIFactory.getUIFormBuilder();
      f.add("Title", _stringTest);
      f.add("Year", yearTest);
      f.add("Director", _stringTest);
      Form  _getVideoForm = f.toUI("Enter Video");
      _getVideoForm2 =_getVideoForm;
  }
  
  void run() {
    try {
      while (_state != EXITED) {
        try {					// EXITED
			_ui.processMenu(_menus[_state]);
		} catch (Throwable e) {
			e.printStackTrace();
		}
      }
    } catch (Exception e) {
      _ui.displayError("UI closed");
    }
  }
  
  private void addSTART(int stateNum) {
    InterBuilder<UIMenuAction,MenuUI> m = UIFactory.getUIMenuBuilder();
    m.add("Default",MenuState.DFLTST);
    m.add("Add/Remove copies of a video",MenuState.ADDRMV);
    m.add("Check in a video",MenuState.CHKIN);
    m.add("Check OUT a video",MenuState.CHKOUT);
    m.add("Print the inventory",MenuState.PRTINV);
    m.add("Clear the inventory",MenuState.CLRINV);
    m.add("Undo",MenuState.UNDO);
    m.add("Redo",MenuState.REDO);
    m.add("Print top ten all time rentals in order",MenuState.TOPTEN);
    m.add("Exit",MenuState.EXITX);
    m.add("Initialize with bogus contents",MenuState.BOGUS);
    _menus[stateNum] = m.toUI("Bob's Video");
  }
  private void addEXIT(int stateNum) {
    InterBuilder<UIMenuAction,MenuUI> m = UIFactory.getUIMenuBuilder();
    m.add("Default", MenuState.DEFAULT);
    m.add("Yes", MenuState.YESX);   
    m.add("No", MenuState.NOX);  
    _menus[stateNum] = m.toUI("Are you sure you want to exit?");
  }







}
