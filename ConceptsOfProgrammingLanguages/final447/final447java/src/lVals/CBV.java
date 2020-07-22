package lVals;

public class CBV {
    private static void swapInt(int x, int y) {
        int oldX = x;
        int oldY = y;
        x = oldY;
        y = oldX;
    }
    private static void swapRef(Integer x, Integer y) {
        Integer oldX = x;
        Integer oldY = y;
        x = oldY;
        y = oldX;
    }
    public static void main(String args[]) {
        {
            int a = 0;
            int b = 1;
            System.out.format ("a = %d, b = %d\n", a, b);
            swapInt (a, b);
            System.out.format ("a = %d, b = %d\n", a, b);
        }
        {
            Integer a = 0;
            Integer b = 1;
            System.out.format ("a = %d, b = %d\n", a, b);
            swapRef (a, b);
            System.out.format ("a = %d, b = %d\n", a, b);
        }
    }
}
