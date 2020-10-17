package clone.prototype;
import java.util.Random;
public class PersonFactory {
  private static SSNObj _prototypeSSN = new SSNObj();
  private static PersonObj _prototypePerson = new PersonObj();

  private PersonFactory() {}
  static private Random _random = new Random();
  static public SSN newSSN(String s) {
    final int REQUIREDLENGTH = 9;
    if (null == s || REQUIREDLENGTH != s.length())
      throw new IllegalArgumentException();
    boolean someNonZeroDigit = false;
    for (int i=0; i<REQUIREDLENGTH; i++) {
      char c = s.charAt(i);
      if (!Character.isDigit(c))
        throw new IllegalArgumentException();
      if (0 != Character.digit(c,10))
        someNonZeroDigit = true;
    }
    if (!someNonZeroDigit)
      throw new IllegalArgumentException();
    return ((SSNObj)_prototypeSSN.clone()).initialize(s);
  }
  static public Person newRandomPerson() {
    return ((PersonObj)_prototypePerson.clone()).
      initialize(Integer.toString(_random.nextInt()),SSN.INVALID);
  }
  static public Person newPerson(String name, String ssn) {
    return ((PersonObj)_prototypePerson.clone()).initialize(name,ssn);
  }
  static public Person newPerson(String name, SSN ssn) {
    return ((PersonObj)_prototypePerson.clone()).initialize(name,ssn);
  }
}

final class SSNObj implements SSN {
  private String _s;
  private long _l;
  SSNObj() {}
  SSNObj initialize(String s) {
    _s = s;
    _l = Long.parseLong(s);
    return this;
  }
  public String toString() { return _s; }
  public long toLong() { return _l; }
  // TODO: define equals, hashcode, compareTo
  public int compareTo(SSN o) { return -1; }
  public Object clone() {
    try {
      return super.clone();
    } catch (CloneNotSupportedException e) {
      throw new RuntimeException(e);
    }
  } 
}

final class PersonObj implements Person {
  private String _name;
  private SSN _ssn;
  PersonObj() {}
  PersonObj initialize(String name, SSN ssn) {
    if (null == name || 0 == name.trim().length()) 
      throw new IllegalArgumentException();
    _name = name.trim();
    _ssn = ssn;
    return this;
  }
  PersonObj initialize(String name, String ssn) {
    return initialize(name,PersonFactory.newSSN(ssn));
  }
  public String name() { return _name; }
  public SSN ssn() { return _ssn; }
  // TODO: define equals, hashcode, compareTo
  public int compareTo(Person o) { return -1; }
  public Object clone() {
    try {
      return super.clone();
    } catch (CloneNotSupportedException e) {
      throw new RuntimeException(e);
    }
  } 
}    
