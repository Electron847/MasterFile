package state.ui;

public class UIFactory {
  private UIFactory() {}
  static private TextUI _UI = new TextUI();
  static public UI ui () {
    return _UI;
  }
}
