package HW3;

import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;

class Person{
    final int salary;
    final String name;

    Person(int salary, String name){
        this.salary = salary;
        this.name = name;
    }
    int getSalary() { return salary; }
    String name() { return name;}
}


public class fpPractice {

    /***
     *
     * @param l Iterable of Objects
     * @param f function with two parameters
     * @param <U> Generic Type U
     * @param <V> Generic Type V
     * @return
     */


    static <U,V> List<Object> map(Iterable<Object> l, Function<Integer, Object> f)
    {
        List<Object> hashcodes = new ArrayList<>();

        for (Object s: l)
        {
            int index = 0;
            Object x = f.apply(s.hashCode());
            hashcodes.add(index, x);
            index++;
        }
       return hashcodes;
    }

    /***
     *
     * @param e expression
     * @param l Iterable
     * @param f BiFunction with three parameters
     * @param <U> Generic Type U
     * @param <V> Generic Type V
     * @return
     */

    static <U,V> V foldLeft(V e, Iterable<U>l, BiFunction<V,U,V> f){
        for(U u:l) {
            e = f.apply(e, u);
        }
        return e;
    }

    /***
     *
     * @param e expression
     * @param l Iterable
     * @param f BiFunction with three parameters
     * @param <U> Generic Type U
     * @param <V> Generic Type V
     * @return
     */

    static <U,V> V foldR(V e, Iterable<U>l, BiFunction<U,V,V> f)
    {
        V returnV = e;
        for(U x:l)
        {
            returnV = f.apply(x, returnV);
        }
        return returnV;
    }

    /***
     *
     * @param l Iterable
     * @param p Predicate producing boolean value
     * @param <U> Generic Type U
     * @return
     */

    static <U> Iterable<U> filter(Iterable<U> l, Predicate<U> p)
    {
        //Declare List to return
        List<U> list = new ArrayList<U> ();
        //apply function to each item in l and
        //store in return list
        for(U x:l)
        {
            if (p.test(x))
                list.add(x);
        }
        return list;
    }

    /***
     *
     * @param l Iterable
     * @param c Comparator
     * @param <U> Generic Type U
     * @return
     */

    static <U> U minVal(Iterable<U> l, Comparator<U> c) {
        System.out.println();
        return foldLeft(l.iterator().next(), l, (u, u2) -> c.compare(u, u2) < 0 ? u : u2);
    }

    /***
     *
     * @param l Iterable
     * @param <U> Generic Type U
     * @return
     */

    static <U extends Comparable<U>> int minPos(Iterable<U> l){

        ArrayList<U> al = new ArrayList<U>();
        //to store list in ArrayList
        for(U i : l) al.add(i);
        //find minimum in the Iterable l
        BiFunction<U, U, U> biFun;
        //Declare Bifunction to compare
        biFun = (a,b) -> a != null && a.compareTo(b) <= 0 ? a : b;
        //call foldL to find the minimum using biFun
        U min= foldLeft((U) null, l,biFun );
        //find position of the minimum and return it
        return al.indexOf(min);

    }

