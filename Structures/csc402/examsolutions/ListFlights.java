//Seth Weber

package csc402examsolutions;
import java.util.*;
import stdlib.*;

public class ListFlights {

	public static void main(String[] args) {
		StdIn.fromFile("data/flightdata.txt");
		ArrayList<Flight> flights = new ArrayList<>();
		while (!StdIn.isEmpty()) {
			String line = StdIn.readLine();
			String[] fields = line.split("\\s+");
			int flight_number = Integer.parseInt(fields[1]);
			String airline_name = fields[0];
			String Dairport_code = fields[2];
			String arrival_airport_code = fields[3];
			String dep_time = fields[4];
			String arrive_time = fields[5];
			flights.add(new Flight(airline_name, flight_number,Dairport_code, arrival_airport_code, dep_time, arrive_time));
		}
		
		Collections.sort(flights);
		StdOut.println("--- Flights data from text file ---");
		for (Flight flight: flights) {
			StdOut.println(flight);
		}
		
		Collections.sort(flights, Flight.byflight);
		StdOut.println();
		StdOut.println("--- Flights ordered by flight number ---");
		for (Flight flight: flights) {
			StdOut.println(flight);
		}
		
		Collections.sort(flights, Flight.bydeparture);
		StdOut.println();
		StdOut.println("--- Flights ordered by departure airport ---");
		for (Flight flight: flights) {
			StdOut.println(flight);
	}
		Collections.sort(flights, Flight.byarrival);
		StdOut.println();
		StdOut.println("--- Flights ordered by arrival airport ---");
		for (Flight flight: flights) {
			StdOut.println(flight);
	}
  }
}
