package shop.ui;

import shop.ui.UIFactory.Pair;

public interface MenuUI {
	public int size();
	public String getHeading();
	public String getPrompt(int i);
	public void runAction(int i);
	public static UIMenu UIMenu(String heading, Pair[] menu) {
		return null;
	}
}
