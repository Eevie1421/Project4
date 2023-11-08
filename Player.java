import java.util.HashSet;

/* Player v 0.1
 * Player's job is to keep track of health and status.
 * Player will also have different attack/ability options in future versions
 * Player will have and allow interaction with items.
 */
public class Player {
    //status 0 is default, 1 is unconscious, 2 is dead. Room for more types later.
    private int status;
    private int maxHealth;
    private int health;
    private HashSet<Item> backpack;
    private String name;

    public Player(String name){
        status = 0;
        maxHealth = 50;
        health = 50;
        this.name = name;
    }

    public Player(){
        this("John");
    }

    public String getName() {
        return name;
    }

    public int checkHealth() {
        return health;
    }

    public int checkStatus() {
        return status;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void updateStatus(int status){
        this.status = status;
    }

    public void updateHealth(int damage){
        health = health - damage;
        if(health <= 0){
            health = 0;
            updateStatus(1);
        }
        else if(health > maxHealth){
            health = maxHealth;
        }
    }
    public void pickItem(Item item){

    }
}
