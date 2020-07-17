package shop.data;

import java.util.Arrays;

/**
 * Implementation of Video interface.
 * @see Data
 */
final class VideoObj implements Video {
  private final String _title;
  private final int    _year;
  private final String _director;

  /**
   * Initialize all object attributes.
   * Title and director are "trimmed" to remove leading and final space.
   * @throws IllegalArgumentException if object invariant violated.
   */
  VideoObj(String title, int year, String director) {
	  if(title==null) {
		  throw new IllegalArgumentException("Movie title is null");
	  }
	  if(director==null) {
		  throw new IllegalArgumentException("Movie director is null");
	  }
	  if(title=="") {
		  throw new IllegalArgumentException("Movie title is empty");
	  }
	  if(title==" ") {
		  throw new IllegalArgumentException("Movie title is spaces");
	  }
	  if(director=="") {
		  throw new IllegalArgumentException("Movie director is empty");
	  }
	  if(year>=5000|year<=1800) {
		  throw new IllegalArgumentException("Movie year outside of requirement range");
	  }
	  
	  if(!(year>=0|year<=0)) {
		  throw new IllegalArgumentException("Movie year is not an integer");
	  }
	  title = title.trim();	  
	  director = director.trim();
	  _title = title; 
	  _year = year;
	  _director = director; 
  
  }

  public String director() {
    // TODO
    return _director;
  }

  public String title() {
    // TODO
    return _title;
  }

  public int year() {
    // TODO
    return _year;
  }

  
  public int[] phrase(String s) {
	    String name = s; // String to check it's value
	    int nameLength = name.length(); // length of the string used for the loop
	    int[] ascii = new int[name.length()];
	    for(int i = 0; i < nameLength ; i++){
	        char character = name.charAt(i);
	        ascii[i] = (int) character;
	    }
	    return ascii;
	  
  }

  public boolean equals(Object o) {
	    // TODO->finished
		  if (o == null) return false;
		  if (o == this) {
	            return true;
	                     
	        } 
	       
		  if (!(o instanceof VideoObj)) { 
	            return false;
	        }
		  VideoObj c = (VideoObj) o;
		  boolean a1 = Arrays.equals(phrase(this._title), phrase(c._title));
		  boolean b1 = Arrays.equals(phrase(this._director), phrase(c._director));
		  boolean c1 = this._year == c._year;
		  boolean d1 = a1 && b1 && c1;
		
		return d1;

	  }

  
  public int hashCode() {
	    // TODO->finished
		    int tas = _title.hashCode();
		    int das = _director.hashCode();
	  long result = 17;
		  result = 37*result + tas; //titleInt();
		  result = 37*result + _year;
		  result = 37*result + das;//directorInt();
		  return (int) result; 
	  }
  

  public int compareTo(Video o) {
    // TODO->finished
	  int result2 = 0;
	  if(this.hashCode() > o.hashCode()) {
		  return 1;
	  }else if(this.hashCode() < o.hashCode()) {
		  return -1;
	  }
	  
	  if(this.equals(o)) {
		  return 0;
	  }else if(!this.equals(o)) {
	  
		  throw new IllegalArgumentException("compareTo(equals()) returned false");
	  }

	  return -2;

  }

  
  public String toString() {
	    // TODO->finished
		  String s1 = String.format("%s (%d) : %s  ",_title,_year,_director);
		  return s1;

	  }
}
