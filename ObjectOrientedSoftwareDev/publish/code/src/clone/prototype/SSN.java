package clone.prototype;
public interface SSN extends Comparable<SSN>, Cloneable {
  public Object clone();
  public String toString();
  public long toLong();
  public static final SSN INVALID = ((SSNObj)PersonFactory.newSSN("999999999")).initialize("000000000");
}
