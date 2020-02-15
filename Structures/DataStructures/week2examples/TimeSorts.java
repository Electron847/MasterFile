package week2examples;

import java.util.Formatter;
/*
 * This program demonstrates the relative speed of selection
 * sort vs. merge sort on large arrays.  
 */
import java.util.Random;
import algs21.Selection;
import algs22.Merge;
import stdlib.*;

public class TimeSorts {

	public static void main(String[] args) {
		String header =             "Array size";
		String selectionSortTimes = "Selection ";
		String mergeSortTimes =     "Merge     ";
		for (int size = 50000; size <= 200000; size += 50000) {
			Formatter format = new Formatter();
			header += format.format("%8d", size).toString();
			Integer[] arraySS = new Integer[size];
			Integer[] arrayMS = new Integer[size];
			Random r = new Random();
			for (int i = 0; i < arraySS.length; i++) {
				int randomValue = r.nextInt();
				arraySS[i] = randomValue;
				arrayMS[i] = randomValue;
			}

			Stopwatch timer = new Stopwatch();
			Selection.sort(arraySS);
			format = new Formatter();
			selectionSortTimes += format.format("%8.3f", timer.elapsedTime()).toString();

			timer = new Stopwatch();
			format = new Formatter();
            Merge.sort(arrayMS);
			mergeSortTimes += format.format("%8.4f", timer.elapsedTime()).toString();
		}
		StdOut.println(header);
		StdOut.println("--------");
		StdOut.println(selectionSortTimes);
		StdOut.println(mergeSortTimes);
	}

}
