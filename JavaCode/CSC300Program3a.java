package algs11;

import java.util.Arrays;
import stdlib.*;

/**
 * Version 1.0
 * 
 *   Your Name goes here
 *   You class section  goes here
 *   
 *   Does your program have compile errors?     Yes / No
 *   Indicate which TODOs have been completed correctly   
 *   TODO 1    Correct / Incorrect
 *   TODO 2 	   Correct / Incorrect
 *   
 * This is a skeleton file for your homework. Edit the sections marked TODO. You
 * may also edit the function "main" to test your code.
 *
 * You must not change the declaration of any method. This will be true of every
 * skeleton file I give you.
 *
 * For example, you will get zero points if you change the line
 * <pre>
 *     public static double minValue (double[] list) {
 * </pre>
 * to something like
 * <pre>
 *     public static void minValue (double[] list) {
 * </pre>
 * or
 * <pre>
 *     public static double minValue (double[] list, int i) {
 * </pre>
 * 
 * Each of the functions below is meant to be SELF CONTAINED. This means that
 * you should use no other functions or classes unless otherwise indicated. 
 * You should not use any HashSets or ArrayLists, or anything else! 
 * In addition, each of your functions should go
 * through the argument array at most once. The only exception to this
 * removeDuplicatesAndReverse, which is allowed to call numUnique and then go through the
 * array once after that.
 */
public class CSC300Program3a {

	/**
	 * numUnique returns the number of unique values in an array of ints.
	 * Precondition: the array may be empty, but if it is not empty the array is sorted from low to high.
	 *                                                    { your solution can assume this is true }
	 *
	 * Your solution must go through the array exactly once. Your solution must
	 * not call any other functions. Here are some examples (using "=="
	 * informally):
	 *
	 * <pre>
	 *     0 == numUnique(new int[] { })
	 *     1 == numUnique(new int[] { 11 })
	 *     1 == numUnique(new int[] { 11, 11, 11, 11 })
	 *     8 == numUnique(new int[] { 11, 11, 11, 11, 22, 33, 44, 44, 44, 44, 44, 55, 55, 66, 77, 88, 88 })
	 *     8 == numUnique(new int[] { 11, 22, 33, 44, 44, 44, 44, 44, 55, 55, 66, 77, 88 })
	 * </pre>
	 */
	public static int numUnique (int[] list) {
		
		return -1; //TODO1: fix this
	}

	/**
	 * deDupAndReverse returns a new array containing the unique values in the
	 * original array reversed. There should not be any extra space in the array --- there should
	 * be exactly one space for each unique element (Hint: numUnique tells you
	 * how big the array should be). You may assume that the list is sorted, as
	 * you did for numUnique.
	 *
	 * Your solution may call numUnique, but should not call any other
	 * functions. After the call to numUnique, you must go through the array
	 * exactly one more time. Here are some examples (using "==" informally):
	 *
	 *	Precondition: the array may be empty, but if it is not empty the array is sorted from low to high.
	 *                                                     { your solution can assume this is true }
	 *
	 * <pre>
	 *   new int[] { }    == deDupAndReverse(new int[] { })
	 *   new int[] { 11 } == deDupAndReverse(new int[] { 11 })
	 *   new int[] { 11 }  == deDupAndReverse(new int[] { 11, 11, 11, 11 })
	 *   new int[] { 88, 77, 66, 55, 44, 33, 22, 11 }  == deDupAndReverse(new int[] { 11, 11, 11, 11, 22, 33, 44, 44, 44, 44, 44, 55, 55, 66, 77, 88, 88 })
	 *   new int[] { 88, 77, 66, 55, 44, 33, 22, 11 }  == deDupAndReverse(new int[] { 11, 22, 33, 44, 44, 44, 44, 44, 55, 55, 66, 77, 88 })
	 * </pre>
	 */
	public static int[] deDupAndReverse (int[] list) {
		
		return list; // TODO2: fix this
	}

