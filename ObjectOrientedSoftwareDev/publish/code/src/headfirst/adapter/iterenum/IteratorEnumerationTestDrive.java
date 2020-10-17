package headfirst.adapter.iterenum;

import java.util.*;

public class IteratorEnumerationTestDrive {
	public static void main (String args[]) {
		List<String> l = Arrays.asList(args);
		Enumeration enumeration = new IteratorEnumeration(l.iterator());
		while (enumeration.hasMoreElements()) {
			System.out.println(enumeration.nextElement());
		}
	}
}
