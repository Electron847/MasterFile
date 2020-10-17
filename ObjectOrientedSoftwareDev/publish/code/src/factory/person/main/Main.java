package factory.person.main;
import factory.person.Person;
import factory.person.SSN;
import factory.person.PersonFactory;
public class Main {
  private Main() {}
  public static void main(String[] args) {
    Person p = PersonFactory.newPerson("Bob Jones","123456789");
    System.out.println(p);
  }
}
