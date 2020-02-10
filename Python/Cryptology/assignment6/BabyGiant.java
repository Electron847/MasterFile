//Seth Weber
//Baby Step Giant Step
//Assignment 6
//Cryptology 440
//March 14th 2019

import java.math.*;
import java.util.*;

public class BabyGiant {

    public static void main (String[]args)
    {
        String [] alphaList = new String[] {"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"};
        BigInteger p = new BigInteger("595117");
        BigInteger alpha = new BigInteger("1002");
        BigInteger beta = new BigInteger("437083");
        BigInteger n = new BigInteger("772");
        int N = 772;
        HashMap<BigInteger, Integer> list1 = new HashMap<>();

        for (int i = 0; i <= N; i++)
        {
            long u = i;
            BigInteger proxy = BigInteger.valueOf(u);
            BigInteger element = alpha.modPow(proxy,p);
            list1.put(element,i);
        }
        System.out.println("list one complete");
        for(int i = 0; i <= N; i++)
        {
            long u = i;
            BigInteger k = BigInteger.valueOf(u);
            BigInteger exponent = n.multiply(k);
            BigInteger secondCalc = alpha.modPow(exponent.negate(), p);
            BigInteger betaCalc = (beta.multiply(secondCalc)).mod(p);
            BigInteger result = new BigInteger("0");
            if(list1.containsKey(betaCalc))
            {
                System.out.println("match found");
                System.out.format("%s%d --- %s%d\n", "k = ", k, "j = ", list1.get(betaCalc));
                int jVal = list1.get(betaCalc).intValue();
                int kVal = k.intValue();
                int xVal = jVal + (kVal*N);
                System.out.println("value for X is: " + xVal);
                BigInteger xVal1 = new BigInteger(Integer.toString(xVal));
                String stringXVal = xVal1.toString();
                System.out.println(stringXVal.substring(0,2) + "  " + stringXVal.substring(2,4) + "  " + stringXVal.substring(4,6));
                int firstLetter = Integer.parseInt(stringXVal.substring(0,2));
                int secondLetter = Integer.parseInt(stringXVal.substring(2,4));
                int thirdLetter = Integer.parseInt(stringXVal.substring(4,6));
                String letterOne = alphaList[firstLetter-1];
                String letterTwo = alphaList[secondLetter-1];
                String letterThree = alphaList[thirdLetter-1];
                System.out.format("%s - %s - %s", letterOne.toUpperCase(), letterTwo.toUpperCase(), letterThree.toUpperCase());
                //System.out.println(alphaList[09]);

            }
        }
        System.out.println("\n\ncomplete");
    }
}
