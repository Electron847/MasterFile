/*
CSC 403
Autumn 2018
Assignment 4.2 "A rebalancing BST"
Kristina McChesney
 */

package csc403;

import stdlib.*;
import algs13.Queue;
import java.util.ArrayList;

public class A4ReorganizingBST<K extends Comparable<? super K>, V> {
	private Node<K,V> root;             // root of BST

	private static class Node<K extends Comparable<? super K>,V> {
		public K key;       			// sorted by key
		public V val;             		// associated data
		public Node<K,V> left, right;  	// left and right subtrees

		public Node(K key, V val) {
			this.key = key;
			this.val = val;
		}
	}
	
	/* *********************************************************************
	 *  Search BST for given key, and return associated value if found,
	 *  return null if not found
	 ***********************************************************************/
	// does there exist a key-value pair with given key?


	public A4ReorganizingBST() {}
	/* *********************************************************************
	 *  Search BST for given key, and return associated value if found,
	 *  return null if not found
	 ***********************************************************************/
	// does there exist a key-value pair with given key?
	
	int countChanges = 0; 		// counts how many times a change to a tree was made
								// if more than 100 the tree will be rebuild

	/* ***************************************************************************
	 *  Rebuilds existing Tree from scratch to rebalance 
	 *****************************************************************************/
	private void rebuildTree(){		 			// non-recursive private method
		countChanges = 0; 						// Reset the change counter to 0
		ArrayList<K> aKeys = new ArrayList<K>();		
		for (K i : keys()) {					// Iterate through the Queue
			aKeys.add(i);						// Add value to an array list
		}
												// Call the next method described below to get a queue of the keys in median order
		Node <K,V> newRoot;						// Declare a Node variable; this will be the root of the new and balanced tree 
		for (K key: listInMedianOrder(aKeys)){  // Iterate through the queue returned by that method and call the public put, 
			put(key,get(key));					//	passing it the root of the new tree
		}										
		newRoot = this.root; 					// Change the root instance variable to refer to this new root
	}


	/* ***************************************************************************
	 *  Getting the medians in the array of keys and return the queue of those keys 
	 *****************************************************************************/
	private Iterable<K> listInMedianOrder (ArrayList<K> arr){ 
		int len = arr.size();
		Queue<K> qMedian = new Queue<>();				// Create three queues: median queue, left index queue, right index queue; 
		Queue<Integer> qLeftIndex = new Queue<>();		// the first one will hold keys,	
		Queue<Integer> qRightIndex = new Queue<>();		// the other two will hold integer indices
		qLeftIndex.enqueue(0);							// enqueue 0 onto the left index queue
		qRightIndex.enqueue(len-1);						// and the length of the array minus one on the right index queue
		while (!qLeftIndex.isEmpty()){					//	while the left index queue is not empty:
			int left = qLeftIndex.dequeue();			// 		let left be the dequeue of the left index queue and 
			int right = qRightIndex.dequeue();			// 		right the same for the right index queue
			if (left <= right) {						//		if left <= right then
				int medianIndex = (left + right)/2; 	//		    let median index by the average of left and right
				qMedian.enqueue(arr.get(medianIndex));	// 			enqueue the key in the array at the median index onto the median queue
				qLeftIndex.enqueue(left);				//	    	enqueue left and the median index minus one onto the left index queue
				qLeftIndex.enqueue(medianIndex+1);
				qRightIndex.enqueue(medianIndex-1);		//			enqueue right and the median index plus one onto the right index queue	
				qRightIndex.enqueue(right);
			}
		}
		return qMedian;									//		return the median queue
	}
	public boolean contains(K key) {
		return get(key) != null;
	}

	// return value associated with the given key, or null if no such key exists
	public V get(K key) { return get(root, key); }
	private V get(Node<K,V> x, K key) {
		if (x == null) return null;
		int cmp = key.compareTo(x.key);
		if      (cmp < 0) return get(x.left, key);
		else if (cmp > 0) return get(x.right, key);
		else              return x.val;
	}

	/* *********************************************************************
	 *  Insert key-value pair into BST
	 *  If key already exists, update with new value
	 ***********************************************************************/
	public void put(K key, V val) {
		countChanges += 1;		// every time change is made counter is incremented
		if (countChanges >=100) {rebuildTree(); return;}
		// the tree is rebuild if more than 100 changes were made
		if (val == null) { delete(key); return; }
		root = put(root, key, val);
	}

	private Node<K,V> put(Node<K,V> x, K key, V val) {
		if (x == null) return new Node<>(key, val);
		int cmp = key.compareTo(x.key);
		if      (cmp < 0)
			x.left  = put(x.left,  key, val);
		else if (cmp > 0)
			x.right = put(x.right, key, val);
		else
			x.val   = val;
		return x;
	}

	/* *********************************************************************
	 *  Delete
	 ***********************************************************************/

	public void delete(K key) {
		root = delete(root, key);
		countChanges += 1;		// every time change is made counter is incremented
		if (countChanges >=100) {rebuildTree(); return;}
		// the tree is rebuild if more than 100 changes were made
	}

	private Node<K,V> delete(Node<K,V> x, K key) {
		if (x == null) return null;
		int cmp = key.compareTo(x.key);
		if      (cmp < 0) x.left  = delete(x.left,  key);
		else if (cmp > 0) x.right = delete(x.right, key);
		else {
			// x is the node to be deleted.  
			// The value returned in each of these cases below
			// becomes the value of the child reference from
			// the parent of x.  Returning a null makes that
			// reference a null and so cuts x off, causing its
			// automatic deletion.

			// Determine how many children x has.
			if (x.right == null && x.left == null){
				// This is a leaf node.
				return null;
			} else if (x.right == null) {
				// One child, to the left.
				return x.left;
			} else if (x.left == null) {
				// One child, to the right.
				return x.right;
			} else {
				// Node x has two children.
				// Find the node in x's right subtree with
				// the minimum key.
				Node<K,V> rightTreeMinNode = findMin(x.right);
				x.key = rightTreeMinNode.key;
				x.val = rightTreeMinNode.val;
				x.right = delete(x.right, rightTreeMinNode.key);
			}
		}
		return x;
	}

	private Node<K,V> findMin(Node<K,V> x) {
		if (x.left == null) return x;
		else return findMin(x.left);
	}

	public void printKeys() {
		printKeys(root);
	}

	private void printKeys(Node<K,V> x) {
		if (x == null) return;
		printKeys(x.left);
		StdOut.println(x.key);
		printKeys(x.right);
	}

	public Iterable<K> keys() {
		Queue<K> q = new Queue<>();
		inOrder(root, q);
		return q;
	}

	private void inOrder(Node<K,V> x, Queue<K> q) {
		if (x == null) return;
		inOrder(x.left, q);
		q.enqueue(x.key);
		inOrder(x.right, q);
	}

	public int height() {
		return height(root);
	}

	private int height(Node<K,V> x) {
		if (x == null) return -1;
		return 1+Math.max(height(x.left), height(x.right));
	}

	/* ***************************************************************************
	 *  Visualization
	 *****************************************************************************/

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
