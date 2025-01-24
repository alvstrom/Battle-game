public class Player extends GameCharacter {
    public Player(String name, int hitPoints) {
        super(name, hitPoints);
        dexterity = 0.8;
        setEquippedWeapon(new Weapon("Sword", 10));
    }
}
