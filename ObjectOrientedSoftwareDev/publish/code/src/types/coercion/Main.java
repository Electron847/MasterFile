package types.coercion;
class Main {
  private Main() {}
  public static void main(String[] args) {
    int i0 = 1;
    float f0 = i0;         // compiles ok, runs ok (implicit up-coercion)
    float f1 = (float) i0; // compiles ok, runs ok (explicit up-coercion)

    float f2 = 1.42f;
//  int i2 = f2;       // compiler error (implicit down-coercion)
    int i3 = (int) f2; // compiles ok, runs ok (explicit down-coercion)

    // Note that coercion changes the actual value, not just the
    // declared type.
    float f3 = i3;
    System.out.println("f2=" + f2);
    System.out.println("f3=" + f3);
  }
}
class CoercionOrdering {
  private CoercionOrdering() {}
  public static void showingCoercionOrder() {
    // booleans and object types do not coerce
    // use ?: to convert booleans to other types
    // use ?: also for object types
    boolean p = false;
    int j = p ? 1 : 0;
    Object o = null;
    int k = (null==o) ? 0 : 1;

    // char and short are both 16 bit, but mutually incomparable
    char  c = '\0';
    short z = 0;
          z = (short) c;
          c = (char) z;
    
    // number types from bottom to top
    byte   b = 0; 
    short  s = b;
    int    i = s;
    long   l = i;
    float  f = l;
    double d = f;

    // number types from top to bottom, losing precision
    f = (float) d;
    l = (long) f;
    i = (int) l;
    s = (short) i;
    b = (byte) s;
  }
}
