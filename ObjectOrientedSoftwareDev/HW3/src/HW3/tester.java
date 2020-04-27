package HW3;

import com.sun.xml.internal.xsom.impl.scd.Iterators;

import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Function;




public class tester {

    public static <V> List<V>
    getListFromIterator(Iterator<V> iterator)
    {

        // Create an empty list
        List<V> list = new ArrayList<>();

        // Add each element of iterator to the List
        iterator.forEachRemaining(list::add);

        // Return the List
        return list;
    }







    static <U,V> List<V> map1(Iterable<U> l, Function<U, V> f)
    {

        //Iterator<U> anIterator = l.iterator();
        //List<V> hashCodeList = getListFromIterator(anIterator);

       // MyInterface

        List<V> hashList = new ArrayList<V>(l.iterator().hashCode());



        return hashList;
    }




   /* {
        List<B> hashList = new ArrayList<>();
        l.iterator().forEachRemaining(value -> f.apply(value).hashCode());
        System.out.println(hashList);
        return hashList;
    }*/










    static <U,V> V foldLeft(V e, Iterable<U>l, BiFunction<V,U,V> f)
    {

        Iterable<U> parameter = new ArrayList<U>((Collection<? extends U>) l);


        //BiFunction<V,U,V>



        //return l.forEach(value -> numbers.add(l.iterator().next().hashCode()
      //   .reduce(0, (acc, n) -> (acc + n));
        return null;
    }











    static <U> U minVal(Iterable<U> l, Comparator<U> c) {



        // write using fold.  No other loops or recursion permitted.
        return null;
    }





    public static void main(String[] args)
    {

        //BiFunction<V,U,V>

        //  final Iterators.Array<Integer> a = array(97, 44, 67, 3, 22, 90, 1, 77, 98, 1078, 6, 64, 6, 79, 42);
      //  final int b = a.foldLeft(add, 0);

      //  BiFunction<Integer, Function<Integer, Integer>> add2 = i -> (j -> i + j);
      //  final int c = a.foldLeft(add2, 0);









        Iterable<String> names = new ArrayList<String>();
        ((ArrayList<String>) names).add("Mary");
        ((ArrayList<String>) names).add("Isla");
        ((ArrayList<String>) names).add("Sam");
        ((ArrayList<String>) names).add("Beth");
        ((ArrayList<String>) names).add("Dolly");
        ((ArrayList<String>) names).add("Huxtable");
        ((ArrayList<String>) names).add("Dan Rather");

        //List<Integer> hashList = new ArrayList<Integer>(names.iterator().next().hashCode());









        //System.out.println(hashList);

      /*  List<Integer> hashcodes = new ArrayList<>();

        MyInterface mapInterface = (Iterable<> myList) ->
        {
            for (int i = 0; i < ((ArrayList<String>) names).size() - 1; i++)
            {
                ((ArrayList<Integer>) hashcodes).add(i, ((ArrayList<String>) names).get(i).hashCode());
            }
            return hashcodes;
        };*/

        //map1(names,mapInterface.listIt(names));












        //iterableContainer<Integer> parameterIterable = new iterableContainer(names);
        //int val1 = parameterIterable.getItem1();






        Comparator <Integer> myComparator = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1.compareTo(o2);
            }
        };

        List<Integer> myIntList = new ArrayList<>();
        ((ArrayList<Integer>) myIntList).add(1);
        ((ArrayList<Integer>) myIntList).add(2);
        ((ArrayList<Integer>) myIntList).add(3);
        ((ArrayList<Integer>) myIntList).add(4);
        ((ArrayList<Integer>) myIntList).add(15);

        //System.out.println(this.sum(myIntList));


        minVal(myIntList, myComparator);
        //System.out.println(minVal(myIntList, myComparator));





        //int x = sum(myIntList);
        //System.out.println(x);





        /*Iterable<String> names = new ArrayList<String>();
        ((ArrayList<String>) names).add("Mary");
        ((ArrayList<String>) names).add("Isla");
        ((ArrayList<String>) names).add("Sam");
        ((ArrayList<String>) names).add("Beth");
        ((ArrayList<String>) names).add("Dolly");
        ((ArrayList<String>) names).add("Huxtable");
        ((ArrayList<String>) names).add("Dan Rather");*/

        //Function f = names.forEach(value -> value.hashCode());

       // map1(names,f);









        /*List<Integer> hashcodes = new ArrayList<>();

        MyInterface mapInterface = (Iterable<String> myList) ->
        {
            for (int i = 0; i < ((ArrayList<String>) names).size() - 1; i++)
            {
                ((ArrayList<Integer>) hashcodes).add(i, ((ArrayList<String>) names).get(i).hashCode());
            }
            return hashcodes;
        };*/

        //return mapInterface.listIt(myIntList);
        //System.out.println(mapInterface.listIt(names));
    }
}
