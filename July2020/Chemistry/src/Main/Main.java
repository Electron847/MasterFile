package Main;
import Elements.*;

public class Main {
    public static void main(String[] args){
        Hydrogen hydrogen1 = new Hydrogen();
        Helium helium1 = new Helium();
        Lithium lithium1 = new Lithium();
        System.out.println(helium1.electrons());
        System.out.println(hydrogen1.electrons());
        System.out.println(lithium1.mass());
    }
}
