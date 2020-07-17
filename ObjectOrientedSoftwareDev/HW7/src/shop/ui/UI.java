package shop.ui;

public interface UI {
  public void processMenu(Menu_UI menu) throws Exception, Throwable;
  public String[] processForm(UIForm form) throws Exception;
  public void displayMessage(String message);
  public void displayError(String message);
  public String[] processForm(FormBuilder form) throws Exception;


}
