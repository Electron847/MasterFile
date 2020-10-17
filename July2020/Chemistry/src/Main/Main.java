package Main;
import Elements.*;

import java.util.Arrays;

public class Main {
    public static void main(String[] args){
        Hydrogen hydrogen1 = new Hydrogen();
        Helium helium1 = new Helium();
        Lithium lithium1 = new Lithium();
        Carbon carbon1 = new Carbon();
        Oxygen oxygen1 = new Oxygen();
        System.out.println(helium1.electrons());
        System.out.println(hydrogen1.electrons());
        System.out.println(lithium1.mass());
        System.out.println(Arrays.toString(carbon1.electronIsotopes()));
        System.out.println(Arrays.toString(oxygen1.electronIsotopes()));
    }
}
