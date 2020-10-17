package factory.person;
public interface Person extends Comparable<Person> {
  public String name();
  public SSN ssn();
}
