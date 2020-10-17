package state.ui.main;

import state.ui.UIFactory;

public class Main {
  private Main() {}
  public static void main(String[] args) {
    Control control = new Control(UIFactory.ui());
    control.run();
  }
}
