package algs11;

/**
*  Version 1.1
*  
*  CSC300 Program 3b:  the file name must be  PairOfDice
*  
*   Your Name goes here
*   You class section  goes here
*   
*   Does your program have compile errors?     Yes / No
*   
* This is a skeleton file for your homework. Edit the sections marked TODO. You
* may also edit the function "main" to test your code.
*
* You must not change the declaration of any method. 
*/
import stdlib.StdOut;
import stdlib.StdRandom;

public class PairOfDice {
	private int die1, die2;

	public PairOfDice() {
		roll();
	}

	public void roll() {
		die1 = StdRandom.uniform(1, 7);
		die2 = StdRandom.uniform(1, 7);
	}
	public boolean isDoubles() {
		return die1 == die2;
	}
	public int sum() {
		return die1 + die2;
        
	}
	
    public boolean bothEven() {
        if ((die1 % 2 == 0) && (die % 2 == 0)){
            return true;
        }
        else {
            return false;
        }
        
    }
    
    public boolean bothOdd() {
        if ((die1 % 2 != 0) && (die2 % 2 != 0)){
            return true;
        }
        else {
            return false;
        }
    }
    
    
	// ToDo 1
	// add an instance method that determines if both dice are even.
	// hint boolean

	// ToDo 2
	// add an instance method that determines if both dice are odd.
	// hint boolean
	
//------------------------------------- testing program ---------------------
	
	 /* experiment 1
	 * precondition:   rollValue is an integer from 1 - 12
	 * 
	 * Q. how many rolls does it take (on the average) to roll a given number?
	 * write a loop to count the number of rolls needed to get the specified rollValue. 
	 * add code to repeat that test 1,000,000 times.  
	 * return: the average (number of rolls needed) of all the tests
	 */
	
	public static double experimentOne( PairOfDice x, int rollValue) {
        double rollCounter = 0;
        int tester = 1000000
        for (int i = 0; i <= tester; i++){
            int sum = 0
            while (sum != rollValue){
                x.roll();
                sum = x.sum();
                rollCounter++;
            }
        }
		return rollCounter/tester; // ToDo 3

	}
	/*
	 * experiment 2
	 * precondition: rollType will be   "Even", "Odd", "OneOfEach"
	 * the function should roll the dice 1,000,000 times and 
	 * determine and return the percentage of rolls that are : rollType
	 * you must use the instance methods you created above
	 * hint:  don't use == for string comparison
	 */
	public static double experimentTwo( PairOfDice x, String rollType) {
		
		return -1;  // ToDo 4:
	}
	
	// nothing to do here
	public static void main(String[] args) {
		
		 PairOfDice x = new PairOfDice();
		
         double sevens = experimentOne(x, 7);
         double twelves = experimentOne(x,12);
         experimentOne(x, 12);
         double evens = experimentTwo(x, "Even");
         double odds  = experimentTwo(x, "Odd");
         double oneOfEach = experimentTwo(x, "OneOfEach");
         
         StdOut.format(" The average number of rolls to get a 7: %5.3f\n", sevens);
         StdOut.format(" The average number of rolls to get a 12: %5.3f\n", twelves);
         StdOut.format(" The percentage of rolls with both dice even is %5.3f\n", evens);
         StdOut.format(" The percentage of rolls with both dice odd is %5.3f\n", odds);
         StdOut.format(" The percentage of rolls with one even and one odd is %5.3f\n", oneOfEach);
	}

	
	
}
