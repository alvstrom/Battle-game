import java.io.Serializable;
import java.util.Random;

public class Npc extends GameCharacter implements Serializable {

    private static final String[] NAMES = {"Goblin", "Orc", "Troll", "Dragon"};
    private static final String[] WEAPON_NAMES = {"Claws", "Teeth", "Tail", "Horns"};
    private static final int MIN_DAMAGE = 7;
    private static final int MAX_DAMAGE = 15;
    private static final Random random = new Random();

    public static GameCharacter spawnNpc() {
        String name = NAMES[random.nextInt(NAMES.length)];
        int hitPoints = random.nextInt(50, 101);
        Npc npc = new Npc(name, hitPoints);
        String weaponName = WEAPON_NAMES[random.nextInt(WEAPON_NAMES.length)];
        int weaponDamage = random.nextInt(MIN_DAMAGE, MAX_DAMAGE + 1);
        Weapon weapon = new Weapon(weaponName, weaponDamage);
        npc.setEquippedWeapon(weapon);
        npc.addWeaponToInventory(weapon);
        return npc;
    }

    public Npc(String name, int hitPoints) {
        super(name, hitPoints);
        dexterity = 0.5;
    }
}