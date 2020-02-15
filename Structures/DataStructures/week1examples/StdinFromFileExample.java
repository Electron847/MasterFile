package week1examples;

/*
 * This program reads an input file line by line.  Each line
 * read in has three values.  The first is a name, the second and 
 * third are scores.  It converts the scores, which are read in as
 * strings, to numeric values and computes their average.  It prints
 * the name and the average.  
 * 
 * For the program to run correctly, the file week1scores.txt must be in the
 * data directory.
 * 
 * Written by John Rogers for the DS2 (CSC 301/CSC 403) class, Spring, 2018.
 */

import stdlib.*;

public class StdinFromFileExample {

	public static void main(String[] args) {
		StdIn.fromFile("data/week1scores.txt");
		while (StdIn.hasNextLine()) {
			String line = StdIn.readLine();
			String[] fields = line.split("\\s+");
			String name = fields[0];
			int score1 = Integer.parseInt(fields[1]);
			int score2 = Integer.parseInt(fields[2]);
			double average = (score1 + score2) / 2.0;
			StdOut.println(name + ": " + average);
		}
	}

}
