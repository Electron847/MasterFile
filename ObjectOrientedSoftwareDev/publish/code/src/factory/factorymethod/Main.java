package factory.factorymethod;
public class Main {
  public static void main (String[] args) {
    Triple<String> t1 = new FieldTriple<String>("dog", "cat", "pig");
    Triple<String> t2 = new ArrayTriple<String>("daisy", "rose", "tulip");
    for (String s : t1)
      System.out.println(s);
    for (String s : t2)
      System.out.println(s);
  }
}
