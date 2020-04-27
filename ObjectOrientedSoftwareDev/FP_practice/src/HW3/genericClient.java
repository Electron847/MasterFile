package HW3;


import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

interface A<T>
{
    T compute (T o);
}


interface func< U, V>
{
    V sum(U u, V v);
}




public class genericClient {

    public static void main(String[]args)
    {

        ArrayList<Integer> myIntList = new ArrayList<>();
        (myIntList).add(1);
        (myIntList).add(2);
        (myIntList).add(3);
        (myIntList).add(4);
        (myIntList).add(15);




        func<ArrayList<Integer>, Integer> a3 = (a, b) -> {
            int first = 0;
            for (int i = 0; i < myIntList.size();i++)
            {
                first += myIntList.get(i);

            }
            return first;
        };

        int y = a3.sum(myIntList,3);
        System.out.println(y);


        A<Integer> a1 = (n) ->  {
            int fact = 1;
            for (int i = 1; i < n; i++)
                fact = fact*i;
            return fact;
        };

        int f = a1.compute(5);
        System.out.println(f);

        A<String> a2 = (s) ->
        {
            String reverse = "";
            for (int i = s.length() -1; i >= 0; i--){
                reverse = reverse + s.charAt(i);
            }
            return reverse;
        };

        String r = a2.compute("java");
        System.out.println(r);
    }
}
