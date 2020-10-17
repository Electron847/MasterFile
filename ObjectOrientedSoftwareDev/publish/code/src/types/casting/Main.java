package types.casting;
// class Number {} 
// class Integer extends Number {} 
// class Float extends Number {} 

public class Main {
  private Main() {}
  public static void main(String[] args) {
    // Upcasts always compile and run ok
    Number n1 = new Integer(42);          // implicit upcast
    Number n2 = (Number) new Float(3.14); // explicit upcast

    // Explicit downcasts always compile, but may cause runtime errors
//  Integer i1 = n1;           // implicit downcast: compiler error
    Integer i2 = (Integer) n1; // explicit downcast: runtime success
    Integer i3 = (Integer) n2; // explicit downcast: runtime error

    // Crosscasts are always disallowed by the compiler
    // So Integer cannot cast to Float
//  Float f1 = (Float) i2;          // crosscast: compiler error
    Float f2 = (Float) (Number) i2; // upcast+downcast: runtime error

    // Casting changes the declared type, but not the actual type
    System.out.println("Object n2=" + ((Object) n2));
    System.out.println("Number n2=" + n2);
    System.out.println("Float  n2=" + ((Float) n2));
  }
}
