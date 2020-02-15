//Seth Weber
//CSC 403
//Final Exam Question 4
/*
* The pattern formed by the 'First Visit' and 'Finished'
* messages is one of a LIFO (last in first out) pattern
* and this is substantiated by 3 being the the last one 
* with a 'First Visit' tag next to it and the first one
* with a 'Finished' tag next to it.
*/


package csc403;
import algs41.Graph;
import stdlib.*;

public class DepthFirstSearch {
	private final boolean[] marked;    // marked[v] = is there an s-v path?
	private int count;                 // number of vertices connected to s

	// single source
	public DepthFirstSearch(Graph G, int s) {
		marked = new boolean[G.V()];
		dfs(G, s);
	}
	
	public static void dfsPrintTrace(Graph g) {
		// *** Declare and initialize the marked array.
		boolean [] marked = new boolean[g.V()];
	    dfsPrintTrace(g, 0, marked, 0);
	}
	
	private static void dfsPrintTrace(Graph g, int start, boolean[] marked, int indent) {
		
		//build output
		StringBuilder Output = new StringBuilder();
		for (int i = 0; i < indent; i++) {
			Output.append(" ");
		}
		//The first time the method visits a vertex, it should print the message "First time at vertex n", where n is the integer label of the vertex.
		if (marked[start] == false) {
			Output.append("First time at vertex " + start);
			StdOut.println(Output.toString());
			marked [start] = true;
		}
		
		for (int w : g.adj(start)) {
			if (!marked[w]) {
				dfsPrintTrace(g, w, marked, indent + 1);
			}
			else {
				Output = new StringBuilder();
				for (int i = 0; i < indent; i++) {
					Output.append(" ");
				}
				Output.append(" visited " + w + " again");
				StdOut.println(Output.toString());
			}
		}
		
		//When it exits, it should print the message "Finished searching from n".
		Output = new StringBuilder();
		for (int i = 0; i < indent; i++) {
			Output.append(" ");
		}
		Output.append("Finished with " + start);
		StdOut.println(Output.toString());
		
	}
	
	// depth first search from v
	private void dfs(Graph G, int v) {
		marked[v] = true;
		count++;
		for (int w : G.adj(v)) {
			if (!marked[w]) {
				dfs(G, w);
			}
		}
	}

	// is there an s-v path?
	public boolean marked(int v) {
		return marked[v];
	}

	// number of vertices connected to s
	public int count() {
		return count;
	}
	
	public static void main(String[] args) {
		In input = new In("data/tinyG.txt");
		Graph g = new Graph(input);

		dfsPrintTrace(g);
	}

}
