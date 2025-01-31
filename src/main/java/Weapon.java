import java.io.Serializable;
import java.util.Random;

public class Weapon implements Serializable {
    private String name;
    private int damage;


    // Constructor
    public Weapon(String name, int damage) {
        this.name = name;
        this.damage = damage;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    @Override
    public String toString() {
        return name + " (" + damage + " damage)";
    }
}