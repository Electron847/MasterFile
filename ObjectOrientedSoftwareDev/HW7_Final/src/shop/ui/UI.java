package shop.ui;

public interface UI {
  public String[] processForm(FormBuilder form) throws Exception;
  public void processMenu(MenuUI _menus) throws Exception, Throwable;
  public String[] processForm(Form _getVideoForm2) throws Exception;
  public void displayMessage(String message);
  public void displayError(String message);



}
