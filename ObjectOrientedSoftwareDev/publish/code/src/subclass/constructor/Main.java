package subclass.constructor;
class Main {
  public static void main(String[] argv) {
    new X();
    new X();
  }
}
class O {
  {
    System.err.println("<O Initializer>");
  }
  O() {
    System.err.println("<O Constructor>");
  }
}
class X extends O {
  {
    System.err.println("<X Initializer>");
  }
  X() {
    System.err.println("<X Constructor>");
  }
}
  
