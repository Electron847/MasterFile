package csc402week10examples;

import java.util.Comparator;


public class Student implements Comparable<Student> {
	private Integer id;
	private String lastname;
	private String firstname;
	public static final Comparator<Student> bylastname = new ByLastname();
	
	public Student(Integer id, String lastname, String firstname) {
		this.id = id;
		this.lastname = lastname;
		this.firstname = firstname;
	}
	
	public Integer id() {return id;}
	public String lastname() {return lastname;}
	public String firstname() {return firstname;}
	
	public String toString() {
		return id+"\t"+lastname+", "+firstname;
	}
	
	public int compareTo(Student that) {
		return this.id.compareTo(that.id);
	}
	
	private static class ByLastname implements Comparator<Student> {
		public int compare(Student s1, Student s2) {
			return s1.lastname.compareTo(s2.lastname);
		}
	}
}
