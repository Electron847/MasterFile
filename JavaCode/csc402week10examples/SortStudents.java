package csc402week10examples;

import java.util.*;

import stdlib.*;

public class SortStudents {

	public static void main(String[] args) {
		StdIn.fromFile("data/students.txt");
		ArrayList<Student> students = new ArrayList<>();
		while (!StdIn.isEmpty()) {
			String line = StdIn.readLine();
			String[] fields = line.split("\\s+");
			int id = Integer.parseInt(fields[0]);
			String firstname = fields[1];
			String lastname = fields[2];
			students.add(new Student(id, firstname, lastname));
		}
		
		Collections.sort(students);
		StdOut.println("--- Students ordered by id number ---");
		for (Student student: students) {
			StdOut.println(student);
		}
		
		Collections.sort(students, Student.bylastname);
		StdOut.println();
		StdOut.println("--- Students ordered by lastname ---");
		for (Student student: students) {
			StdOut.println(student);
		}

		Collections.sort(students, (s1, s2) -> s1.firstname().compareTo(s2.firstname()));
		StdOut.println();
		StdOut.println("--- Students ordered by firstname ---");
		for (Student student: students) {
			StdOut.println(student);
		}


	}

}
