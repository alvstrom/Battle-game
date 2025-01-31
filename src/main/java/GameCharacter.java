import java.io.Serializable;
import java.util.Random;
import java.util.ArrayList;

public abstract class GameCharacter implements Serializable {
    private String name;
    private int hitPoints;
    double dexterity;
    private Weapon equippedWeapon;
    private ArrayList<Weapon> inventory;


    // Konstruktor
    public GameCharacter(String name, int hitPoints) {
        this.name = name;
        this.hitPoints = hitPoints;
        this.inventory = new ArrayList<>();
    }


    // Getter och setter för inventory
    public ArrayList<Weapon> getInventory() {
        return inventory;
    }

    public void setInventory(ArrayList<Weapon> inventory) {
        this.inventory = inventory;
    }

    // add weapon to inventory
    public void addWeaponToInventory(Weapon weapon) {
        inventory.add(weapon);
    }

    // remove weapon from inventory
    public void removeWeaponFromInventory(int index) {
        if (index >= 0 && index < inventory.size()) {
            inventory.remove(index);
        }
    }

    // choose weapon from inventory
    public void chooseWeapon(int index) {
        if (index >= 0 && index < inventory.size()) {
            setEquippedWeapon(inventory.get(index));
        }
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