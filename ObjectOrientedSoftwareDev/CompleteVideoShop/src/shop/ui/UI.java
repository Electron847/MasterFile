package shop.ui;

public interface UI {
  public void processMenu(MenuUI _menus) throws Exception, Throwable;
  public String[] processForm(Form _getVideoForm2) throws Exception;
  public void displayMessage(String message);
  public void displayError(String message);
  public String[] processForm(InterBuilder form) throws Exception;


}
