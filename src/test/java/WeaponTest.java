import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WeaponTest {

    void testWeaponInstantiation() {
        Weapon w = new Weapon("Sword", 10);
        assertEquals("Sword", w.getName());
        assertEquals(10, w.getDamage());
    }
}