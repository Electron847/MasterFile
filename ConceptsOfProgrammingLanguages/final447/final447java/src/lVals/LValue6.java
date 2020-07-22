package lVals;

// yes, array
public class LValue6 {
    static void f (int x) {
        int[] arr = { 5, 6, 7, 8, 9, 10, 11 };
        System.out.println(arr[x++]);
    }
    public static void main (String[] args) {
        f(2);
    }
}
