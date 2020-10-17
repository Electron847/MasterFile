package basics.mixedclass;
public class Main {
  private Main() {}
  static public void main (final String[] args) {
    try {
      if (args.length != 1)
        throw new NumberFormatException();
      int vx = Integer.parseInt(args[0]);
      Integer rx = new Integer(vx);
      System.out.println("  Number: " + rx.toString());
      System.out.println("Hashcode: " + rx.hashCode());

    } catch (NumberFormatException e) {
      System.out.println("Error: Bad input");
    }
  }
}
