package week2examples;

/*
 * This program read all of the words in a text file and
 * computes and prints the average length of the words in 
 * that text file.
 */

import stdlib.*;

public class ComputeAverageWordLength {

	public static void main(String[] args) {
		String bookFilePathname = "data/tale.txt";
		StdIn.fromFile(bookFilePathname);
		String[] words = StdIn.readAllStrings();
		int sumOfLengths = 0;
		for (String word: words) {
			sumOfLengths += word.length();
		}
		double averageLength = (double) sumOfLengths/words.length;
		StdOut.printf("In the file %s, the average word length is %5.2f letters.", bookFilePathname, averageLength);
	}

}
