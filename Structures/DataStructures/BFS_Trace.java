//Seth Weber
//CSC 403
//Final Exam Question 5

package csc403;

import algs13.Queue;
import algs41.Graph;
import stdlib.In;
import stdlib.StdOut;

public class BFSTrace {
	private static final int INFINITY = Integer.MAX_VALUE;
	private static boolean[] marked; 
	private static int[] distTo;
	private static int[] edgeTo; 
	private static int s;
	
	public static void main(String[] args) {
		In input = new In("data/tinyG.txt");
		Graph g = new Graph(input);

		bfsPrintTrace(g);
	}
	
	public static void bfsPrintTrace(Graph g) {
			marked = new boolean[g.V()];
			distTo = new int[g.V()];
			edgeTo = new int[g.V()];
			bfsPrintTrace(g, 0);
		}
	
	
	private static void bfsPrintTrace(Graph g, int s) {
		Queue<Integer> q = new Queue<>();
		for (int v = 0; v < g.V(); v++) distTo[v] = INFINITY;
		distTo[s] = 0;
		marked[s] = true;
		q.enqueue(s);
		StdOut.println("Enqueued "+ s);

		while (!q.isEmpty()) {
			int v = q.dequeue();
			StdOut.println("Dequeued "+v);
			for (int w : g.adj(v)) {
				if (!marked[w]) {
					edgeTo[w] = v;
					distTo[w] = distTo[v] + 1;
					marked[w] = true;
					q.enqueue(w);
					StdOut.println("Enqueued "+w);
				}
			}
		}
	}
}