import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;
import java.io.*;

public class Main {
    private static final String SAVE_FILE = "save.ser";

    public static void main(String[] args) {
        System.out.println("The warrior of the forest");
        Player player = null;
        Scanner scanner = new Scanner(System.in);

        // Check if save file exists
        File saveFile = new File(SAVE_FILE);
        if (saveFile.exists()) {
            if (Utils.getYesOrNo(scanner, "A save file was found, do you wish to use it? (y/n)")) {
                try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(SAVE_FILE))) {
                    player = (Player) ois.readObject();
                    System.out.println("Save loaded.");
                    System.out.println("Welcome back, " + player.getName() + "!");
                } catch (IOException | ClassNotFoundException e) {
                    System.out.println("Could not load save file, creating new player.");
                }
            }
        }

        // If no saved player exists, create a new one
        if (player == null) {
            String input;
            do {
                System.out.println("State your name, warrior:");
                input = scanner.nextLine();
                if (!input.matches("[a-zA-Z]+")) {
                    System.out.println("Invalid input. Please use letters only.");
                }
            } while (!input.matches("[a-zA-Z]+"));

            player = new Player(input, 100);
            System.out.println("Welcome, " + player.getName() + "!");
        }

        do {
            player.setHitPoints(100);
            GameCharacter computerPlayer = Npc.spawnNpc();

            System.out.println("You walk through a forest, dark as night, when suddenly you hear a noise behind you.\n" +
                    "You turn around and see a pair of glowing eyes staring into your soul.\n" +
                    "It is a frightening " + computerPlayer.getName() + ", now running right at you. Do you fight or flee?");

            // Battle loop
            GameCharacter attacker = player;
            GameCharacter defender = computerPlayer;

            while (player.getHitPoints() > 0 && computerPlayer.getHitPoints() > 0) {
                if (attacker == player) {
                    System.out.println("Choose your weapon ('q' to flee):");

                    for (int i = 0; i < player.getInventory().size(); i++) {
                        Weapon weapon = player.getInventory().get(i);
                        System.out.println((i + 1) + ". " + weapon.getName() + " (Damage: " + weapon.getDamage() + ")");
                    }

                    try {
                        String input = scanner.nextLine();
                        if (input.equals("q")) {
                            System.out.println("You flee from the battle, you coward!");
                            return;
                        }
                        int weaponChoice = Integer.parseInt(input) - 1;
                        player.chooseWeapon(weaponChoice);
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input. Please enter a valid number.");
                        continue;
                    }
                }

                // Perform attack
                int attackDamage = attacker.attack(defender);
                System.out.println(attacker.getName() + " attacks " + defender.getName() +
                        " with " + attacker.getEquippedWeapon().getName() + " for " + attackDamage + " damage. " +
                        defender.getName() + " has " + defender.getHitPoints() + " HP left.");

                if (defender.getHitPoints() <= 0) {
                    break;
                }

                if (attacker == player && findHeal() && player.getHitPoints() < 65) {
                    int healAmount = randomHeal(player);
                    System.out.println("You found a healing potion and healed for " + healAmount + " HP. You now have " + player.getHitPoints() + " HP.");
                }

                // Swap attacker and defender
                GameCharacter temp = attacker;
                attacker = defender;
                defender = temp;
            }

            if (player.getHitPoints() > 0) {
                System.out.println(player.getName() + " wins!");
                if (Utils.getYesOrNo(scanner, "You have defeated the monster and saved the forest! \nDo you wish to pick up the enemy's weapon? (y/n)")) {
                    player.addWeaponToInventory(computerPlayer.getEquippedWeapon());
                    System.out.println("You have picked up " + computerPlayer.getEquippedWeapon().getName() + ".");
                }

                if (Utils.getYesOrNo(scanner, "Do you wish to delete a weapon from your inventory? (y/n)")) {
                    System.out.println("Choose the weapon to delete:");
                    for (int i = 0; i < player.getInventory().size(); i++) {
                        Weapon weapon = player.getInventory().get(i);
                        System.out.println((i + 1) + ". " + weapon.getName() + " (Damage: " + weapon.getDamage() + ")");
                    }
                    try {
                        int weaponChoice = Integer.parseInt(scanner.nextLine()) - 1;
                        player.removeWeaponFromInventory(weaponChoice);
                        System.out.println("Weapon removed from inventory.");
                    } catch (NumberFormatException | IndexOutOfBoundsException e) {
                        System.out.println("Invalid input. No weapon removed.");
                    }
                }


                if (!Utils.getYesOrNo(scanner, "Do you wish to fight another monster? (y/n)")) {
                    System.out.println("You have chosen to quit the game, goodbye!");
                    if (Utils.getYesOrNo(scanner, "Do you want to save the game? (y/n)")) {
                        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(SAVE_FILE))) {
                            oos.writeObject(player);
                            System.out.println("Game saved.");
                        } catch (IOException e) {
                            System.out.println("Could not save the game.");
                        }
                    }
                    scanner.close();
                    return;
                } else {
                    System.out.println("You have chosen to continue, good luck!");
                }
            } else {
                System.out.println(computerPlayer.getName() + " wins!");
                System.out.println("You have been defeated by the monster, you have failed to save the forest! \n" +
                        "Do you wish to try again? If so, press any button to continue, if not, press 'q' to quit.");
                String input = scanner.nextLine();
                if (input.equals("q")) {
                    System.out.println("You have chosen to quit the game, goodbye!");
                    if (Utils.getYesOrNo(scanner, "Do you wish to save your game (y/n)")) {
                        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(SAVE_FILE))) {
                            oos.writeObject(player);
                            System.out.println("Game saved.");
                        } catch (IOException e) {
                            System.out.println("Error, could not save game.");
                        }
                    }

                    scanner.close();
                    return;
                } else {
                    System.out.println("You have chosen to try again, good luck!");
                }
            }
        } while (true);
    }

    // Method to randomly determine if a heal is found
    private static boolean findHeal() {
        Random random = new Random();
        return random.nextInt(100) < 20; // 20% chance to find a heal
    }

    // Small or big heal, randomized
    private static int randomHeal(Player player) {
        Random random = new Random();
        return random.nextBoolean() ? player.heal() : player.bigHeal();
    }
}