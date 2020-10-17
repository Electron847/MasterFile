package state.ui;

public interface UI {
  public Object processMenu(UIMenu menu);
  public String[] processForm(UIForm form);
  public void displayMessage(String message);
  public void displayError(String message);
}
