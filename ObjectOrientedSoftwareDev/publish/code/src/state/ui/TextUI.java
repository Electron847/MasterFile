package state.ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;

final class TextUI implements UI {
  final BufferedReader _in;
  final PrintStream _out;

  TextUI() {
    _in = new BufferedReader(new InputStreamReader(System.in));
    _out = System.out;
  }

  public void displayMessage(String message) {
    _out.println(message);
  }

  public void displayError(String message) {
    _out.println(message);
  }

  public Object processMenu(UIMenu menu) {
    _out.println(menu.getHeading());
    _out.println("Enter choice by number:");

    for (int i = 1; i < menu.size(); i++) {
      _out.println("  " + i + ". " + menu.getPrompt(i));
    }

    String responseString;

    try {
      responseString = _in.readLine();
    } catch (IOException e) {
      throw new UIError(); // re-throw UIError
    }
    if (responseString == null) {
      throw new UIError(); // input closed
    }
    
    int selection;
    try {
      selection = Integer.parseInt(responseString, 10);
      if ((selection < 0) || (selection >= menu.size()))
        selection = 0;
    } catch (NumberFormatException e) {
      selection = 0;
    }

    return menu.runAction(selection);
  }

  public String[] processForm(UIForm form) {
    // TODO
    return null;
  }
}
