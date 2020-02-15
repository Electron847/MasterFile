package textstats;
import stdlib.*;
import algs13.*;
import algs22.Merge;

/**  Seth Weber
--- Processing the text file data/tale.txt ---
Number of words in the text: 135643
Average word length: 4.300937018497084
First word: a
Middle Word: man
Last word: zealous
Number of unique words: 10674
First word: a
Middle Word: laughingly
Last word: zealous
**/

public class TextStats {
	public static int numUnique (String[] words) {
		if (words.length == 0) return 0;
		int uniqueCount = 1;
		for (int i = 1; i < words.length; i++) {
			if (!words[i].equals(words[i-1])) uniqueCount++;
		}
		return uniqueCount;
	}
	
	public static String[] deDupAndReverse(String[] words) 
	{
		int numOfUnique = numUnique(words);
		String uniqueValue = "";
		String[] result = new String[numOfUnique];
		
		int count =0;
		for (int i = words.length -1; i>=0; i-- ) 
		{
			if (!uniqueValue.equals(words[i]))
			{
				uniqueValue = words[i];
				result[count] = words[i];
				count++;
			}
		}
		
		return result;
	}
	
	public static String[] reverseString(String[] words)
	{
		String[] uniqueWords = deDupAndReverse(words);
		Stack <String> stack = new Stack <String>();
		for (int i = 0; i < uniqueWords.length; i++) {
			stack.push(uniqueWords[i]);
		}
		
		int numOfUnique = numUnique(words);
		String[] result = new String[numOfUnique];
		int count = 0;
		while (!stack.isEmpty()) {
			result[count] = stack.pop();
			count++;
		}
	    return result;
	}
	
	public static double averageWordLength(String[] words) {
		double totalLength = 0;
		for(int i=0; i<words.length; i++) {
			totalLength += words[i].length();
		}
		
		return totalLength / words.length;
	}

	public static void main(String[] args) {
		System.out.println("--- Processing the text file data/tale.txt ---");
		StdIn.fromFile("data/tale.txt");
		String[] words = StdIn.readAllStrings();
		Merge.sort(words);
		System.out.println("Number of words in the text: " + words.length);
		System.out.println("Average word length: " + averageWordLength(words));
		System.out.println("First word: " + words[0]);
		System.out.println("Middle Word: " + words[words.length / 2]);
		System.out.println("Last word: " + words[words.length - 1]);
		System.out.println("Number of unique words: " + numUnique (words));
		
		String[] newArray = reverseString(words);
		System.out.println("First word: " + newArray[0]);
		System.out.println("Middle Word: " +  newArray[newArray.length / 2]);
		System.out.println("Last word: " + newArray[newArray.length - 1]);
	}
}
