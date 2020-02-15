package ratings;
import java.util.*;
import stdlib.*;

public class RatingReport {


		public static void main(String[] args) {
			StdIn.fromFile("data/restaurantratings.txt");
			ArrayList<RestaurantRating> restaurants = new ArrayList<>();
			while (!StdIn.isEmpty()) {
				String line = StdIn.readLine();
				String[] fields = line.split("\\s+");
				int rating = Integer.parseInt(fields[3]);
				String price_range = fields[1];
				String cuisine = fields[2];
				String name = fields[0];
				restaurants.add(new RestaurantRating(name, cuisine, price_range, rating));
			}
			
			Collections.sort(restaurants);
			StdOut.println("--- Restaurants naturally ordered ---");
			for (RestaurantRating restaurant: restaurants) {
				StdOut.println(restaurant);
			}
			
			Collections.sort(restaurants, RestaurantRating.byrating);
			StdOut.println();
			StdOut.println("--- Restaurants are ordered by rating ---");
			for (RestaurantRating restaurant: restaurants) {
				StdOut.println(restaurant);
			}

			


		}

	}

