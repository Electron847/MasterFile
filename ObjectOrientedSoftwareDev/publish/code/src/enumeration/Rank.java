package enumeration;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;


// Ordinal-based typesafe enum (From Bloch)
//
// No need to override equals, since only one instance of each rank.
// Note that _instanceNum and _name are instance fields.
// Note that cNumInstances is a class field.
// Note that the order of the constant definitions is important.
// Note that VALUES is an immutable collection.
// Java arrays are always mutable :-(
public final class Rank implements Comparable<Rank> {
  // Number of instances
  private static int cNumInstances = 0;

  // Ordinal for this instance
  private final int _instanceNum;

  // Name of Rank
  private final String _name;

  // Private constructor: All instances created in the class
  private Rank(String name) {
    _name = name;
    _instanceNum = Rank.cNumInstances++;
  }

  public String toString() {
    return _name;
  }

  public int compareTo(Rank that) {
    return this._instanceNum - that._instanceNum;
  }

  public int getValue() {
    if (this == ACE_HIGH) {
      return 1;
    } else {
      return _instanceNum + 1;
    }
  }

  public static final Rank ACE_LOW = new Rank("ace");
  public static final Rank TWO = new Rank("two");
  public static final Rank THREE = new Rank("three");
  public static final Rank FOUR = new Rank("four");
  public static final Rank FIVE = new Rank("five");
  public static final Rank SIX = new Rank("six");
  public static final Rank SEVEN = new Rank("seven");
  public static final Rank EIGHT = new Rank("eight");
  public static final Rank NINE = new Rank("nine");
  public static final Rank TEN = new Rank("ten");
  public static final Rank JACK = new Rank("jack");
  public static final Rank QUEEN = new Rank("queen");
  public static final Rank KING = new Rank("king");
  public static final Rank ACE_HIGH = new Rank("ace");
  public static final List<Rank> VALUES;
  static {
    Rank[] values = {
      ACE_LOW, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING, ACE_HIGH
    };
    VALUES = Collections.unmodifiableList(Arrays.asList(values));
  }
}
