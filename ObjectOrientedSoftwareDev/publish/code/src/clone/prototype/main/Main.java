package clone.prototype.main;
import clone.prototype.Person;
import clone.prototype.SSN;
import clone.prototype.PersonFactory;
public class Main {
  private Main() {}
  public static void main(String[] args) {
    Person p = PersonFactory.newPerson("Bob Jones","123456789");
    System.out.println(p);
  }
}
