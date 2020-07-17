package shop.ui;

import java.util.ArrayList;
import java.util.List;
import shop.ui.UIFactory.Pair;

final class UIFormBuilder implements InterBuilder<UIFormTest,UIForm> {
  private final List<Pair> _menu = new ArrayList<Pair>();
  public void add(String prompt, UIFormTest test) {
    _menu.add(new Pair(prompt, test));
  }
  public UIForm toUI(String heading) {
    if (null == heading)
      throw new IllegalArgumentException();
    if (_menu.size() < 1)
      throw new IllegalStateException();
    Pair[] array = new Pair[_menu.size()];
    for (int i = 0; i < _menu.size(); i++)
      array[i] = _menu.get(i);
    return new UIForm(heading, array);
  }
  public int size() {
	  return _menu.size();
  }
  public String getPrompt(int i) {
      return null;
    }
}
