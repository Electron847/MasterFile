package shop.ui;

import javax.swing.JOptionPane;
//import java.io.IOException;

final class PopupUI implements UI {
  public void displayMessage(String message) {
    JOptionPane.showMessageDialog(null,message);
  }

  public void displayError(String message) {
    JOptionPane.showMessageDialog(null,message,"Error",JOptionPane.ERROR_MESSAGE);
  }

  public void processMenu(Menu_UI menu) {
    StringBuilder b = new StringBuilder();
    b.append(menu.getHeading());
    b.append("\n");
    b.append("Enter choice by number:");
    b.append("\n");

    for (int i = 1; i < menu.size(); i++) {
      b.append("  " + i + ". " + menu.getPrompt(i));
      b.append("\n");
    }

    String response = JOptionPane.showInputDialog(b.toString());
    if (response == null) {
      response = "";
    }
    int selection;
    try {
      selection = Integer.parseInt(response, 10);
      if ((selection < 0) || (selection >= menu.size()))
        selection = 0;
    } catch (NumberFormatException e) {
      selection = 0;
    }

    menu.runAction(selection);
  }

  public String[] processForm(UIForm form) {
    // TODO	  
	  String[] aform = new String[form.size()];
	  for(int i = 0; i < form.size(); i++) {
		  System.out.println(form.getPrompt(i));
//		  aform[i] = JOptionPane.showInputDialog(form.toString());	
		  aform[i] = JOptionPane.showInputDialog(form.getPrompt(i).toString());
	  }
		  
    return aform;
      }

@Override
public String[] processForm(FormBuilder form) {//changed - Brad-20200523
	// TODO Auto-generated method stub
	  String[] aform = new String[form.size()];
	  for(int i = 0; i < form.size(); i++) {
		  aform[i] = JOptionPane.showInputDialog(form.getPrompt(i).toString());
//		  _out.println(form.getPrompt(i));
//		  checkInput(int i, String input)
		  
	  }
		  
  return aform;
}
}
