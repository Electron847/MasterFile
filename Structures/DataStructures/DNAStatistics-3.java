package StudyG;

import java.util.TreeMap;
import stdlib.StdIn;

public class DNAStatistics {
	public static void main(String[] args) {
		StdIn.fromFile("data/sequences.txt");
		String[] data = StdIn.readAllStrings();
		
		for(int i=0; i < data.length; i+=3) {
			
			String name = data[i] + " " + data[i+1];
			String DNA = data[i+2];
			int codonNum = 0;
			int nucleotidesNum = 0;
			TreeMap<Character, Integer> nucleotides = new TreeMap<Character, Integer>();
			TreeMap<String, Integer> codons = new TreeMap<String, Integer>();
			
			for(int j=0; j < DNA.length(); j+=3) {
				
				// handle nucleotides
				for(int k=j; k<j+3; k++) {
					nucleotidesNum++;
					char c = DNA.charAt(k);
					if(nucleotides.containsKey(c)) 
						nucleotides.put(c, nucleotides.get(c)+1);
					else
						nucleotides.put(c, 1);
				}
				
				// handle codons
				String codon = DNA.substring(j, j+3);
				codonNum++;
				if(codons.containsKey(codon))
					codons.put(codon, codons.get(codon)+1);
				else
					codons.put(codon, 1);
			}
			
			System.out.println(name);
			System.out.println("Number of nucleotides: " + nucleotidesNum);
			//System.out.println(nucleotides);
			for(Character key : nucleotides.keySet()) 
			{
				System.out.print(key +": " + nucleotides.get(key) + " ");
			}
			System.out.println();
			System.out.println("Number of codons: " + codonNum);
			System.out.println("Number of different codons: " + codons.size());
			//System.out.print(codons);
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
