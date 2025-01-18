import java.util.Random;

public class Main {
    public static void main(String[] args) {
        System.out.println("Let the battle begin!");


        GameCharacter humanPlayer = new GameCharacter("Human", 100);
        GameCharacter computerPlayer = new GameCharacter("Ghoul", 50);

        // Startmessage
        System.out.println("A scary-looking Ghoul runs towards you. You decide to attack.");

        // Battle loop
        int damagePerAttack = 20; // Skada per attack
        while (humanPlayer.getHitPoints() > 0 && computerPlayer.getHitPoints() > 0) {
            // Human plauer attacks
            computerPlayer.takeDamage(damagePerAttack);
            System.out.println(humanPlayer.getName() + " attacks " + computerPlayer.getName() +
                    " for " + damagePerAttack + " HP. " + computerPlayer.getName() + " has " +
                    computerPlayer.getHitPoints() + " HP left.");

            // Checkar om datorspelaren är död
            if (computerPlayer.getHitPoints() <= 0) {
                break;
            }

            // Datorspelare attackerar
            humanPlayer.takeDamage(damagePerAttack);
            System.out.println(computerPlayer.getName() + " attacks " + humanPlayer.getName() +
                    " for " + damagePerAttack + " HP. " + humanPlayer.getName() + " has " +
                    humanPlayer.getHitPoints() + " HP left.");
        }


        if (humanPlayer.getHitPoints() > 0) {
            System.out.println(humanPlayer.getName() + " wins!");
        } else {
            System.out.println(computerPlayer.getName() + " wins!");
        }
    }
}