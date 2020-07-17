package shop.ui;

import shop.ui.UIFactory.Pair;

/**
 * @see UIMenuBuilder;
 */
 final class UIMenu implements MenuUI {
  private final String _heading;
  private final Pair<String,UIMenuAction>[] _menu;

  UIMenu(String heading, Pair<String, UIMenuAction>[] menu) {
    _heading = heading;
    _menu = menu;
  }
  public int size() {
     return _menu.length;
  }
  public String getHeading() {
    return _heading;
  }
  public String getPrompt(int i) {
    return _menu[i].prompt;
  }
  public void runAction(int i) {
    _menu[i].test.run();
  }

}
