package AdventureGame;

public class King extends Character
{
    public King()
    {
        weaponBehavior = new SwordBehavior();
    }

    public void display()
    {
        System.out.println("I'm a king!");
    }
}
