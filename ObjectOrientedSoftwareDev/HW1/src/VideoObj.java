/***
 * videoobject file
 */



final class VideoObj implements Comparable<VideoObj> {
  private final String title;
  private final int    year;
  private final String director;


  /***
   *
   * @param title
   * @param year
   * @param director
   */

  VideoObj(String title, int year, String director) {
    if (  (title == null)
            || (director == null)
            || (year <= 1800)
            || (year >= 5000)) {
      throw new IllegalArgumentException();
    }
    this.title = title.trim();
    this.director = director.trim();
    this.year = year;
    if (  ("".equals(title))
            || ("".equals(director))) {
      throw new IllegalArgumentException();
    }
  }

  /***
   * sets the director attribute to video object
   * @return
   */

  public String director() {
    return director;
  }

  /***
   * sets the title attribute to video object
   * @return
   */

  public String title() {
    return title;
  }

  /***
   * sets the year attribute to bideo object
   * @return
   */

  public int year() {
    return year;
  }

  /***
   * Compare the attributes of this object with those of thatObject.
   * @param thatObject the Object to be compared.
   * @return deep equality test between this and thatObject.
   */

  public boolean equals(Object thatObject)
  {
    if (this == thatObject)
    {
      return true;
    }
    if ((this.getClass().equals(thatObject.getClass())) || thatObject == null)
    {
      return true;
    }
    if (this != thatObject)
    {
      return false;
    }
    if (!(this.getClass().equals(thatObject.getClass())))
    {
      return false;
    }
    if (!(thatObject instanceof VideoObj) ) {
      return false;
    }
    //return false;
    return thatObject.equals(this) && this.equals(thatObject);
  }

  /***
   * Return a hash code value for this object using the algorithm from Bloch:
   * fields are added in the following order: title, year, director.
   */
  public int hashCode() {
    int result = 17;
    result = 37*result + title.hashCode();
    result = 37*result + year;
    result = 37*result + director.hashCode();
    return result;
  }

  /***
   * Compares the attributes of this object with those of thatObject, in
   * the following order: title, year, director.
   * @param that the VideoObj to be compared.
   * @return a negative integer, zero, or a positive integer as this
   *  object is less than, equal to, or greater than that object.
   */
  public int compareTo(VideoObj that) {
    int titleDiff = title.compareTo(that.title());
    if (titleDiff != 0) {
      return titleDiff;
    }
    int yearDiff = Integer.compare (year, that.year());
    if (yearDiff != 0) {
      return yearDiff;
    }
    int directorDiff = director.compareTo(that.director());
    if (directorDiff != 0) {
      return directorDiff;
    }
    return 0;
  }

  /***
   * Return a string representation of the object in the following format:
   * <code>"title (year) : director"</code>.
   */
  public String toString() {
    StringBuilder buffer = new StringBuilder();
    buffer.append(title);
    buffer.append(" (");
    buffer.append(year);
    buffer.append(") : ");
    buffer.append(director);
    return buffer.toString();
  }
}