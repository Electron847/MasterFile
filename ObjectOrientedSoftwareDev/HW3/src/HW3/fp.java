package HW3;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.BiFunction;
import java.util.function.Predicate;
import java.util.Comparator;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;


class iterableContainer<U>
{
    U item1;

    public iterableContainer(U item1)
    {
        this.item1 = item1;
    }
    public U getItem1()
    {
        return item1;
    }
    public void setItem1()
    {
        this.item1 = item1;
    }
}

class listContainer<V>
{
    V item1;

    public listContainer(V item1)
    {
        this.item1 = item1;
    }
    public V getItem1()
    {
        return item1;
    }
    public void setItem1()
    {
        this.item1 = item1;
    }
}

public class fp {







    static <U,V> List<V> map(Iterable<U> l, Function<U,V> f)
    {
        List<U> listFromIterable = StreamSupport.stream(l.spliterator(),false).collect(Collectors.toList());
        //listFromIterable.stream().map()
        List<V> hashList = new ArrayList<V>();

        Function<U, V> mapFunction = (List<U> ourList) ->
        {
            for (int i = 0; i < ((ArrayList<U>) listFromIterable).size() - 1; i++) {
                ((ArrayList<Integer>) hashList).add(i, ((ArrayList<String>) listFromIterable).get(i).hashCode());
            }
            return (V) hashList;
        };


        /*List<V> hashList = new ArrayList<>();
        l.iterator().forEachRemaining(value -> f.apply(value).hashCode());
        System.out.println(hashList);*/
        //return hashList;


        return null;
    }








    static <U,V> V foldLeft(V e, Iterable<U>l, BiFunction<V,U,V> f)
    {

        List<U> numbers = StreamSupport.stream(l.spliterator(),false).collect(Collectors.toList());

        return null;
        //return numbers.stream()
               // .reduce(0, (acc, n) -> (acc + n));
    }

    static <U,V> V foldRight(V e, List<U>l, BiFunction<U,V,V> f){
        return null;
    }



    static <U> Iterable<U> filter(Iterable<U> l, Predicate<U> p)
    {


        return null;
    }

    static <U> U minVal(Iterable<U> l, Comparator<U> c){
        // write using fold.  No other loops or recursion permitted.
        return null;
    }

    static <U extends Comparable<U>> int minPos(Iterable<U> l){
        // write using fold.  No other loops or recursion permitted.
        return 0;
    }

    public static void main(String[] args) {


        //Function myFunction =

        Iterable<String> names = new ArrayList<>();
        ((ArrayList<String>) names).add("Mary");
        ((ArrayList<String>) names).add("Isla");
        ((ArrayList<String>) names).add("Sam");
        ((ArrayList<String>) names).add("Beth");
        ((ArrayList<String>) names).add("Dolly");
        ((ArrayList<String>) names).add("Huxtable");
        ((ArrayList<String>) names).add("Dan Rather");
        //map(names)


        //System.out.println(map(names, MyInterface1<names, func));


        // (1) Use map to implement the following behavior (described in Python).  i.e given a List<T> create a List<Integer> of the hashes of the objects.
        // names = ['Mary', 'Isla', 'Sam']
        // for i in range(len(names)):
        // names[i] = hash(names[i])

        // (2) Use foldleft to calculate the sum of a list of integers.
        // i.e write a method: int sum(List<Integer> l)

        // (3) Use foldRight to concatenate a list of strings i.e write a method
        // String s (List<String> l)

        // (4) consider an array of Persons. Use filter to
        // print the names of the Persons whose salary is
        // greater than 100000

        // (5) Use minVal to calculate the minimum of a List of
        //       Integers

        // (6) Use minVal to calculate the maximum of a List of
        //       Integers

        // (7)  Use minPos to calculate the position of the
        // minimum in  a List of  Integers

        // (8)  Use minPos to calculate the position of the
        // minimum in  a List of  String
    }

}

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