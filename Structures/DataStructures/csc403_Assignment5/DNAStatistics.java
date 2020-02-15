//Seth Weber
//Data Structures 403
package csc403;

import java.util.TreeMap;
import stdlib.*;

public class DNAStatistics {
	public static void main(String[] args) {
		StdIn.fromFile("data/sequences.txt");
		String[] data = StdIn.readAllStrings();
		
		for(int i=0; i < data.length; i+=3) {
			
			String animalName = data[i] + " " + data[i+1];
			String DNA_string = data[i+2];
			int codonNum = 0;
			int nucleotidesNum = 0;
			TreeMap<Character, Integer> nukes = new TreeMap<Character, Integer>();
			TreeMap<String, Integer> codons = new TreeMap<String, Integer>();
			
			for(int j=0; j < DNA_string.length(); j+=3) {
				
				//nucleotides
				for(int k=j; k<j+3; k++) {
					nucleotidesNum++;
					char c = DNA_string.charAt(k);
					if(nukes.containsKey(c)) 
						nukes.put(c, nukes.get(c)+1);
					else
						nukes.put(c, 1);
				}
				
				//codons
				String codon = DNA_string.substring(j, j+3);
				codonNum++;
				if(codons.containsKey(codon))
					codons.put(codon, codons.get(codon)+1);
				else
					codons.put(codon, 1);
			}
			
			System.out.println(animalName);
			System.out.println("Number of nucleotides: " + nucleotidesNum);
			for(Character key : nukes.keySet()) 
			{
				System.out.print(key +": " + nukes.get(key) + " ");
			}
			System.out.println();
			System.out.println("Number of codons: " + codonNum);
			System.out.println("Number of different codons: " + codons.size());
			int n = 0;
			for(String key : codons.keySet())
			{
				System.out.print(key +": " +codons.get(key) +" ");
				n++;
				if(n%8==0)
				{
					System.out.println();
				}
					
			}
			System.out.println("\n");
		}
	}
}
