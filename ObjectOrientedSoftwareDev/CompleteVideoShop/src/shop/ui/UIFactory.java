package shop.ui;

public class UIFactory  {
  public UIFactory() {} //constructor
  //static private UI _UI = new PopupUI();
  static private UI _UI = new TextUI();
  static public UI ui (String s) {
	  
    if (s=="TextUI") {
    _UI = new TextUI();
  } else if(s=="PopupUI"){
    _UI = new PopupUI();
  }
    return _UI;
  }
  
  public static final class Pair<T, V> {
	    final T prompt;
	    final V test;

	    Pair(T thePrompt, V theTest) {
	      prompt = thePrompt;
	      test = theTest;
	    }
	  }
  
	public static InterBuilder getUIFormBuilder() {
		return new UIFormBuilder();
	}
	public static InterBuilder getUIMenuBuilder() {
		return new UIMenuBuilder();
	}
}



