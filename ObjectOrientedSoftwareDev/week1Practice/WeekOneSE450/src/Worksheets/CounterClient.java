package Worksheets;

public class CounterClient
{
    public static void main (String[]args)
    {
        Counter c = new Reg();
        int x = c.get();
        System.out.println(x);
        c.inc();
        System.out.println(x);
        c.set(5);
        System.out.println(x);

    }
}
