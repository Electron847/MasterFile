package csc402week10examples;

import stdlib.*;
import java.util.Random;
import algs24.*;

public class HeapSort {
	
	public static <T extends Comparable<T>> void sort(T[] a) {
		MinPQ<T> pq = new MinPQ<>();
		for (T element: a) {
			pq.insert(element);
		}
		for (int i = 0; i < a.length; i++) {
			a[i] = pq.delMin();
		}
	}

	public static void main(String[] args) {
		Integer[] a = new Integer[16];
		Random r = new Random();
		// Fill a with randomly chosen values in the range -500 to 500
		for (int i = 0; i < a.length; i++) {
			a[i] = r.nextInt(1001) - 500;
		}
		for (int element: a) {
			StdOut.print(element+" ");
		}
		StdOut.println();
		sort(a);
		for (int element: a) {
			StdOut.print(element+" ");
		}
	}
}
