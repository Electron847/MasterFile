import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;


public class ShortPlaintext_Attack {

	public static HashMap<BigInteger, Integer> listOne(BigInteger c, BigInteger e, BigInteger n, int N){
		HashMap<BigInteger, Integer> resultHM = new HashMap<>();

		for (int x=1; x<N; x++){
			try {
				BigInteger mid = BigInteger.valueOf(x).modPow(e.negate(),n);
				BigInteger result = (c.multiply(mid)).mod(n);
				resultHM.put(result,x);
			}
			catch (Exception exc) {
				System.out.printf("Inverse for %d doesn't exist. %s\n",x,exc);
			}
		}
		System.out.println("Step 1 finished");
		return(resultHM);
	}

	public static void listTwo(BigInteger e, BigInteger n, int N, HashMap<BigInteger, Integer> hm1){
		//		HashMap<BigInteger,Integer> resultHM = new HashMap<>();
		System.out.println("List two is running...");

		/*
		  Aaron:
		   	  You don't need two loops for checking matches that could take O(N^2).
		   	  The first one is like a dictionary for the second loop which make run time to linear
		   	  and once we find a match we need the index of the match in second loop and multiply the value of the hashmap.

		   	  The plaintext is SQRING.
		 */



		for (int y=1; y<N; y++){

			BigInteger result = BigInteger.valueOf(y).modPow(e,n);


				if (hm1.containsKey(result)) {
					////				if (hm1.get(i).equals(hm2.get(j)))
					System.out.println("found a match");
					System.out.format("%d - %d\n", result, hm1.get(result));
					System.out.format("%d\n ", BigInteger.valueOf(y).multiply(BigInteger.valueOf( hm1.get(result) ) ));
					break;
				}
		}
			//			resultHM.put(result,y);

			
			//		


		System.out.println("Step 2 finished");
//		return(resultHM);
	}

		public static void main(String[] args) {

			BigInteger mod = new BigInteger ("665491722871");
			BigInteger exponent = new BigInteger ("710221");
			BigInteger cipher = new BigInteger ("121259080226");
			int listSize = (int)Math.pow(2,21);

			HashMap<BigInteger,Integer> hm1 = listOne(cipher,exponent,mod,listSize);
			listTwo(exponent,mod,listSize,hm1);
			//		HashMap<BigInteger,Integer> hm2 =listTwo(exponent,mod,listSize);

//			message = (x*y)^e mod n;


			System.out.println("Finished");
		}

	}
