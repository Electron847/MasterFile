package flyweight;

import java.lang.Comparable;

public interface Video extends Comparable<Video> {
  public String director();
  public String title();
  public int year();
  public boolean equals(Object thatObject);
  public int hashCode();
  public int compareTo(Video that);
  public String toString();
}
