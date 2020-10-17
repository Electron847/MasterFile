package serialization.fixedJavaSoftExample;
import java.io.*;

public class CardWriter {
    public static void main(String[] args) {
        Card2 card = new Card2(12, Card2.SPADES);
	System.out.println("Card to write is: " + card);

	try {
	    FileOutputStream out = new FileOutputStream("card.out");
	    ObjectOutputStream oos = new ObjectOutputStream(out);
	    oos.writeObject(card);
	    oos.flush();
	} catch (Exception e) {
	    System.out.println("Problem serializing: " + e);
	}
    }
}
