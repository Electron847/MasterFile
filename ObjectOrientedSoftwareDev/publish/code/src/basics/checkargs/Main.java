package basics.checkargs;
public class Main {
  private Main() {}
  static public void main (final String[] args) {
    System.out.println(new Person("bob"));
    System.out.println(new Person(null));
  }
}
final class Person {
  final private String _name;
  public Person(String name) {
    if (name == null)
      throw new IllegalArgumentException();
    _name = name;
  }
  public String toString() { return "Person(" + _name + ")"; };
}
