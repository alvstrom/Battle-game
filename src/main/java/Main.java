import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("The warrior of the forest");

        Scanner scanner = new Scanner(System.in);
        String input;

        // Start message
        System.out.println("State your name warrior:");
        input = scanner.nextLine();
        GameCharacter humanPlayer = new Player(input, 100);
        System.out.println("Welcome, " + humanPlayer.getName() + "!");

        do {
            humanPlayer.setHitPoints(100);
            GameCharacter computerPlayer = new Npc("Monster", 50);

            System.out.println("You walk through a forest, dark as night, when suddenly you hear a noise behind you.\n" +
                    "You turn around and see a pair of glowing eyes staring into your soul.\n" +
                    "It is a monster, now running right at you, do you fight or flee?");

            // Battle loop
            GameCharacter attacker = humanPlayer;
            GameCharacter defender = computerPlayer;

            while (humanPlayer.getHitPoints() > 0 && computerPlayer.getHitPoints() > 0) {
                // Ask player if they want to continue only if the humanPlayer is the attacker
                if (attacker == humanPlayer) {
                    System.out.println("Press any button to fight or 'q' to flee:");
                    input = scanner.nextLine();
                    if (input.equals("q")) {
                        System.out.println("You flee from the battle, you coward!");
                        return;
                    }
                }

                // Perform attack
                int attackDamage = attacker.attack(defender);
                System.out.println(attacker.getName() + " attacks " + defender.getName() +
                        " with " + attacker.getEquippedWeapon().getName() + " for " + attackDamage + " damage. " +
                        defender.getName() + " has " + defender.getHitPoints() + " HP left.");

                // Check if defender is dead
                if (defender.getHitPoints() <= 0) {
                    break;
                }

                // Swap attacker and defender
                GameCharacter temp = attacker;
                attacker = defender;
                defender = temp;
            }

            if (humanPlayer.getHitPoints() > 0) {
                System.out.println(humanPlayer.getName() + " wins!");
                System.out.println("You have defeated the monster and saved the forest! \n" +
                        "Do you wish to fight another monster? If so, press any button to continue, if not, press 'q' to quit.");
                input = scanner.nextLine();
                if (input.equals("q")) {
                    System.out.println("You have chosen to quit the game, goodbye!");
                    return;
                } else {
                    System.out.println("You have chosen to continue, good luck!");
                }
            } else {
                System.out.println(computerPlayer.getName() + " wins!");
                System.out.println("You have been defeated by the monster, you have failed to save the forest! \n" +
                        "Do you wish to try again? If so, press any button to continue, if not, press 'q' to quit.");
                input = scanner.nextLine();
                if (input.equals("q")) {
                    System.out.println("You have chosen to quit the game, goodbye!");
                    return;
                } else {
                    System.out.println("You have chosen to try again, good luck!");
                }
            }
        } while (!input.equals("q"));
    }
}