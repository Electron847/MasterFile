/*
 u002l02wsrafa0b:HW2 Said$ java PairOfDice
 The average number of rolls to get a 7: 6.000
 The average number of rolls to get a 12: 35.978
 The percentage of rolls with both dice even is 0.251
 The percentage of rolls with both dice odd is 0.250
 The percentage of rolls with one even and one odd is 0.500
u002l02wsrafa0b:HW2 Said$ 

 */

import java.util.Random;

//import stdlib.StdOut;

//import stdlib.StdRandom;

public class PairOfDice {

	private int die1, die2;

	public PairOfDice() {

		roll();
	}

	public void roll() {

		Random rand = new Random();

		die1 = rand.nextInt(6) + 1;

		die2 = rand.nextInt(6) + 1;

	}

	public boolean isDouble() {

		return die1 == die2;

	}

	public int sum() {

		return die1 + die2;

	}

	public boolean areBothEven() {

		if ((die1 % 2 == 0) && (die2 % 2 == 0)) {

			return true;

		} else {

			return false;

		}

	}
	
	public boolean areBothOdd() {

		if ((die1 % 2 == 1) && (die2 % 2 == 1)) {

			return true;

		} else {

			return false;

		}

	}

	public static double experimentOne(PairOfDice x, int rollValue) {

		double sumOfNumOfRolls = 0;
		int testTimes = 1000000;

		for (int i = 1; i <= testTimes; i++) {

			int sum = 0;
			
			while(sum != rollValue) {
				x.roll();
				sum = x.sum();
				sumOfNumOfRolls++;
			}

		}

		return sumOfNumOfRolls / testTimes;

	}
	
	public static double experimentTwo(PairOfDice x, String rollType) {
		double evenTimes = 0;
		double oddTimes = 0;
		double oneOfEachTimes = 0;
		int rollTimes = 1000000;
		
		for(int i=1; i<=rollTimes; i++) {
			x.roll();
			if(x.areBothEven()) {
				evenTimes++;
			}else if(x.areBothOdd()){
				oddTimes++;
			}else {
				oneOfEachTimes++;
			}
		}
		
		if(rollType.equals("Even")) {
			return evenTimes / rollTimes;
		}else if (rollType.equals("Odd")) {
			return oddTimes / rollTimes;
		}else if(rollType.equals("OneOfEach")){
			return oneOfEachTimes / rollTimes;
		}
		
		return -1;
	}

	public static void main(String[] args) {

		PairOfDice x = new PairOfDice();
		
        double sevens = experimentOne(x, 7);
        double twelves = experimentOne(x,12);
        experimentOne(x, 12);
        double evens = experimentTwo(x, "Even");
        double odds  = experimentTwo(x, "Odd");
        double oneOfEach = experimentTwo(x, "OneOfEach");
        
        System.out.printf(" The average number of rolls to get a 7: %5.3f\n", sevens);
        System.out.printf(" The average number of rolls to get a 12: %5.3f\n", twelves);
        System.out.printf(" The percentage of rolls with both dice even is %5.3f\n", evens);
        System.out.printf(" The percentage of rolls with both dice odd is %5.3f\n", odds);
        System.out.printf(" The percentage of rolls with one even and one odd is %5.3f\n", oneOfEach);

	}

}
