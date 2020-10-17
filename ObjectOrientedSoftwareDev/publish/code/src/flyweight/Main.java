package flyweight;
public class Main {
  public static void main (String[] args) {
    Video v1 = Data.getVideo("Taxi Driver", 1976, "Scorsese");
    Video v2 = Data.getVideo("Taxi Driver", 1976, "Scorsese");
    System.out.println(v1==v2);
  }
}
