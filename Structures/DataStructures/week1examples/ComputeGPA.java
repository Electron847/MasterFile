package week1examples;

/*
 * This program creates and fills a symbol table where each entry
 * is a letter grade (the key) and grade points (the value).  
 * It then reads in a sequence of letter grades from a file and, using the 
 * symbol table to look up the grade points for each letter grade,
 * computes and prints the GPA for those grades.  
 * 
 * For the program to run correctly, the file week1grades.txt, which contains
 * the letter grades for which the GPA is being computed, must be in the
 * data directory.
 * 
 * Written by John Rogers for the DS2 (CSC 301/CSC 403) class, Spring, 2018.
 */
import stdlib.*;

public class ComputeGPA {

	public static void main(String[] args) {
		algs31.BinarySearchST<String, Double> gradePoints = new algs31.BinarySearchST<String, Double>();
		gradePoints.put("A+", 4.33);
		gradePoints.put("A", 4.0);
		gradePoints.put("A-", 3.67);
		gradePoints.put("B+", 3.33);
		gradePoints.put("B", 3.0);
		gradePoints.put("B-", 2.67);
		gradePoints.put("C+", 2.33);
		gradePoints.put("C", 2.0);
		gradePoints.put("C-", 1.67);
		gradePoints.put("D", 1.0);
		gradePoints.put("F", 0.0);
		StdIn.fromFile("data/week1grades.txt");
		String[] letterGrades = StdIn.readAllStrings();
		double totalGradePoints = 0.0;
		for (String letterGrade: letterGrades) {
			double gradePoint = gradePoints.get(letterGrade);
			totalGradePoints += gradePoint;
		}
		double gpa = totalGradePoints / letterGrades.length;
		StdOut.println("The GPA is " + gpa + ".");
	}

}
