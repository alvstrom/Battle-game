import org.junit.jupiter.api.Test;

import java.io.*;

import static org.junit.jupiter.api.Assertions.*;

class SaveGameTest {

    private static final String SAVE_FILE = "test_save.ser";

    @Test
    void testSaveGameFunctionality() {

        Player originalPlayer = new Player("Warrior", 100);
        Weapon sword = new Weapon("Sword", 10);
        originalPlayer.setEquippedWeapon(sword);
        originalPlayer.addWeaponToInventory(sword);

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(SAVE_FILE))) {
            oos.writeObject(originalPlayer);
        } catch (IOException e) {
            fail("Failed to save the player object: " + e.getMessage());
        }

        Player loadedPlayer = null;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(SAVE_FILE))) {
            loadedPlayer = (Player) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            fail("Failed to load the player object: " + e.getMessage());
        }

        assertNotNull(loadedPlayer);

        assertEquals(originalPlayer.getName(), loadedPlayer.getName());
        assertEquals(originalPlayer.getEquippedWeapon().getName(), loadedPlayer.getEquippedWeapon().getName());
        assertEquals(originalPlayer.getEquippedWeapon().getDamage(), loadedPlayer.getEquippedWeapon().getDamage());
    }
}