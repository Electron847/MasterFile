package shop.main;

import shop.ui.UIFactory;
import shop.ui.UI;
import shop.data.Data;

public class Main {
  private Main() {}
 
  public static void main(String[] args) {
    // UI ui;
    
//  Control control = new Control(Data.newInventory(), UIFactory.ui("PopupUI"));
    Control control = new Control(Data.newInventory(), UIFactory.ui("TextUI"));
    control.run();
  }
}


