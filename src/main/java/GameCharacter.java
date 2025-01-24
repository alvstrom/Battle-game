import java.util.Random;

public abstract class GameCharacter {
    private String name;


    private int hitPoints;
    double dexterity;
    private Weapon equippedWeapon;

    // Konstruktor
    public GameCharacter(String name, int hitPoints) {
        this.name = name;
        this.hitPoints = hitPoints;
    }


    public int attack(GameCharacter defender) {
        Random random = new Random();
        int minDamage = (int) (equippedWeapon.getDamage() * dexterity);
        int maxDamage = equippedWeapon.getDamage();
        int attackDamage = random.nextInt(maxDamage - minDamage + 1) + minDamage;
        defender.takeDamage(attackDamage);
        return attackDamage;
    }

    public Weapon getEquippedWeapon() {
        return equippedWeapon;
    }

    public void setEquippedWeapon(Weapon equippedWeapon) {
        this.equippedWeapon = equippedWeapon;
    }

    public void setName(String name) {
        this.name = name;

    }

    public String getName() {
        return name;
    }

    public int getHitPoints() {
        return hitPoints;
    }
    public void setHitPoints(int hitPoints) {
        this.hitPoints = Math.max(hitPoints, 0);
    }

    // minskar hitPoints när man blir attackerad
    public void takeDamage(int damage) {
        this.hitPoints -= damage;
        if (this.hitPoints < 0) {
            this.hitPoints = 0;
        }
    }


}