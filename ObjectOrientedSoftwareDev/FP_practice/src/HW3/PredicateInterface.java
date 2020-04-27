package HW3;

import java.util.function.Predicate;

public interface PredicateInterface extends Predicate<Object> {
   // Predicate predicateFunc(Iterable<Object> myList1);

    @Override
    boolean test(Object o);
}
