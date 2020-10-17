package serialization;
import java.io.*;

class Person2 implements Externalizable {
  private static final long serialVersionUID = 2008L;
  private String name = ""; private int age = 0;
  public Person2() { }
  public Person2(String name, int age) { this.name = name; this.age = age;}
  public String getName() { return name; }
  public int getAge() { return age; }
  public String toString() {return "Name: " + name + " Age: " + Integer.toString(age);}
  
  public void writeExternal(ObjectOutput out)
    throws IOException
  {
    out.writeObject(name);
    out.writeInt(age);
  }
  public void readExternal(ObjectInput in)
    throws IOException, ClassNotFoundException
  {
    name = (String) in.readObject();
    age = in.readInt();
  }
}
