package serialization.custom;
import java.io.Serializable;
interface IntList extends Serializable {
  public void addBack(int i);
  public void addFront(int i);
  public int removeFront();
  public int removeBack();
  public boolean isEmpty();
}

