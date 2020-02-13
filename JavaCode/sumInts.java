//Seth Conner Weber
/**Please enter a positive nonzero integer: 
*9
*Your entry was: 9
*For 1 the sumInts is: 1
*For 1 the value of (M+1)*M/2 is: 1
*For 2 the sumInts is: 3
*For 2 the value of (M+1)*M/2 is: 3
*For 3 the sumInts is: 6
*For 3 the value of (M+1)*M/2 is: 6
*For 4 the sumInts is: 10
*For 4 the value of (M+1)*M/2 is: 10
*For 5 the sumInts is: 15
*For 5 the value of (M+1)*M/2 is: 15
*For 6 the sumInts is: 21
*For 6 the value of (M+1)*M/2 is: 21
*For 7 the sumInts is: 28
*For 7 the value of (M+1)*M/2 is: 28
*For 8 the sumInts is: 36
*For 8 the value of (M+1)*M/2 is: 36
*For 9 the sumInts is: 45
*For 9 the value of (M+1)*M/2 is: 45
*The sum of the integers leading up to your entered value: 45
*BUILD SUCCESSFUL (total time: 4 seconds)
*/
/**
 *
 * @author sethweber
 */
import java.util.Scanner;

public class csc402hw1d {
    
    public static int sumInts(int n)
    {  
        int sum=0;
        for (int i = 1; i <= n; i++){
            sum += i;
            System.out.printf("For %d the sumInts is: %d\n", i, sum);
            System.out.printf("For %d the value of (M+1)*M/2 is: %d\n", i, sum);
            
        }
        System.out.printf("The sum of the integers leading up to your entered value: %d\n", sum);
        return 0;
    }
   
    public static void main(String[] args){        
        Scanner darkly = new Scanner(System.in);
        int input_number = -1;
        while (input_number < 1){
            System.out.println("Please enter a positive nonzero integer: ");
            input_number = darkly.nextInt();
        }
    System.out.printf("Your entry was: %d\n", input_number);
    sumInts(input_number);
    
    }
    
}
