package HW3;

import java.util.LinkedList;

public class StackImpl implements Stack{
    LinkedList<Integer> l = new LinkedList<Integer>();
    public void push(Integer o) {
        l.add(o);
    }
    public int pop() {
        return l.getLast();
    }
    public boolean isEmpty() {
        return l.size()==0;
    }

    public void foo() { }

}