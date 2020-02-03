//Seth Weber
//RSA short plain text attack program
//Assignment 6
//Cryptology 440
//March 14th 2019

import java.math.*;
import java.util.*;

public class RSA {
    public static void main(String[] args)
    {
        String [] alphaList = new String[] {"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"};
        HashMap<BigInteger, Integer> list1 = new HashMap<>();
        BigInteger n = new BigInteger("665491722871");
        BigInteger e = new BigInteger("710221");
        BigInteger c = new BigInteger("121259080226");
        for (int x = 1; x <= 2500000; x++)
        {
            try
            {
            BigInteger proxy = BigInteger.valueOf(x).modPow(e.negate(),n);
            BigInteger xFactor = (c.multiply(proxy)).mod(n);
            list1.put(xFactor, x);
            }
            catch (ArithmeticException e1){System.out.print("woopsies!");}
        }
        System.out.println("list one complete");
        for (int index = 1; index <= 2500000; index++)
        {
            BigInteger y = BigInteger.valueOf(index).modPow(e,n);
            if (list1.containsKey(y))
            {
                System.out.println(list1.get(y));
                System.out.println("match found");
                System.out.format("%d - %d\n", y, list1.get(y));
                System.out.format("%d\n ", BigInteger.valueOf(index).multiply(BigInteger.valueOf(list1.get(y))));
                BigInteger resultVal = BigInteger.valueOf(index).multiply(BigInteger.valueOf(list1.get(y)));
                String stringVal = resultVal.toString();
                System.out.println(stringVal.substring(0,2) + "  " + stringVal.substring(2,4) + "  " + stringVal.substring(4,6) + "  " + stringVal.substring(6,8) + "  " + stringVal.substring(8,10) + "  " + stringVal.substring(10,12));
                int firstLetter = Integer.parseInt(stringVal.substring(0,2));
                int secondLetter = Integer.parseInt(stringVal.substring(2,4));
                int thirdLetter = Integer.parseInt(stringVal.substring(4,6));
                int fourthLetter = Integer.parseInt(stringVal.substring(6,8));
                int fifthLetter = Integer.parseInt(stringVal.substring(8,10));
                int sixthLetter = Integer.parseInt(stringVal.substring(10,12));
                String letterOne = alphaList[firstLetter-1];
                String letterTwo = alphaList[secondLetter-1];
                String letterThree = alphaList[thirdLetter-1];
                String letter4 = alphaList[fourthLetter -1];
                String letter5 = alphaList[fifthLetter-1];
                String letter6 = alphaList[sixthLetter - 1];
                System.out.format(" %s - %s - %s - %s - %s - %s", letterOne.toUpperCase(), letterTwo.toUpperCase(), letterThree.toUpperCase(), letter4.toUpperCase(), letter5.toUpperCase(), letter6.toUpperCase());
                break;
            }
        }
    }
}
