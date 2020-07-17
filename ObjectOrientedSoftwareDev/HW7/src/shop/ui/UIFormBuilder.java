package shop.ui;

import java.util.ArrayList;
import java.util.List;

import shop.ui.UIFactory.Pair;

final class UIFormBuilder implements FormBuilder, FormtoUI {
  private final List<Pair> _menu = new ArrayList<Pair>();
  public void add(String prompt, UIFormTest test) {
    _menu.add(new Pair(prompt, test));
  }
  public UIForm toUIForm(String heading) {
    if (null == heading)
      throw new IllegalArgumentException();
    if (_menu.size() < 1)
      throw new IllegalStateException();
    Pair[] array = new Pair[_menu.size()];
    for (int i = 0; i < _menu.size(); i++)
      array[i] = _menu.get(i);
    return new UIForm(heading, array);
  }
@Override
public int size() {
	return _menu.size();
}

public Pair getPrompt(int i) {
    return _menu.get(i);
  }

}
