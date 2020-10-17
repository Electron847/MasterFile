package types.abstractTypes;
public class Main {
  private Main() {}
  public static void main(String[] args) {
    java.util.LinkedList o1 = new java.util.LinkedList();
    java.util.AbstractSequentialList o2 = o1;
    java.util.AbstractList o3 = o1;
    java.util.AbstractCollection o4 = o1;
    java.lang.Object o5 = o1;

    java.util.List i1 = o1;
    java.util.Collection i2 = o1;

    java.io.Serializable i3 = o1;
    java.lang.Cloneable i4 = o1;
  }
}
