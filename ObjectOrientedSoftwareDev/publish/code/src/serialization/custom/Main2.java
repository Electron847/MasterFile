package serialization.custom;
import java.util.LinkedList;
import java.io.Serializable;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.FileInputStream;
import java.io.ObjectInputStream;

class IntList2 implements IntList
{
  private LinkedList<Integer> _v;
  private void setV(LinkedList<Integer> v) { _v = _v; }

  public IntList2()               { _v = new LinkedList<Integer>(); }
  public void addBack(int i)      { _v.addLast(i); }
  public void addFront(int i)     { _v.addFirst(i); }
  public int removeFront()        { return _v.removeFirst(); }
  public int removeBack()         { return _v.removeLast(); }
  public boolean isEmpty()        { return _v.size() == 0; }

  private void writeObject(ObjectOutputStream out) throws IOException 
  { System.out.println("call specialized writer");
    out.writeInt(_v.size());
    for (Integer i : _v )
      out.writeInt(i);
  }

  private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException
  { System.out.println("call specialized reader");
    _v = new LinkedList<Integer>();
    int size = in.readInt();
    for (int i = 0;  i<size; i++)
      addBack(in.readInt());
  }
}

public class Main2 {
  public static void main(String[] args)
    throws IOException, FileNotFoundException, ClassNotFoundException 
  {
    IntList L = new IntList2();
    for (int i=0; i<1000; i++)
      L.addFront(i);
    
    ObjectOutputStream os = new ObjectOutputStream (new FileOutputStream("out2.dat"));
    os.writeObject(L);
    os.flush();

    ObjectInputStream in = new ObjectInputStream (new FileInputStream("out2.dat")); 
    IntList V = (IntList) in.readObject();
  }
}
