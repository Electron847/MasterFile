package shop.ui;

import javax.swing.JOptionPane;

final class PopupUI implements UI, MenuUI {
  private PopupUI _menu;

public void displayMessage(String message) {
    JOptionPane.showMessageDialog(null,message);
  }
  public void displayError(String message) {
    JOptionPane.showMessageDialog(null,message,"Error",JOptionPane.ERROR_MESSAGE);
  }

  public void processMenu(UIMenu menu) {
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
	  String[] aform = new String[form.size()];
	  for(int i = 0; i < form.size(); i++) {
		  System.out.println(form.getPrompt(i));
		  aform[i] = JOptionPane.showInputDialog(form.getPrompt(i).toString());
	  }
        return aform;
      }

@Override
public String[] processForm(FormBuilder form) {
	  String[] aform = new String[form.size()];
	  for(int i = 0; i < form.size(); i++) {
		  aform[i] = JOptionPane.showInputDialog(form.getPrompt(i).toString());
	  }
		  
  return aform;
}

@Override
public String[] processForm(Form form) throws Exception {
	  String[] aform = new String[form.size()];
	  for(int i = 0; i < form.size(); i++) {
		  System.out.println(form.getPrompt(i));
		  aform[i] = JOptionPane.showInputDialog(form.getPrompt(i).toString());
	  }
  return aform;
}

@Override
public int size() {
	return 0;
}

@Override
public String getHeading() {
	return null;
}

@Override
public String getPrompt(int i) {
	return null;
}

@Override
public void runAction(int selection) {
	_menu.runAction(selection);
}

@Override
public void processMenu(MenuUI _menu) throws Exception, Throwable {
    StringBuilder b = new StringBuilder();
    b.append(_menu.getHeading());
    b.append("\n");
    b.append("Enter choice by number:");
    b.append("\n");

    for (int i = 1; i < _menu.size(); i++) {
      b.append("  " + i + ". " + _menu.getPrompt(i));
      b.append("\n");
    }

    String response = JOptionPane.showInputDialog(b.toString());
    if (response == null) {
      response = "";
    }
    int selection;
    try {
      selection = Integer.parseInt(response, 10);
      if ((selection < 0) || (selection >= _menu.size()))
        selection = 0;
    } catch (NumberFormatException e) {
      selection = 0;
    }

    _menu.runAction(selection);
    }
}
