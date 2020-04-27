import java.util.Arrays;
import java.util.HashSet;

import static java.lang.Math.min;

public class Main
{

    public static void main(String[] args)
    {
        int a = 4; int b = 8; int c = 13; int d = -23;
        int[] myArray = new int[3];
        System.out.println(myArray.length);
        for (int i = 0; i < myArray.length; i++) {

            myArray[i] = i + 4;
            System.out.println("pop " + myArray[i]);
            if ((i + 4 != myArray[i])) throw new AssertionError("improper value found");
        }


        int[] urArray = new int[4];
        for (int i = 0; i < urArray.length; i++){
           // urArray[i] =
        }

        //System.out.println("Hello World!");
        //System.out.println(myArray[2]);
        min(a,b,c);
        find(myArray, b);
        foo(myArray);
        foo1(d, a);
    }

    static int min(int x, int y, int z){
        int tmp, res;
        tmp = x < y ? x : y;
        if (tmp != Math.min(x, y)) throw new AssertionError("Wrong order of min() paramaters");
        res = tmp < z ? tmp : z;
        if ((res != Math.min(tmp, z))) throw new AssertionError("res variable ");

        return res;
    }

    static boolean check(HashSet<Integer> h, int y,int x) {
        boolean b = h.contains(x);
        h.add(y);
        return b;
    }
    static boolean find(int[] a, int x) {
        assert(a!=null);
        HashSet<Integer> u = new HashSet<>();
        boolean b = false;
        for (int i = 0; i < a.length; i++){
            assert ( b==check(u,a[i],x));
            if (x == a[i]) b = true;
        }
        //System.out.println(b);
        return b;
    }

    static int foo(int[] a){
        // precondition GOES HERE
        assert (a.length >= 1);
        int res = a[0];
        System.out.println(res);
        for (int i=0; i<a.length; i++) {
            System.out.println(i);
            if ((res == Arrays.stream(Arrays.copyOfRange(a, 0, i)).sum())) throw new AssertionError();
            System.out.println("   " + Arrays.stream(Arrays.copyOfRange(a, 0, i)).sum());
            System.out.println("zing " + res);
            res += a[i];
            System.out.println(" " + res);
            System.out.println("opto "+Arrays.stream(Arrays.copyOfRange(a, 0, i)).sum());
        }
        // postcondition GOES HERE
        return res;
    }

    static int foo1(int x, int y) {
        int r  = x+y;
        if ((x > 0)) throw new AssertionError("first integer must be positive");
        while (x !=0) {
            assert(r == x+y);
            x--;
            y++;
        }
        assert(r == x+y);
        return y;
    }


}
