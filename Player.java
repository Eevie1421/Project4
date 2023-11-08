
import java.util.Hashtable;

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
    private Hashtable<String, Item> backpack;
    private int playerClass;
    private String name;
    private int playerAc;

    public Player(String name, int playerClass){
        status = 0;
        maxHealth = 50;
        health = 50;
        this.name = name;
        backpack = new Hashtable<>();
        this.playerClass = playerClass;
        setAc();
        pickItem(new Item(1));
    }

    public Player(){
        this("John", 1);
    }

    public String getName() {
        return name;
    }

    public int getAc(){
        return playerAc;
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
    public void setAc(){
        if(playerClass == 1){
            playerAc = 15;
        }
        else{
            playerAc = 12;
        }
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
        if(status > 0 && health > 0){
            status = 0;
        }
    }
    public void pickItem(Item item){
        backpack.put(item.getType(), item);
    }

    public boolean useItem(String key){
        Item temp = backpack.get(key);
        if(temp.outOfCharges()){
            return false;
        }
        int val = temp.use();
        if(temp.outOfCharges()){
            backpack.remove(key);
        }
        if(key.equals("Health pot")){
            updateHealth(val);
        }
        return true;
    }

    public int attack1(int ac){
        int attackRoll = (int)(1 + Math.random() * 19);
        int damageRoll = 0;
        if(playerClass == 1 && attackRoll >= ac){
            damageRoll = sword();
        }
        else if(playerClass == 2 && attackRoll >= ac){
            damageRoll = mace();
        }
        return damageRoll;
    }
    private int sword(){
        return (int)(1 + Math.random() * 9);
    }

    private int mace(){
        return (int)(1 + Math.random() * 5);
    }
}
