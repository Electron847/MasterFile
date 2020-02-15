//Seth Weber
//csc403 Data Structures 2

package csc403;
import java.util.Arrays;
import stdlib.StdIn;

public class TestA3SimplerBST {

	public static void main(String[] args) {
		StdIn.fromFile("data/tale.txt");
		String[] words = StdIn.readAllStrings();
		
		A3SimplerBST<String, Integer> sBST = new A3SimplerBST<String, Integer>();
		for (String word: words)
		{
			if(sBST.contains(word)) sBST.put(word, sBST.get(word)+1);
			else sBST.put(word, 1);
		}
		System.out.printf("When words are not sorted. Longest path from root: %d. Leaf number: %d. \n", sBST.longestPathLength(), sBST.leafCount());
		
		Arrays.sort(words);
		A3SimplerBST<String, Integer> sBSTSorted = new A3SimplerBST<String, Integer>();
		for (String word: words)
		{
			if(sBSTSorted.contains(word)) sBSTSorted.put(word, sBSTSorted.get(word)+1);
			else sBSTSorted.put(word, 1);
		}
		System.out.printf("When the words have been sorted. Longest path from root: %d. Leaf number: %d. \n", sBSTSorted.longestPathLength(), sBSTSorted.leafCount());
	}
}