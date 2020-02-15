//Seth Weber

package csc402examsolutions;
import java.util.Comparator;


public class Flight implements Comparable<Flight> {
	private Integer flight_number;
	private String arrival_airport_code;
	private String Dairport_code;
	private String dep_time;
	private String arrive_time;
	private String airline_name;
	private String name_number = airline_name+ flight_number;
	public static final Comparator<Flight> byflight = new ByFlight();
	public static final Comparator<Flight> bydeparture = new ByDeparture();
	public static final Comparator<Flight> byarrival = new ByArrival();
	
	public Flight(String airline_name, Integer flight_number, String Dairport_code, String arrival_airport_code, String dep_time, String arrive_time) {
		this.flight_number = flight_number;
		this.arrival_airport_code = arrival_airport_code;
		this.Dairport_code = Dairport_code;
		this.dep_time = dep_time;
		this.arrive_time = arrive_time;
		this.airline_name = airline_name;
	}
	
	String pad_flight_number(Integer flight_number){
	    return String.format("%04d",flight_number);
	}
	
	public Integer flight_number() {return flight_number;}
	public String arrival_airport_code() {return arrival_airport_code;}
	public String Dairport_code() {return Dairport_code;}
	public String dep_time() {return dep_time;}
	public String arrive_time() {return arrive_time;}
	public String airline_name() {return airline_name;}
	
	public String toString() {
		return airline_name+pad_flight_number(flight_number)+", "+Dairport_code+", "+arrival_airport_code+", "+dep_time+", "+arrive_time;
	}
	
	public int compareTo(Flight that) {
		//return this.pad_flight_number(flight_number).compareTo(that.pad_flight_number(flight_number));
		return this.name_number.compareTo(that.name_number);
	}
	
	private static class ByFlight implements Comparator<Flight> {
		public int compare(Flight s1, Flight s2) {
			return s1.flight_number.compareTo(s2.flight_number);
		}
	}
	
	private static class ByDeparture implements Comparator<Flight> {
		public int compare(Flight s1, Flight s2) {
			return s1.Dairport_code.compareTo(s2.Dairport_code);
		}
	}
	
	private static class ByArrival implements Comparator<Flight> {
		public int compare(Flight s1, Flight s2) {
			return s1.arrival_airport_code.compareTo(s2.arrival_airport_code);
		}
	}
	
}
