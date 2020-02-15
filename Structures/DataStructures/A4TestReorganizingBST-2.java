//Rehan Khan
//csc403
package csc403;

import java.util.Arrays;
import stdlib.*;
import week1examples.*;
import week3examples.*;

public class A4TestReorganizingBST {

	public static void main(String[] args) {
		StdIn.fromFile("data/tale.txt");
		String[] words = StdIn.readAllStrings();

		SimplerBST<String, Boolean> bst = new SimplerBST<>();
		A4ReorganizingBST<String, Boolean> robst = new A4ReorganizingBST<>();
		AVLTreeST<String, Boolean> avl = new AVLTreeST<>();

		Stopwatch timer;
		
		StdOut.println("=== Times for words as they appear in the text ===");
		
		// Fill and time the usual BST.
		timer = new Stopwatch();
		for (String word: words) {
			bst.put(word, true);
		}
		StdOut.println("\nElapsed time for usual BST: " + timer.elapsedTime());
		StdOut.println("Height of BST: " + bst.height());
		
		// Fill and time the reorganizing BST.
		timer = new Stopwatch();
		for (String word: words) {
			robst.put(word, true);
		}
		StdOut.println("\nElapsed time for reorganizing tree: " + timer.elapsedTime());
		StdOut.println("Height of reorganized tree: " + robst.height());
		
		// Fill and time the AVL tree.
		timer = new Stopwatch();
		for (String word: words) {
			avl.put(word, true);
		}
		StdOut.println("\nElapsed time for AVL tree: " + timer.elapsedTime());
		StdOut.println("Height of AVL tree: " + avl.height());

		StdOut.println("\n=== Times for sorted words ===");
		
		bst = new SimplerBST<>();
		robst = new A4ReorganizingBST<>();
		avl = new AVLTreeST<>();
		
		Arrays.sort(words);
		
		// Fill and time the usual BST.
		timer = new Stopwatch();
		for (String word: words) {
			bst.put(word, true);
		}
		
		StdOut.println("\nElapsed time for usual BST: " + timer.elapsedTime());
		StdOut.println("Height of BST: " + bst.height());
		
		// Fill and time the reorganizing BST.
		timer = new Stopwatch();
		for (String word: words) {
			robst.put(word, true);
		}
		StdOut.println("\nElapsed time for reorganizing tree: " + timer.elapsedTime());
		StdOut.println("Height of reorganized tree: " + robst.height());
		
		// Fill and time the AVL tree.
		timer = new Stopwatch();
		for (String word: words) {
			avl.put(word, true);
		}
		StdOut.println("\nElapsed time for AVL tree: " + timer.elapsedTime());
		StdOut.println("Height of AVL tree: " + avl.height());
	}
}

