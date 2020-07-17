package shop.ui;

public interface MenuBuilder {
	public static UIMenuBuilder getUIMenuBuilder() {
		return new UIMenuBuilder();
	}
	
	public void add(String prompt, UIMenuAction action);
	public UIMenu toUIMenu(String heading);





}
