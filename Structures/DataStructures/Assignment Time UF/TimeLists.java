package csc402;

/**
 * This program computes and prints the number of seconds it takes
 * to declare, initialize, and fill with 32,000 integers each of
 * the data structures Bag, ResizingArray, and LinkedList from
 * the algs13 package.
 */
import stdlib.*;
import algs13.*;

public class TimeLists {

	public static void main(String[] args) {
		StdOut.println("--- Times to fill various data structure with 32,000 integers from a file ---");
		String filename = "data/32Kints.txt";
		Stopwatch timer;

		StdIn.fromFile(filename);
		timer = new Stopwatch();
		Bag<Integer> bag = new Bag<>();
		while(!StdIn.isEmpty()) {
			int value = StdIn.readInt();
			bag.add(value);
		}
		StdOut.println("Bag: " + timer.elapsedTime() + " seconds");
		
		StdIn.fromFile(filename);
		timer = new Stopwatch();
		ResizingArray<Integer> resizingarray = new ResizingArray<>();
		while(!StdIn.isEmpty()) {
			int value = StdIn.readInt();
			resizingarray.add(value);
		}
		StdOut.println("Resizing array: " + timer.elapsedTime() + " seconds");

		StdIn.fromFile(filename);
		timer = new Stopwatch();
		LinkedList<Integer> linkedlist = new LinkedList<>();
		while(!StdIn.isEmpty()) {
			int value = StdIn.readInt();
			linkedlist.add(value);
		}
		StdOut.println("Linked list: " + timer.elapsedTime() + " seconds");
	}

}
