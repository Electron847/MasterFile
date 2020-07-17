package shop.ui;
import shop.ui.UIFactory.Pair;

public interface Menu_UI {

    public int size();
    public String getHeading();
    public String getPrompt(int i);
    public void runAction(int i);
    public static UIMenu UI_MENU(String heading, Pair[] menu){
        return null;
    }
}
