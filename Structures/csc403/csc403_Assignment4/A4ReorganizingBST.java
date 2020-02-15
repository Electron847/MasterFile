//Seth Weber
//csc403 Data Structures II 
//Homework Assignment 4
package csc403;

import stdlib.*;
import java.util.ArrayList;
import java.util.Collections;
import algs13.Queue;

public class A4ReorganizingBST<K extends Comparable<? super K>, V> {
	private Node<K, V> root;
	private int counter = 0;
	public int rebuildCounter = 0;
	
	private static class Node<K extends Comparable<? super K>, V> {
		public K key;
		public V val;
		public Node<K, V> left, right;

		public Node(K key, V val) {
			this.key = key;
			this.val = val;
		}
	}
	public A4ReorganizingBST() {}
	
	//int counter = 0;
	//int rebuildCounter = 0;
	
	private void rebuildTree()
	{
		rebuildCounter++;
		counter = 0;
		Iterable<K> key = keys(); // This line is needed in order to activate line 38
								  // It will not affect performance if line 37 is activated instead
		ArrayList<K> orderedKeys = new ArrayList<K>();
		//for(K k : keys()) //
		for(K k:key) //
		{
			orderedKeys.add(k);
		}
		Collections.sort(orderedKeys); // 
		Node<K,V> newRoot = null;
		Queue<K> keyQ = InMedianOrder(orderedKeys); // This line is needed to activate line 47
		//for(K i : InMedianOrder(orderedKeys)) //
		for(K i:keyQ) //
		{
			if(newRoot == null) {
				newRoot = new Node <K,V> (i, get(i));
				newRoot = put(newRoot,i,get(i));
			}
			//put(i, get(i));
			//newRoot = this.root;
			//newRoot = put(newRoot,i,get(i));
			}
		this.root = newRoot;
		//newRoot = this.root;
	}
	private Queue<K> InMedianOrder(ArrayList<K> list) //
	//private Iterable<K> InMedianOrder (ArrayList<K> arr) //
	{
		//int arr_len = arr.size(); //
		Queue<K> medianQueue = new Queue<>();
		Queue<Integer> queueLeftIndex = new Queue<> ();
		Queue<Integer> queueRightIndex = new Queue<> ();
		
		Iterable<K> keys = medianQueue; //
		queueLeftIndex.enqueue(0);
		queueRightIndex.enqueue(list.size()-1); //
		//queueRightIndex.enqueue(arr_len-1); //
		while(!queueLeftIndex.isEmpty())
		{
			int left = queueLeftIndex.dequeue();
			int right = queueRightIndex.dequeue();
			if( left <= right)
			{
				int median = ((left+right)/2);
				medianQueue.enqueue(list.get(median)); //
				//medianQueue.enqueue(arr.get(median)); //
				queueLeftIndex.enqueue(left);
				queueLeftIndex.enqueue(median+1);
				queueRightIndex.enqueue(median-1);
				queueRightIndex.enqueue(right);
			}
			
		}
		return medianQueue;
	}

	public boolean contains(K key) {
		return get(key) != null;
	}

	public V get(K key) {
		return get(root, key);
	}

	private V get(Node<K, V> x, K key) {
		if (x == null)
			return null;
		int cmp = key.compareTo(x.key);
		if (cmp < 0)
			return get(x.left, key);
		else if (cmp > 0)
			return get(x.right, key);
		else
			return x.val;
	}

	public void put(K key, V val) {
		counter ++;
		if( counter >= 100) { 
			rebuildTree(); 
			return; 
			}
		if (val == null) 
		{
			delete(key);
			return;
		}
		root = put(root, key, val);
		
	
	}

	private Node<K, V> put(Node<K, V> x, K key, V val) {
		if (x == null)
			return new Node<>(key, val);
		int cmp = key.compareTo(x.key);
		if (cmp < 0)
			x.left = put(x.left, key, val);
		else if (cmp > 0)
			x.right = put(x.right, key, val);
		else
			x.val = val;
		return x;
	}

	public void delete(K key) {
		root = delete(root, key);
		counter++;
		if(counter >= 100){ 
			rebuildTree(); 
			return;
			}
	}

	private Node<K, V> delete(Node<K, V> x, K key) {
		if (x == null)
			return null;
		int cmp = key.compareTo(x.key);
		if (cmp < 0)
			x.left = delete(x.left, key);
		else if (cmp > 0)
			x.right = delete(x.right, key);
		else {

			if (x.right == null && x.left == null) {
				return null;
			} else if (x.right == null) {
				return x.left;
			} else if (x.left == null) {
				return x.right;
			} else {
				Node<K, V> rightTreeMinNode = findMin(x.right);
				x.key = rightTreeMinNode.key;
				x.val = rightTreeMinNode.val;
				x.right = delete(x.right, rightTreeMinNode.key);
			}
		}
		return x;
	}
	
// Below are the provided functions within SimplerBST()

	private Node<K, V> findMin(Node<K, V> x) {
		if (x.left == null)
			return x;
		else
			return findMin(x.left);
	}

	public void printKeys() {
		printKeys(root);
	}

	private void printKeys(Node<K, V> x) {
		if (x == null)
			return;
		printKeys(x.left);
		StdOut.println(x.key);
		printKeys(x.right);
	}

	public Iterable<K> keys() {
		Queue<K> q = new Queue<>();
		inOrder(root, q);
		return q;
	}

	private void inOrder(Node<K, V> x, Queue<K> q) {
		if (x == null)
			return;
		inOrder(x.left, q);
		q.enqueue(x.key);
		inOrder(x.right, q);
	}

	public int height() {
		return height(root);
	}

	private int height(Node<K, V> x) {
		if (x == null)
			return -1;
		return 1 + Math.max(height(x.left), height(x.right));
	}
	
	public void drawTree() {
		if (root != null) {
			StdDraw.setPenColor (StdDraw.BLACK);
			StdDraw.setCanvasSize(1200,700);
			drawTree(root, .5, 1, .25, 0);
		}
	}
	private void drawTree (Node<K,V> n, double x, double y, double range, int depth) {
		int CUTOFF = 10;
		StdDraw.text (x, y, n.key.toString ());
		StdDraw.setPenRadius (.007);
		if (n.left != null && depth != CUTOFF) {
			StdDraw.line (x-range, y-.08, x-.01, y-.01);
			drawTree (n.left, x-range, y-.1, range*.5, depth+1);
		}
		if (n.right != null && depth != CUTOFF) {
			StdDraw.line (x+range, y-.08, x+.01, y-.01);
			drawTree (n.right, x+range, y-.1, range*.5, depth+1);
		}
	}

	}
