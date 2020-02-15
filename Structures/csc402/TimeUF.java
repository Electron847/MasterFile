package timeuf;
import stdlib.*;
import algs15.*;

//quckfind took: 6.572000 seconds.
//quckunionuf took: 0.007000 seconds.
//weighted took: 0.010000 seconds.
//compression took: 0.006000 seconds.

//quckfind took: 6.656000 seconds.
//quckunionuf took: 0.009000 seconds.
//weighted took: 0.014000 seconds.
//compression took: 0.008000 seconds.



public class TimeUF {

	public static void main(String[] args) {
	
		Stopwatch timer;
		StdIn.fromFile("data/uf16k.txt");
		int count = StdIn.readInt();
		
		timer = new Stopwatch();
		QuickFindUF uf = new QuickFindUF(count);
		while(!StdIn.isEmpty()) {
			int p = StdIn.readInt();
			int q = StdIn.readInt();
			if (uf.connected(p, q)) continue;
			uf.union(p, q);
			
		}
		StdOut.format("quckfind took: %f seconds.\n", timer.elapsedTime());
		
		//quickunionuf
		timer = new Stopwatch();
		QuickUnionUF un = new QuickUnionUF(count);
		while(!StdIn.isEmpty()) {
			int p = StdIn.readInt();
			int q = StdIn.readInt();
			if (un.connected(p, q)) continue;
			un.union(p, q);
			
		
		
	}
		StdOut.format("quckunionuf took: %f seconds.\n", timer.elapsedTime());	
		
		//weighted union find
		timer = new Stopwatch();
		WeightedUF wuf = new WeightedUF(count);
		while(!StdIn.isEmpty()) {
			int p = StdIn.readInt();
			int q = StdIn.readInt();
			if (wuf.connected(p, q)) continue;
			wuf.union(p, q);
		
		
}
		StdOut.format("weighted took: %f seconds.\n", timer.elapsedTime());	


	//compression 
	timer = new Stopwatch();
	CompressionUF cuf = new CompressionUF(count);
	while (!StdIn.isEmpty()) {
		int p = StdIn.readInt();
		int q = StdIn.readInt();
		if (!cuf.connected(p, q)) {
			cuf.union(p, q);
		}
	}
	StdOut.format("compression took: %f seconds.\n", timer.elapsedTime());	

}
}