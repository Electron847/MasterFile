package csc403;
import stdlib.*;
public class ComputeARR {

	public static void main(String[] args) {
		algs31.BinarySearchST<String, Integer> RestaurantRating = new algs31.BinarySearchST<String, Integer>();
		RestaurantRating.put("Outstanding", 6);
		RestaurantRating.put("Excellent", 5);
		RestaurantRating.put("Better", 4);
		RestaurantRating.put("Average", 3);
		RestaurantRating.put("Worse", 2);
		RestaurantRating.put("Awful", 1);
		RestaurantRating.put("Avoid", 0);
		
		StdIn.fromFile("data/a1ratings.txt");
		String[] Ratings = StdIn.readAllStrings();
		double total = 0.0;
		
		for (String Rating : Ratings) {
			double Standing = RestaurantRating.get(Rating);
			total += Standing;
		}
		double Average = total / Ratings.length;
		StdOut.println("The total average rating is " + Average);
	}

}
