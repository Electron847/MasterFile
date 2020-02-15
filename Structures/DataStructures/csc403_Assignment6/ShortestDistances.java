package csc403;

import stdlib.*;
import java.util.*;
import algs41.*;
import algs35.ST;

public class ShortestDistances {

	public static void main(String[] args) {
		StdIn.fromFile("data/a6cities.txt");
		String [] a6cities = StdIn.readAllStrings();	
		ST<String,Integer> cityArrayValues = new ST<String,Integer>();
		//System.out.println(a6cities.length);
		for (int i = 0; i < a6cities.length; i++)
		{
			String city = a6cities[i];
			cityArrayValues.put(city, i);
			//System.out.printf("The city is %s "+"with index %d \n", city, cityArrayValues.get(city));
		}
		
		StdIn.fromFile("data/a6majorcities.txt");
		String [] a6majorcities = StdIn.readAllStrings();
		//System.out.println(a6majorcities.length);
		for (int i = 0; i < a6majorcities.length; i++)
		{
			String major_city = a6majorcities[i];
			//System.out.printf("The major city is %s "+"with array index %d \n", major_city, i);
		}
		
		Graph distances = new Graph(cityArrayValues.size());
		StdIn.fromFile("data/a6connections.txt");
		while (StdIn.hasNextLine())
		{
			String connections = StdIn.readLine();
			String connection [] = connections.split("\\s+");
				for(int i = 0; i<connection.length; i+=2) 
				{
					distances.addEdge(cityArrayValues.get(connection[i]), cityArrayValues.get(connection[i+1]));
				}
		}
		
		StdOut.printf("%20s", "");
		for (String majorCity: a6majorcities) 
		{
			StdOut.printf("%-12s", majorCity);
		}
		StdOut.println();
		
		for(String city: cityArrayValues.keys()) 
		{
			BreadthFirstPaths bfs = new BreadthFirstPaths(distances, cityArrayValues.get(city));
			StdOut.printf("%-18s",city);
			for(String majorCity: a6majorcities) {
				StdOut.printf("    %3d     ", bfs.distTo(cityArrayValues.get(majorCity)));
			}
			StdOut.println();
		}
	}
}
