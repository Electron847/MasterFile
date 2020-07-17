package shop.ui;

import java.util.ArrayList;
import java.util.List;

  class UIMenuBuilder implements InterBuilder<UIMenuAction,UIMenu> {
  private final List<UIFactory.Pair> _menu = new ArrayList<UIFactory.Pair>();
  public void add(String prompt, UIMenuAction action) {
    if (null == action)
      throw new IllegalArgumentException();
    _menu.add(new UIFactory.Pair(prompt, action));
  }

    @Override
    public UIMenu toUI(String heading) {
    if (null == heading)
        throw new IllegalArgumentException();
      if (_menu.size() <= 1)
        throw new IllegalStateException();
      UIFactory.Pair[] array = new UIFactory.Pair[_menu.size()];
      for (int i = 0; i < _menu.size(); i++)
        array[i] = _menu.get(i);
      return new UIMenu(heading, array);

    }
    @Override
    public String getPrompt(int i) {
        return null;
    }
    @Override
    public int size() {
     return 0;
    }
  }
