import java.util.Scanner;

public class GameLogic {
    private Player player;
    private Room currentRoom;

    public GameLogic(Player player, Room startingRoom) {
        this.player = player;
        this.currentRoom = startingRoom;
    }

    public void play() {
        Scanner scanner = new Scanner(System.in);

        while (player.isAlive() && currentRoom != null) {
            System.out.println("Current Room: " + currentRoom.getDescription());

            if (currentRoom.getEnemy() != null) {
                // Handle combat
                System.out.println("Do you want to attack? (yes/no)");
                String userInput = scanner.nextLine();

                if ("yes".equalsIgnoreCase(userInput)) {
                    int damage = player.attack1(currentRoom.getEnemy().getEnemyAc());
                    currentRoom.getEnemy().updateHealth(damage);
                    System.out.println("You hit the enemy for " + damage + " damage!");
                } else {
                    System.out.println("You decided not to attack.");
                }

                // ... Handle enemy's attack and check for player defeat ...
            }

            // Handle other room interactions and move to the next room based on user input
            // For simplicity, let's assume there's always a next room
            System.out.println("Do you want to proceed to the next room? (yes/no)");
            String userInput = scanner.nextLine();

            if ("yes".equalsIgnoreCase(userInput)) {
                currentRoom = currentRoom.getNextRoom();
            } else {
                System.out.println("You decided not to proceed to the next room. The adventure has ended.");
                break;
            }
        }

        System.out.println("Game Over! The adventure has ended.");
    }
}