	/**
	 * A test program, using private helper functions.  See below.
	 * To make typing tests a little easier, I've written a function to convert strings to arrays.  See below.
	 */
	public static void main (String[] args) {		
		// for numUnique: array must be sorted
		testNumUnique (0, new int[] {});
		testNumUnique(1,new int[] {11});
		testNumUnique(1,new int[] {11,11,11,11});
		testNumUnique(4,new int[] {11,21,31,41});
		testNumUnique(4,new int[] {11,11,11,21,31,31,31,31,41});
		testNumUnique(4,new int[] {11,21,21,21,31,41,41,41,41});
		testNumUnique(4,new int[] {11,11,21,21,21,31,31,41,41,41,41});
		testNumUnique(8,new int[] {11,11,11,11,21,31,41,41,41,41,41,51,51,61,71,81,81});
		testNumUnique(8,new int[] {11,21,31,41,41,41,41,41,51,51,61,71,81});
		testNumUnique(7,new int[] {11,11,11,11,21,31,41,41,41,41,41,51,51,61,71});
		testNumUnique(7,new int[] {11,21,31,41,41,41,41,41,51,51,61,71});
		testNumUnique(8,new int[] {-81,-81,-81,-81,-71,-61,-51,-51,-51,-51,-41,-41,-31,-21,-11,-11,-11});

		// for removeDuplicates: array must be sorted
		testDeDupAndReverse (new int[] {}, new int[] {});
		testDeDupAndReverse (new int[] {11}, new int[] {11} );  
		testDeDupAndReverse (new int[] {11}, new int[] {11,11,11,11} );  
		testDeDupAndReverse (new int[] {41,31,21,11}, new int[] {11,21,31,41} ); 
		testDeDupAndReverse (new int[] {41,31,21,11}, new int[] {11,11,11,21,31,31,31,31,41} ); 
		
		testDeDupAndReverse(new int[] {41,31,21,11} , new int[] {11,21,21,21,31,41,41,41,41} );
		testDeDupAndReverse(new int[] {41,31,21,11} , new int[] {11,11,21,21,21,31,31,41,41,41,41} );
		testDeDupAndReverse(new int[] {81,71,61,51,41,31,21,11} , new int[] {11,11,11,11,21,31,41,41,41,41,41,51,51,61,71,81,81} );
		testDeDupAndReverse(new int[] {81,71,61,51,41,31,21,11} , new int[] {11,21,31,41,41,41,41,41,51,51,61,71,81} );
		testDeDupAndReverse(new int[] {81,71,61,51,41,31,21,11} , new int[] {11,11,11,11,21,31,41,41,41,41,41,51,51,61,71,71,81} );
		testDeDupAndReverse(new int[] {81,71,61,51,41,31,21,11} , new int[] {11,21,31,41,41,41,41,41,51,51,61,71,81,81} );
		testDeDupAndReverse(new int[] {-11,-21,-31,-41,-51,-61,-71,-81} , new int[] {-81,-81,-81,-81,-71,-61,-51,-51,-51,-51,-41,-41,-31,-21,-11,-11,-11} );
		StdOut.println ("Finished tests");
		
	}

	private static void testNumUnique (int expected, int[] list) {
	
		int actual = numUnique (list);

		if (expected != actual) {
			StdOut.format ("Failed numUnique([%s]): Expecting (%d) Actual (%d)\n", Arrays.toString(list), expected, actual);
		}
	}
	private static void testDeDupAndReverse (int[] expected, int[] list) {
	
		int[] actual = deDupAndReverse (list);

		// != operator does not do what we want on arrays, use   equals   function from Arrays  class
		if (! Arrays.equals (expected, actual)) {
			StdOut.format ("Failed deDupAndReverse([%s]): Expecting (%s) Actual (%s)\n", Arrays.toString(list), Arrays.toString (expected), Arrays.toString (actual));
		}
	}

}