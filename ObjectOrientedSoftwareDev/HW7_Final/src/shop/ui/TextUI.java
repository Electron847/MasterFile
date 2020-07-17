package shop.ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.*;

  final class TextUI implements UI, MenuUI {
  final BufferedReader _in;
  final PrintStream _out;
  private TextUI _menu;

  public TextUI() {
    _in = new BufferedReader(new InputStreamReader(System.in));
    _out = System.out;
  }

  public void displayMessage(String message) {
    _out.println(message);
  }
  public void displayError(String message) {
    _out.println(message);
  }
  private String getResponse() throws Exception {
    String response = null;
    try {
		response = _in.readLine();
    } catch (IOException e) {
    	e.getCause();
    }
    if (response == null) {
    	throw new Exception("Result is null");
    }
    return response;
  }

  public void processMenu(UIMenu menu) {
    _out.println(menu.getHeading());
    _out.println("Enter choice by number:");

    for (int i = 1; i < menu.size(); i++) {
      _out.println("  " + i + ". " + menu.getPrompt(i));
    }

    String response = null;
	try {
		response = getResponse();
	} catch (Exception e1) {
		e1.printStackTrace();
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

	public String[] processForm(Form form) throws Exception {

	  String[] aform = new String[form.size()];
	  for(int i = 0; i < form.size(); i++) {
		  _out.println(form.getPrompt(i));
		  aform[i] = getResponse();
	  }
		  
    return aform;
  }


	public String[] processForm(UIForm form)  {
		  String[] aform = new String[form.size()];
		  for(int i = 0; i < form.size(); i++) {
			  _out.println(form.getPrompt(i));
			  try {
				aform[i] = getResponse();
			} catch (Exception e) {
				e.printStackTrace();
			}
		  }
	    return aform;
	}

	@Override
	public String[] processForm(FormBuilder form)  {
		  String[] aform = new String[form.size()];
		  for(int i = 0; i < form.size(); i++) {
			  _out.println(form.getPrompt(i));
			  try {
				aform[i] = getResponse();
			} catch (Exception e) {
				e.printStackTrace();
			}
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
		   _out.println(_menu.getHeading());
		    _out.println("Enter choice by number:");

		    for (int i = 1; i < _menu.size(); i++) {
		      _out.println("  " + i + ". " + _menu.getPrompt(i));
		    }

		    String response = null;
			try {
				response = getResponse();
			} catch (Exception e1) {
				e1.printStackTrace();
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
