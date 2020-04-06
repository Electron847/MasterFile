package AdventureGame;

public class AdventureGameSimulator
{
    public static void main (String[] args)
    {
        Character king = new King();
        ((King) king).display();
        king.attack();
    }
}
