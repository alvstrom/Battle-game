// src/test/java/PlayerTest.java

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    @Test
    void testPlayerInstantiation() {
        Player player = new Player("Warrior", 100);
        assertEquals("Warrior", player.getName());
        assertEquals(100, player.getHitPoints());
    }

    @Test
    void testPlayerAttack() {
        Player player = new Player("Warrior", 100);
        Npc npc = new Npc("Goblin", 50);
        Weapon sword = new Weapon("Sword", 10);
        player.setEquippedWeapon(sword);

        int initialNpcHitPoints = npc.getHitPoints();
        int initialPlayerHitPoints = player.getHitPoints();

        player.attack(npc);

        assertTrue(npc.getHitPoints() < initialNpcHitPoints);
        assertEquals(initialPlayerHitPoints, player.getHitPoints());
    }
}