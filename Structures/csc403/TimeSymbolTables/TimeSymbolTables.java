package csc403;

import algs32.BST;
import algs31.SequentialSearchST;
import algs31.BinarySearchST;
import stdlib.*;

public class TimeSymbolTables {

	public static void main(String[] args) {
		SequentialSearchST<String, Integer> sequentialST = new algs31.SequentialSearchST<>();
		StdIn.fromFile("data/tinyTale.txt");
		Stopwatch timer = new Stopwatch();
		String[] words = StdIn.readAllStrings();
		for (int i = 0; i < words.length; i++) {
			String word = words[i];
			if (!sequentialST.contains(word));{
				sequentialST.put(word, 1);
				//else sequentialST.put(word, sequentialST.get(word) + 1);
			}
			sequentialST.put(word, sequentialST.get(word) + 1);
		}
	StdOut.println("timing for a sequential search symbol Table (SequentialSearchST) was: " + timer.elapsedTime() + " seconds.");		
		BST<String, Integer> BST = new BST<>();
		timer = new Stopwatch();
		for (int i = 0; i < words.length; i++) {
			String word = words[i];
			if (!BST.contains(word)) {
			BST.put(word, 1);
			}
			else BST.put(word, BST.get(word) + 1);
		}
		StdOut.println("timing for a binary symbol table (BST) was: "+ timer.elapsedTime()+" seconds");
	}
}
