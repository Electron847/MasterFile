package shop.ui;

import shop.ui.UIFactory.Pair;


public interface MenuBuilder {
	public static UIMenuBuilder getUIMenuBuilder() {
		return new UIMenuBuilder();
	}
	
	public void add(String prompt, UIMenuAction action);
	public UIMenu toUIMenu(String heading);

	//public int size();
	//public Pair getPrompt(int i);

}
