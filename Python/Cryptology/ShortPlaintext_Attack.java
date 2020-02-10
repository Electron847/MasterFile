package crypto440;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import stdlib.StdOut;

public class ShortPlaintext_Attack {

	public static HashMap<BigInteger, Integer> listOne(BigInteger c, BigInteger e, BigInteger n, int N){
		HashMap<BigInteger, Integer> resultHM = new HashMap<>();

		for (int x=1; x<N; x++)
		{
			try
			{
				BigInteger mid = BigInteger.valueOf(x).modPow(e.negate(),n);
				BigInteger result = (c.multiply(mid)).mod(n);
				resultHM.put(result,x);
			}
			catch (Exception exc)
			{
				System.out.printf("Inverse for %d doesn't exist. %s\n",x,exc);
			}
		}
		StdOut.println("Step 1 finished");
		return(resultHM);
	}

	public static void listTwo(BigInteger e, BigInteger n, int N, HashMap<BigInteger, Integer> hm1){
		//		HashMap<BigInteger,Integer> resultHM = new HashMap<>();
		StdOut.println("List two is running...");
		for (int y=1; y<N; y++){
			BigInteger result = (BigInteger.valueOf(y)).modPow(e,n);
			for (BigInteger i: hm1.keySet()){
				if (result.equals(i)){
					////				if (hm1.get(i).equals(hm2.get(j)))
					StdOut.println("found a match");
					StdOut.printf("%d - %d", result, i);
					//
				}
			}
			//			resultHM.put(result,y);

			
			//		

		}
		StdOut.println("Step 2 finished");
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


			StdOut.println("Finished");
		}

	}
