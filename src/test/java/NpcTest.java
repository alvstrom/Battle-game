// src/test/java/NpcTest.java

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NpcTest {

    @Test
    void testNpcInstantiation() {
        Npc npc = new Npc("Goblin", 50);
        assertEquals("Goblin", npc.getName());
        assertEquals(50, npc.getHitPoints());
    }

    @Test
    void testNpcAttack() {
        Player player = new Player("Warrior", 100);
        Npc npc = new Npc("Goblin", 50);
        Weapon claws = new Weapon("Claws", 10);
        npc.setEquippedWeapon(claws);

        int initialPlayerHitPoints = player.getHitPoints();
        int initialNpcHitPoints = npc.getHitPoints();

        npc.attack(player);

        assertTrue(player.getHitPoints() < initialPlayerHitPoints);
        assertEquals(initialNpcHitPoints, npc.getHitPoints());
    }

    @Test
    void spawnNpc() {
        GameCharacter npc = Npc.spawnNpc();
        assertNotNull(npc);
        assertTrue(npc instanceof Npc);
        assertNotNull(npc.getName());
        assertTrue(npc.getHitPoints() >= 50 && npc.getHitPoints() <= 100);
        assertNotNull(npc.getEquippedWeapon());
        assertTrue(npc.getEquippedWeapon().getDamage() >= 7 && npc.getEquippedWeapon().getDamage() <= 15);
    }
}