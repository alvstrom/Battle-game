import java.io.Serializable;
import java.util.Random;

public class Player extends GameCharacter implements Serializable {

    public int heal() {
        Random random = new Random();
        int minHeal = 5;
        int maxHeal = 15;
        int healAmount = random.nextInt(maxHeal - minHeal + 1) + minHeal;
        setHitPoints(Math.min(getHitPoints() + healAmount, 100));
        return healAmount;
    }

    public int bigHeal() {
        Random random = new Random();
        int minHeal = 10;
        int maxHeal = 30;
        int bigHealAmount = random.nextInt(maxHeal - minHeal + 1) + minHeal;
        setHitPoints(Math.min(getHitPoints() + bigHealAmount, 100));
        return bigHealAmount;
    }

    public Player(String name, int hitPoints) {
        super(name, hitPoints);
        dexterity = 0.8;
        setEquippedWeapon(new Weapon("Sword", 10));
        addWeaponToInventory(getEquippedWeapon());
    }
}
