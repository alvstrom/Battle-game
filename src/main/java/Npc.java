import java.util.Random;

public class Npc extends GameCharacter {
    private static final String[] WEAPON_NAMES = {"Claws", "Teeth", "Tail", "Horns"};
    private static final int MIN_DAMAGE = 5;
    private static final int MAX_DAMAGE = 15;
    private static final Random random = new Random();

    public Npc(String name, int hitPoints) {
        super(name, hitPoints);
        String weaponName = WEAPON_NAMES[random.nextInt(WEAPON_NAMES.length)];
        dexterity = 0.5;
        int weaponDamage = random.nextInt(MIN_DAMAGE, MAX_DAMAGE + 1);
        setEquippedWeapon(new Weapon(weaponName, weaponDamage));
    }
}
