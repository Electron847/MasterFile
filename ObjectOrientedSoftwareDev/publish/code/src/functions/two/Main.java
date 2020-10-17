package functions.two;
interface Stream {
  int next();
}

class IntStream implements Stream { 
  private int _i = -1;
  public int next() {
    return ++_i;
  } 
}

interface Predicate{ 
  boolean evaluate(int j);
}

class FilteredStream implements Stream{ 
  private Stream _it;
  private Predicate _p;
  
  public FilteredStream(Stream it, Predicate p) {
    _it = it;
    _p =p;
  }
  
  public int next() {
    int i;
    do {
      i = _it.next();
    } while (!_p.evaluate(i));
    return i;
  }
}

class IsEven implements Predicate {
  public boolean evaluate(int j){
     return (j%2)== 0;
   }
}
class Main {
  public static void main (String[] args) {
    IntStream I = new IntStream();
    FilteredStream F = new FilteredStream(I, new IsEven());
    System.out.println(F.next());   // prints 0 on the screen
    System.out.println(F.next());   // prints 2 on the screen
    System.out.println(F.next());   // prints 4 on the screen
    System.out.println(F.next());   // prints 6 on the screen
    System.out.println(F.next());   // prints 8 on the screen
    
    IntStream J = new IntStream();
    J.next();                       // move forward one item in J
    FilteredStream G = new FilteredStream(J, new IsEven());
    System.out.println(G.next());   // prints 2 on the screen
    System.out.println(G.next());   // prints 4 on the screen
    System.out.println(G.next());   // prints 6 on the screen
    System.out.println(G.next());   // prints 8 on the screen
    
    IntStream K = new IntStream();
    class Div3 implements Predicate {
      public boolean evaluate(int n) { return (n%3) == 0; }
    }
    FilteredStream H = new FilteredStream(K, new Div3());
    System.out.println(H.next());   // prints 0 on the screen
    System.out.println(H.next());   // prints 3 on the screen
    System.out.println(H.next());   // prints 6 on the screen
    System.out.println(H.next());   // prints 9 on the screen
  }
}
