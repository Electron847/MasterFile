package shop.ui;


import shop.main.ActionClass.MenuState;
import shop.ui.UIFactory.Pair;

public interface InterBuilder<T,C> {
	public void add(String string, T dfltst);
	public C toUI(String string);
	public String getPrompt(int i);
	int size();
}

