package enumeration;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


// Typesafe enum (From Bloch)
//
public final class Card {
  // Rank of Card
  private final Rank _rank;

  // Suit of Card
  private final Suit _suit;

  // Private constructor: All instances created in the class
  private Card(Rank rank, Suit suit) {
    _rank = rank;
    _suit = suit;
  }

  public String toString() {
    return _rank + " of " + _suit;
  }

  public int compareRank(Card c) {
    return _rank.compareTo(c._rank);
  }

  public int compareSuit(Card c) {
    return _suit.compareTo(c._suit);
  }

  public Rank getRank() {
    return _rank;
  }

  public int getRankValue() {
    return _rank.getValue();
  }

  public Suit getSuit() {
    return _suit;
  }

  public static final List<Card> VALUES;
  static {
    List<Card> values = new ArrayList<Card>(56);
    for (Suit s : Suit.VALUES) {
      for (Rank r : Rank.VALUES) {
        values.add(new Card(r, s));
      }
    }
    VALUES = Collections.unmodifiableList(values);
  }
}