    public static void main (String[]args)
    {


        Iterable<Integer> foldNumbers = new ArrayList<>();
        ((ArrayList<Integer>) foldNumbers).add(1);
        ((ArrayList<Integer>) foldNumbers).add(3);
        ((ArrayList<Integer>) foldNumbers).add(5);
        ((ArrayList<Integer>) foldNumbers).add(7);
        ((ArrayList<Integer>) foldNumbers).add(9);
        ((ArrayList<Integer>) foldNumbers).add(11);
        ((ArrayList<Integer>) foldNumbers).add(20);

        List<Object> names1 = new ArrayList<>();
        ((ArrayList<Object>) names1).add("Mary");
        ((ArrayList<Object>) names1).add("Isla");
        ((ArrayList<Object>) names1).add("Sam");
        ((ArrayList<Object>) names1).add("Beth");
        ((ArrayList<Object>) names1).add("Dolly");
        ((ArrayList<Object>) names1).add("Huxtable");
        ((ArrayList<Object>) names1).add("Dan Rather");


        anInterface interface1 = name ->
        {
            return Object::hashCode;
        };

        //(1)
        System.out.println(map(names1,interface1.listIt(names1)));

        BiFunctionInterface interface2 = (number, list) ->
        {
            return Object::equals;
        };

        PredicateInterface predicateInterface = Person -> {
            return true;
        };

        //(2) Use foldleft to calculate the sum of a
        //list of integers.
        System.out.println("\nList of integers: "+foldNumbers);
        // BiFunction to add 2 numbers
        //call foldL method to sum of integers.
        int sum = foldLeft(0, foldNumbers, (a, b) -> a + b);
        System.out.println("sum of List of integers: "+sum);

        List<Integer> numList = new ArrayList<>();
        numList.add(17);
        numList.add(33);
        numList.add(5);
        numList.add(7);
        numList.add(9);
        numList.add(11);
        numList.add(20);

        List<String> stringList = new ArrayList<>();
        stringList.add("hello");
        stringList.add("juxtaposition");
        stringList.add("buckets");
        stringList.add("Air Jordan");
        stringList.add("laundry");
        stringList.add("Operation Snooperfax");
        stringList.add("grilled cheese");
        stringList.add("precious");


        // (3) Use foldRight to concatenate a list of strings
        //i.e write a method String s (List<String> l)
        List<String> listName = new ArrayList<String>();
        listName.add("Sophia");
        listName.add( "Thomas");
        listName.add( "Jacob");
        listName.add( "Abhi");
        System.out.println("\nconcatenate a list of strings: "+listName);
        // BiFunction to add 2 numbers
        BiFunction<String, String, String> strFun = (a, b) -> a + b;
        //call foldL method to concatenate a list of strings.
        String strCon = foldR(" ", listName, strFun);
        System.out.println("Concatenated string:"+strCon);

        // (4) consider an array of Persons. Use filter to print the names of the Persons whose salary is
        // greater than 100000
        List<Person> personsList = new ArrayList<Person>();
        personsList.add(new Person(200000,"Sophia"));
        personsList.add(new Person(58000,"Thomas"));
        personsList.add(new Person(100500,"Jacob"));
        personsList.add(new Person(75000,"Ruth"));
        personsList.add(new Person(208500,"Abhi"));
        //display All persons
        System.out.println("\nList of Persons:");;
        for(Person x:personsList)
            System.out.println(x.name+"\t"+x.salary );
        //Creating predicate for salary is greater than 100000
        Predicate<Person> salaryPred = (p) -> p.getSalary() > 100000;
        Iterable<Person> predicateList;
        //call filter method
        predicateList = filter(personsList, salaryPred);
        System.out.println("\nThe names of the Persons "+
                "whose salary > 100000:");
        //Loop to print persons names
        for(Person x:predicateList)
        {
            System.out.println(x.name );
        }

        // (5) Use minVal to calculate the minium of a List of Integers
        System.out.println("Minimum value in the list foldNumbers is " + minVal(numList, Integer::compare));
        // (6) Use minVal to calculate the maximum of a List of Integers
        System.out.println("Maximum value in the list foldNumbers is " + minVal(numList, Comparator.<Integer>comparingInt(x -> x).reversed()));
        // (7) Use minPos to calculate the position of the minimum in a List of Integers
        System.out.println("\nList of integers: "+numList);
        int minP = minPos(numList);
        System.out.println("\nThe position of the minimum"+
                " of a List of Integers is at index (starting at 0): " +minP );

        // (8) Use minPos to calculate the position of the
        //maximum in a List of String
        System.out.println("\nList of Strings: "+stringList);
        minP = minPos(stringList);
        System.out.println("\nThe position of the minimum"+
                " of a List of Integers is at index (starting at 0 by alphabetical sort): " +minP );
    }
}
