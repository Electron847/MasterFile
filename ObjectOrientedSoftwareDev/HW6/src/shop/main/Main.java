package shop.main;

import shop.data.Data;
import shop.ui.UI;

public class Main {
  private Main() {}
  public static void main(String[] args) {
    UI ui;
    if (Math.random() <= 0.5) {
      ui = new shop.ui.TextUI();
    } else {
      ui = new shop.ui.PopupUI();
    }
    Control control = new Control(Data.newInventory(), ui);
    control.run();
  }
}
