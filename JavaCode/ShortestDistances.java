//csc403 assignment 6
//Seth Weber

package csc403;

import algs35.ST;
import stdlib.StdIn;
import stdlib.StdOut;
import algs41.*;

public class ShortestDistances {
	public static void main (String [] args) {
		String cityData = "data/a6cities.txt";
		String majorCityData = "data/a6majorcities.txt";
		String connectionsData = "data/a6connections.txt";
		
		ST<String, Integer> cities = new ST<>();
		
		StdIn.fromFile(cityData);
		String cityList [] = StdIn.readAllStrings();
		for (int i = 0; i<cityList.length; i++) {
			cities.put(cityList[i], i);
		}

		Graph distances = new Graph(cities.size());
		
		StdIn.fromFile(majorCityData);
		String majorCities [] = StdIn.readAllStrings();

		StdIn.fromFile(connectionsData);
		while(StdIn.hasNextLine()) {
		String connections = StdIn.readLine();
		String connection [] = connections.split("\\s+");
			for(int i = 0; i<connection.length; i+=2) {
				distances.addEdge(cities.get(connection[i]), cities.get(connection[i+1]));
			}
		}
		
		StdOut.printf("%20s", "");
		for (String majorCity: majorCities) {
			StdOut.printf("%-12s", majorCity);
		}
		StdOut.println();
		
		for(String city: cities.keys()) {
			BreadthFirstPaths bfs = new BreadthFirstPaths(distances, cities.get(city));
			StdOut.printf(city);
			for(String majorCity: majorCities) {
				StdOut.printf("    %3d     ", bfs.distTo(cities.get(majorCity)));
			}
			StdOut.println();
		}
	}
}

