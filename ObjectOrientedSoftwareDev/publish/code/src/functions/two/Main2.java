package functions.two;
class NotDivn implements Predicate {
  final private int _n;
  NotDivn(int n) {
    _n = n;
  }
  public boolean evaluate(int m) {
    return (m%_n) != 0;
  }
}

class WhatAPain implements Stream{ 
  private Stream _it;
  
  public WhatAPain(Stream it) {
    _it = it;
  }
  
  public int next() {  
    final int n = _it.next();
    final Predicate d = new NotDivn(n);
    Stream newit = new FilteredStream(_it, d);
    _it = newit;
    return (n);
  } 
}

class Main2 {
  public static void main(String[] args) {
    IntStream I = new IntStream();
    System.out.println(I.next());   // prints out 0 on the screen
    System.out.println(I.next());   // prints out 1 on the screen
    
    WhatAPain w = new WhatAPain(I); 
    System.out.println(w.next());   
    System.out.println(w.next()); 
    System.out.println(w.next());   
    System.out.println(w.next());  
    System.out.println(w.next());   
    System.out.println(w.next()); 
    System.out.println(w.next());   
    System.out.println(w.next());   
    System.out.println(w.next());   
    System.out.println(w.next());             
  }
}  


