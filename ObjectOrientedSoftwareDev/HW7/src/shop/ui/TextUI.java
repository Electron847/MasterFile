package shop.ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;

final class TextUI implements UI, Menu_UI {
  final BufferedReader _in;
  final PrintStream _out;

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
    String result = null;
    try {
      result = _in.readLine();
    } catch (IOException e) {
 //     e.getCause()throw new UIError(); // re-throw UIError
    	e.getCause();
    }
    if (result == null) {
//      throw new UIError(); // input closed
    	throw new Exception("Result is null");
    }
    return result;
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
		// TODO Auto-generated catch block
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

  //public String[] processForm(UIForm form) { 
	public String[] processForm(Form form) throws Exception { //change  -Brad20200523

	  String[] aform = new String[form.size()];
	  for(int i = 0; i < form.size(); i++) {
		  _out.println(form.getPrompt(i));
		  aform[i] = getResponse();
//		  checkInput(int i, String input)
		  
//		  String[] ret = new String[form.size()];for (int i = 0; i < form.size(); i++) {    _out.print(form.getPrompt(i) + ":");    ret[i] = getResponse();    while (!form.checkInput(i, ret[i])) {        _out.println("Invalid Input. Try again");        _out.print(form.getPrompt(i) + ":");        ret[i] = getResponse();    }}return ret;
	  }
		  
    return aform;
  }

	@Override
	public String[] processForm(UIForm form)  {//change  -Brad20200523
		// TODO Auto-generated method stub
		  String[] aform = new String[form.size()];
		  for(int i = 0; i < form.size(); i++) {
			  _out.println(form.getPrompt(i));
			  try {
				aform[i] = getResponse();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
//			  checkInput(int i, String input)
			  
//			  String[] ret = new String[form.size()];for (int i = 0; i < form.size(); i++) {    _out.print(form.getPrompt(i) + ":");    ret[i] = getResponse();    while (!form.checkInput(i, ret[i])) {        _out.println("Invalid Input. Try again");        _out.print(form.getPrompt(i) + ":");        ret[i] = getResponse();    }}return ret;
		  }
			  
	    return aform;
	}

	@Override
	public String[] processForm(FormBuilder form)  {//change  -Brad20200523
		// TODO Auto-generated method stub
		  String[] aform = new String[form.size()];
		  for(int i = 0; i < form.size(); i++) {
			  _out.println(form.getPrompt(i));
			  try {
				aform[i] = getResponse();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
//			  checkInput(int i, String input)
			  
//			  String[] ret = new String[form.size()];for (int i = 0; i < form.size(); i++) {    _out.print(form.getPrompt(i) + ":");    ret[i] = getResponse();    while (!form.checkInput(i, ret[i])) {        _out.println("Invalid Input. Try again");        _out.print(form.getPrompt(i) + ":");        ret[i] = getResponse();    }}return ret;
		  }
			  
	    return aform;
	}





}
