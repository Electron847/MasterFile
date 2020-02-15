package csc403;
import java.util.Arrays;

import stdlib.StdIn;
import stdlib.StdOut;
public class FileMaxMin {
	public static void main(String[] args) {
		StdIn.fromFile("data/2Kints.txt");
		int counter = 0;
		while (!StdIn.isEmpty()) {
		String each_line = StdIn.readLine();
		counter++;
		}
		
		Integer[] numbers = new Integer[counter];
		StdOut.println(numbers.length);
		
		StdIn.fromFile("data/2Kints.txt");
		int i = 0;
		while (!StdIn.isEmpty()) {
		String each_line = StdIn.readLine();
		String trimmedLine = each_line.trim();
		int each_number = Integer.parseInt(trimmedLine);
		numbers[i] = each_number;
		//StdOut.println(numbers[i]); correct placement
		i++;
		//StdOut.println(numbers[i]); sends program into null
		}
		
		System.out.println(Arrays.toString(numbers));
		Arrays.sort(numbers);
		System.out.println(Arrays.toString(numbers));
		StdOut.println("Minimum = " + numbers[0]);
		StdOut.println("Maximum = " + numbers[numbers.length-1]);
	}
		
}
