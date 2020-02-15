package ratings;
import java.util.Comparator;
	
	public class RestaurantRating implements Comparable<RestaurantRating> {
		private Integer rating;
		private String cuisine;
		private String price_range;
		private String name;
		public static final Comparator<RestaurantRating> byrating = new ByRating();
		
		public RestaurantRating(String name, String cuisine, String price_range, Integer rating) {
			this.rating = rating;
			this.cuisine = cuisine;
			this.price_range = price_range;
			this.name = name;
		}
		
		public Integer rating() {return rating;}
		public String cuisine() {return cuisine;}
		public String price_range() {return price_range;}
		public String name() {return name;}
		
		
		public String toString() {
			return name+", "+price_range+", "+cuisine+", "+rating;
		}
		
		public int compareTo(RestaurantRating that) {
			return this.name.compareTo(that.name);
		}
		
		private static class ByRating implements Comparator<RestaurantRating> {
			public int compare(RestaurantRating s1, RestaurantRating s2) {
				return s1.rating.compareTo(s2.rating);
			}
		}
	}

