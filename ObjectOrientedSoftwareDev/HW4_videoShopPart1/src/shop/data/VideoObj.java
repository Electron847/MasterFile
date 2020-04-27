package shop.data;

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
   */
  VideoObj(String title, int year, String director) {
    _title = title;
    _director = director;
    _year = year;
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

  public boolean equals(Object thatObject) {
    if (this == thatObject) {
      return true;
    }
    if(thatObject!=null){
      if ((this.hashCode()==thatObject.hashCode())) {
        return true;
      }
    }
    return false;
  }

  public int hashCode() {
    int hash = 17;
    hash = 37 * hash + _title.hashCode();
    hash = 37 * hash + _year;
    hash = 37 * hash + _director.hashCode();

    return hash;
  }

  public int compareTo(Video that) {
    int res = _title.compareTo(that.title());
    if (res != 0) {
      return res;
    }
    if (_year - that.year() != 0) {
      return _year - that.year();
    }
    res = _director.compareTo(that.director());
    if (res != 0) {
      return res;
    }
    return 0;
  }

  public String toString() {
    StringBuilder res = new StringBuilder();
    res.append(this.title());
    res.append(" (");
    res.append(this.year());
    res.append(") : ");
    res.append(this.director());
    return res.toString();
  }
}
