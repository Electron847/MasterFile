package shop.ui;

import shop.ui.UIFactory.Pair;

public interface Form {

	

public String getHeading();
public String getPrompt(int i);
public int size();
public boolean checkInput(int i, String input);
public static UIForm UIForm(String heading, Pair[] menu) {
	return null;
}


}
