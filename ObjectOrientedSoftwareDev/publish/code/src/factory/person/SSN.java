package factory.person;
public interface SSN extends Comparable<SSN> {
  public String toString();
  public long toLong();
  public static final SSN INVALID = new SSNObj("000000000");
}
