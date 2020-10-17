package serialization.dynamicType;
import java.io.*;

public class Main {
  public static void main(String args[])
    throws FileNotFoundException, IOException, ClassNotFoundException
  {
    ObjectOutputStream os
      = new ObjectOutputStream (new FileOutputStream("out.dat"));
    Entry eo = new Entry(1, new X0());
    os.writeObject(eo);
    
    ObjectInputStream is
      = new ObjectInputStream (new FileInputStream("out.dat"));
    Entry ei = (Entry) is.readObject();
    System.out.println(ei);
  }
}

class Entry implements Serializable {
  private static final long serialVersionUID = 2008L;
  private int _i;
  private X _x;
  
  public Entry(int i, X x) { _i = i; _x = x; }
  public String toString() {
    return "Entry(" + Integer.toString(_i) + "," + _x + ")";
  }
}

interface X {
  public String toString();
}

class X0 implements X, Serializable {
  public String toString() { return "X0"; }
}

class X1 implements X {
  public String toString() { return "X1"; }
}
