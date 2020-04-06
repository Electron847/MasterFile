package AdventureGame;

public abstract class Character
{
    WeaponBehavior weaponBehavior;

    public Character(){}

    public void attack()
    {
        weaponBehavior.useWeapon();
    }
}
