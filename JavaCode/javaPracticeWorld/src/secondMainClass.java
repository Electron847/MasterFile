import java.util.*;

public class secondMainClass {
    public static void main(String[] args) {
        System.out.println("Hello World number 2!");



        ArrayList baseData = new ArrayList(10);
        int k = new Random().nextInt(100);

        for (int i = 0; i <= k; i++){
            byte j = ((byte) new Random().nextInt());
            baseData.add(i, i + j);
            //System.out.println(baseData.size());
            //System.out.println(baseData);
        }
        System.out.println(baseData);

       /* for (int i = 0; i <= baseData.size(); i++) {
            args[i] = baseData.get(i).toString();
            System.out.println(args);
        }*/

        int[] data = new int[baseData.size()];
        for (int i = 0; i < baseData.size(); i++) {
            data[i] = baseData.get(i).hashCode();
        }

        int r = 0, s = 0;

        for (int i = 0; i < data.length; i++) {
            if (data[i] != 0) {
                s = i + 1;
            }
            r = Integer.max (r, i + 1 - s);
        }

        System.out.println ("length of longest segment containing all zeroes = " + r);
    }
}

