package shop.ui;

import shop.ui.UIFactory.Pair;

public interface FormBuilder {
	public void add(String string, UIFormTest _stringTest);
	public UIForm toUIForm(String string);
	public static FormBuilder getUIFormBuilder() {
		return new UIFormBuilder();
	}
	public int size();
	public Pair getPrompt(int i);





}
