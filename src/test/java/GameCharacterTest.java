import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameCharacterTest {

    @Test
    void testNameGetter() {
        GameCharacter g = new Player("foo", 100);
        assertEquals("foo", g.getName());
    }

    @Test
    void testTakeDamage() {
        GameCharacter g = new Player("foo", 100);
        g.takeDamage(30);
        assertEquals(70, g.getHitPoints());
    }
}