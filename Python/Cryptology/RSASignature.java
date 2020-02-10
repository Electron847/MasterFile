//Lindsay Munson
import java.math.BigInteger;

public class RSASignature {
    public static void main(String args[]) {
        //private p and q - 2 large primes
        BigInteger p = new BigInteger("10181285934259926307");
        BigInteger q = new BigInteger("1211068539991484857");

        //public n
        BigInteger nLindsay = p.multiply(q);
        BigInteger nJohn = new BigInteger("2968567020983224981");

        //private phi of n - (p-1)(q-1)
        BigInteger phiN = (p.subtract(BigInteger.valueOf(1))).multiply(q.subtract(BigInteger.valueOf(1)));

        //public e
        BigInteger eLindsay = new BigInteger("947546867");
        BigInteger eJohn = new BigInteger("1790217391");

        //private d
        BigInteger dLindsay = eLindsay.modPow(BigInteger.valueOf(1).negate(),phiN); //might need to add phi

        //plaintext message - id * highest point in IL (1918715 * 1235)
        BigInteger message = new BigInteger("2368001350");

        //Encrypted message
        BigInteger cm =  message.modPow(eJohn,nJohn);
        System.out.println("Encrypted Message: "+cm);

        //Encrypted signature
        BigInteger cs = message.modPow(dLindsay,nLindsay);
        System.out.println("Encrypted Signature: "+cs);
    }
}
