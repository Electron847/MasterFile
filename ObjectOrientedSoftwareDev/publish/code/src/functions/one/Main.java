package functions.one;
interface ArrFun {
  public int[] exec(int[] x);
}

interface IntFun {
  public int exec(int x);
}

class Abs implements IntFun {
  public int exec(int x) {
    //SAME AS: if (x < 0) return -x; else return x;
    return (x < 0) ? -x : x;
  }
}

class Cube implements IntFun {
  public int exec(int x) {
    return x*x*x;
  }
}

class Map implements ArrFun {
  IntFun _f;
  public Map(IntFun f) { _f = f; }
  public int[] exec(int[] x) {
    int[] answer = new int[x.length];
    for (int i=0; i<x.length; i++) {
      answer[i] = _f.exec(x[i]);
    }
    return answer;
  }
}

class Main {
  public static void print(int[] x) {
    System.out.print("[ ");
    for (int i=0; i<x.length; i++)
      System.out.print(x[i]+ " ");
    System.out.println("]");
  }
    
  public static void main(String[] argv) {
    IntFun f = new Abs();
    System.out.println(f.exec(-5));

    int[] a = new int[10];
    for (int i=0; i<a.length; i++)
      a[i] = -i*10;

    ArrFun mabs = new Map(new Comp(new Abs(), new Cube()));
    print(mabs.exec(a));
  }
}

class Comp implements IntFun {
  IntFun _f, _g;
  public Comp(IntFun f, IntFun g) { _f = f; _g = g; }
  public int exec(int x) {
    return _g.exec(_f.exec(x));
  }
}
