package serialization.custom;
import java.util.LinkedList;
import java.io.Serializable;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.FileInputStream;
import java.io.ObjectInputStream;

class IntList1 implements IntList
{
  private LinkedList<Integer> _v;
  private void setV(LinkedList<Integer> v) { _v = _v; }

  public IntList1()               { _v = new LinkedList<Integer>(); }
  public void addBack(int i)      { _v.addLast(i); }
  public void addFront(int i)     { _v.addFirst(i); }
  public int removeFront()        { return _v.removeFirst(); }
  public int removeBack()         { return _v.removeLast(); }
  public boolean isEmpty()        { return _v.size() == 0; }
}

public class Main1 {
  public static void main(String[] args)
    throws IOException, FileNotFoundException, ClassNotFoundException 
  {
    IntList L = new IntList1();
    for (int i=0; i<1000; i++)
      L.addFront(i);
    
    ObjectOutputStream os = new ObjectOutputStream (new FileOutputStream("out1.dat"));
    os.writeObject(L);
    os.flush();

    ObjectInputStream in = new ObjectInputStream (new FileInputStream("out1.dat")); 
    IntList V = (IntList) in.readObject();
  }
}
