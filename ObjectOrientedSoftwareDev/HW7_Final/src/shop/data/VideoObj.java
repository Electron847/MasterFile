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
		  throw new IllegalArgumentException("Movie title empty");
	  }
	  if(title==" ") {
		  throw new IllegalArgumentException("Movie title is spaces");
	  }
	  if(director=="") {
		  throw new IllegalArgumentException("Movie director empty");
	  }
	  if(year>=5000|year<=1800) {
		  throw new IllegalArgumentException("Movie year outside of range");
	  }
	  
	  if(!(year>=0|year<=0)) {
		  throw new IllegalArgumentException("Movie year not an integer");
	  }
	  title = title.trim();	  
	  director = director.trim();
	  _title = title; 
	  _year = year;
	  _director = director;
  }

  public String director() {
    return _director;
  }
  public String title() {
    return _title;
  }
  public int year() {
    return _year;
  }
  
  public int[] phrase(String s) {
	    String name = s; // String to check it's value
	    int nameLength = name.length(); // length of the string used for the loop
	    int[] ascii = new int[name.length()];
	    for(int i = 0; i < nameLength ; i++){   // while counting characters if less than the length add one        
	        char character = name.charAt(i); // start on the first character
	        ascii[i] = (int) character; //convert the first character
	    }
	    return ascii;
  }

  public boolean equals(Object o) {
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
		    int tas = _title.hashCode();
		    int das = _director.hashCode();
	  long result = 17;
		  result = 37*result + tas;
		  result = 37*result + _year;
		  result = 37*result + das;
		  return (int) result; 
	  }
  

  public int compareTo(Video o) {
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
		  String s1 = String.format("%s (%d) : %s  ",_title,_year,_director);
		  return s1;

	  }
}
