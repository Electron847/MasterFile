package serialization.fixedJavaSoftExample;
import java.io.*;

public class CardReader {
    public static void main(String[] args) {
	Card2 card = null;

	try {
	    FileInputStream in = new FileInputStream("card.out");
	    ObjectInputStream ois = new ObjectInputStream(in);
	    card = (Card2)(ois.readObject());
	} catch (Exception e) {
	    System.out.println("Problem serializing: " + e);
	}

	System.out.println("Card read is: " + card);

    }
}
