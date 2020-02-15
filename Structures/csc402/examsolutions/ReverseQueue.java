//Seth Weber

package csc402examsolutions;
import java.util.*;
import algs13.Queue;

public class ReverseQueue {
	
	public static void main(String [] args) {
		
		Queue q = new Queue();
		Queue q3 = new Queue();
		
		Random rand = new Random();
		for (int i = 0; i < 16; i++) {
			int rand1 = rand.nextInt((2000 + 1)-1000);
			q.enqueue(rand1);
		}
		
		System.out.println("The Queue q is: "+ q);
		q3 = reverseQueue(q);
		System.out.println("The Reveresed Queue is: "+ q3);
	
}
	
public static Queue reverseQueue(Queue q2) {
	
	Stack s1 = new Stack();
	
	
	while (!q2.isEmpty())
		s1.push(q2.dequeue());
	while (!s1.isEmpty())
		q2.enqueue(s1.pop());

	return q2;
}


}
