package week2examples;

/*
 * This program prints a table of letter frequencies for a 
 * given text file.
 */
import algs31.*;
import stdlib.*;

public class LetterFrequencies {

	public static void main(String[] args) {
		BinarySearchST<Character, Integer> letterCounts = new BinarySearchST<Character, Integer>();
		String textFilename = "data/twotales.txt";
		StdIn.fromFile(textFilename);
		String text = StdIn.readAll();
		char[] characters = text.toLowerCase().toCharArray();
		for (char character: characters) {
			if (Character.isLetter(character)) {
				if (letterCounts.get(character) == null) {
					// A null entry in an ST means that no value is stored for that key.
					letterCounts.put(character, 1);
				} else {
					letterCounts.put(character, letterCounts.get(character)+1);
				}
			}
		}
		StdOut.println("Letter frequences for " + textFilename);
		for (Character letter: letterCounts.keys()) {
			StdOut.println(letter + ": " + letterCounts.get(letter));
		}
		
	}
}
