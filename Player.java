import java.util.Hashtable;
import java.util.Scanner;

public class Player {
    private int status;
    private int maxHealth;
    private int health;
    private Hashtable<String, Item> backpack;
    private int playerClass;
    private String name;
    private int playerAc;
    private int attackMod;
    private Integer thisRoom;

    public Player(String name, int playerClass) {
        status = 0;
        maxHealth = 50;
        health = 50;
        this.name = name;
        backpack = new Hashtable<>();
        this.playerClass = playerClass;
        setClassStats();
        pickItem(new Item(1));
    }

    public void setClassStats() {
        if (playerClass == 1) {
            playerAc = 15;
            attackMod = 3;
        } else {
            playerAc = 12;
            attackMod = 1;
        }
    }

    public String getName() {
        return name;
    }

    public int getAc() {
        return playerAc;
    }

    public Integer getCurrentRoom() {
        return thisRoom;
    }

    public void pickItem(Item item) {
        backpack.put(item.getType(), item);
    }

    public boolean useItem(String key) {
        Item temp = backpack.get(key);
        if (temp != null && !temp.outOfCharges()) {
            int val = temp.use();
            if (temp.outOfCharges()) {
                backpack.remove(key);
            }
            if ("Health pot".equals(key)) {
                updateHealth(val);
            }
            return true;
        }
        return false;
    }

    public int attack1(int enemyAC) {
        int attackRoll = Dice.rollD20(1, attackMod);
        int damageRoll = 0;

        if (attackRoll >= enemyAC) {
            damageRoll = sword(); // Or mace() based on player class
        }

        return damageRoll;
    }

    private int sword() {
        return Dice.rollD10(1, attackMod);
    }

    public int checkHealth() {
        return health;
    }

    public void updateStatus(int status) {
        this.status = status;
    }

    public boolean isAlive() {
        return status == 0;
    }

    public void updateHealth(int damage) {
        health -= damage;
        if (health <= 0) {
            health = 0;
            updateStatus(1);
        } else if (health > maxHealth) {
            health = maxHealth;
        }
        if (status > 0 && health > 0) {
            status = 0;
        }
    }

    public void setCurrentRoom(Integer roomNum) {
        thisRoom = roomNum;
    }

    public void playTurn(Room currentRoom) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("It's your turn to act!");

        // Print information about the current room
        System.out.println("Current Room: " + currentRoom.getDescription());

        // Check if there is an enemy in the room
        if (currentRoom.getEnemy() != null) {
            // Handle combat
            System.out.println("An enemy is in the room! Do you want to attack? (yes/no)");
            String userInput = scanner.nextLine();

            if ("yes".equalsIgnoreCase(userInput)) {
                int damage = attack1(currentRoom.getEnemy().getEnemyAc());
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
            System.out.println("You proceed to the next room.");
            // Move to the next room (adjust this logic based on your actual implementation)
        } else {
            System.out.println("You decided not to proceed to the next room.");
            // End the game or take other actions based on your game design
        }

    }
    @Override
    public boolean equals(Object o) {
        if(super.equals(o) && getClass() == o.getClass()) {
            if(name.equals(((Player) o).getName()) && playerAc == ((Player) o).getAc() && thisRoom.equals(((Player) o).getCurrentRoom()) && health == ((Player) o).checkHealth()) {
                return true;
            }
        }
        return false;
    }
}
