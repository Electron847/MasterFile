package basics.dependency;
import java.util.Random;
final class Person {
  final private String _name;
  public Person(String name) { _name = name; }
  public String toString() { return "Person(" + _name + ")"; };
}
class PersonFactory {
  private PersonFactory() {}
  static private Random _random = new Random();
  static public  Person randomPerson() {
    return new Person(Integer.toString(_random.nextInt()));
  }
}
