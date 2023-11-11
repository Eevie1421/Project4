import java.util.Scanner;

public class GameLogic {
    private Player player;
    private Room currentRoom;
    private Map map;

    public GameLogic(Player aPlayer, Room startingRoom) {
        this.player = aPlayer;
        this.currentRoom = startingRoom;
        this.map = new Map();
        player.setCurrentRoom(0);
    }

    private boolean moveRooms(Player p, Integer pointer) {
        if(!map.getRoom(pointer).isLocked()) {
            enterRoom(p, pointer);
            return true;
        }
        else {
            if(!map.getRoom(pointer).unlock(p)) {
                enterRoom(p, pointer);
                return true;
            }
        }
        return false;
    }
    private void enterRoom(Player p, Integer pointer) {
        map.getRoom(p.getCurrentRoom()).removePlayer(p);
        p.setCurrentRoom(pointer);
        map.getRoom(pointer).addPlayer(p);
    }
    public String moveBack(Player p) {
        Room r = map.getRoom(p.getCurrentRoom());
        if(r.getBack() != null) {
            if(moveRooms(p, r.getBack())) {
                return "Proceeding on.";
            }
            else {
                return "The door is locked...";
            }
        }
        return "No way through...";
    }
    public String moveForward(Player p) {
        Room r = map.getRoom(p.getCurrentRoom());
        if(r.getForward() != null) {
            if(moveRooms(p, r.getForward())) {
                return "Proceeding on.";
            }
            else {
                return "The door is locked...";
            }
        }
        return "No way through...";
    }
    public String moveLeft(Player p) {
        Room r = map.getRoom(p.getCurrentRoom());
        if(r.getLeft() != null) {
            if(moveRooms(p, r.getLeft())) {
                return "Proceeding on.";
            }
            else {
                return "The door is locked...";
            }
        }
        return "No way through...";
    }
    public String moveRight(Player p) {
        Room r = map.getRoom(p.getCurrentRoom());
        if(r.getRight() != null) {
            if(moveRooms(p, r.getRight())) {
                return "Proceeding on.";
            }
            else {
                return "The door is locked...";
            }
        }
        return "No way through...";
    }

/*
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
    */
}