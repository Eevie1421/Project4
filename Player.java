
import java.util.Hashtable;

/**
 * Player - keeps information on players in game as well as allow for
 * attack, ability, and item functions.
 * @author Evelyn Totman, Salim Jday, Jonathan Murphy
 */

public class Player implements Creature{
    //status 0 is default, 1 is dead. Room for more types later.
    private int status;
    private int maxHealth;
    private int health;
    private Hashtable<String, Item> backpack;
    // 1 = fighter, 2 = cleric, 3 = sorcerer;
    private int playerClass;
    private String name;
    private int playerAc;
    private int attackMod;
    private int initiativeMod;
    private boolean abilityUsed;

    /**
     * Constructor -
     * initializes non-class specific player attributes and calls set classStats.
     * @param name - name of player
     * @param playerClass - class of player
     */

    public Player(String name, int playerClass){
        status = 0;
        this.name = name;
        backpack = new Hashtable<>();
        this.playerClass = playerClass;
        abilityUsed = false;
        setClassStats();
        pickItem(new Item(1));
    }
    public Player(){
        this("John", 1);
    }

    //getter methods
    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getAc(){
        return playerAc;
    }

    public int checkHealth() {
        return health;
    }

    public int checkStatus() {
        return status;
    }

    public boolean isAbilityUsed() {
        return abilityUsed;
    }

    //setter methods
    public void setHealth(int health) {
        this.health = health;
    }

    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAbilityUsed(boolean abilityUsed) {
        this.abilityUsed = abilityUsed;
    }
    /**
     * setClassStats - initializes class specific player attributes
     * 1 = fighter
     * 2 = cleric
     * 3 = sorcerer
     */
    public void setClassStats(){
        if(playerClass == 1){
            maxHealth = 30;
            health = 30;
            playerAc = 15;
            attackMod = 3;
            initiativeMod = 0;
        }
        else if (playerClass == 2){
            maxHealth = 25;
            health = 25;
            playerAc = 16;
            attackMod = 2;
            initiativeMod = 3;
        }
        else if (playerClass ==3){
            maxHealth = 20;
            health = 20;
            playerAc = 12;
            attackMod = 3;
            initiativeMod = 1;
        }
    }

    /**
     * updates player status
     * @param status - status of player
     */
    public void updateStatus(int status){
        this.status = status;
    }

    /**
     * updates player health and updates status if they die.
     * @param damage - health to either heal player by or take away from player
     */
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

    /**
     * picks up item and puts it in back
     * @param item - the item to pick up
     */
    public void pickItem(Item item){
        backpack.put(item.getType(), item);
    }

    /**
     * uses the item corresponding to the given key and removes it from backpack if it is out of charges
     * @param key - key of the Item to use
     * @return - returns false if the player doesn't have the item and true if it is used
     */
    public boolean useItem(String key) {
        if(!backpack.containsKey(key)) {
            return false;
        }
        Item temp = backpack.get(key);
        if(temp.outOfCharges()){
            return false;
        }
        int val = temp.use();
        if(val == 1){
            backpack.remove(key);
        }
        if(key.equals("Health pot")){
            updateHealth(val);
        }
        return true;
    }

    /**
     * does the corresponding attack depending on class type
     * @param ac - the armor class of the target being attacked
     * @return int - the amount of damage dealt. 0 if attack misses.
     */
    @Override
    public int attack(int ac){
        int attackRoll = Dice.rollD20(1, attackMod);
        int damageRoll = 0;
        if(playerClass == 1 && attackRoll >= ac){
            damageRoll = sword();
        }
        else if(playerClass == 2 && attackRoll >= ac){
            damageRoll = mace();
        }
        else if(playerClass == 3 && attackRoll >= ac){
            damageRoll = firebolt();
        }
        return damageRoll;
    }
    // Functions for different weapon types.
    private int sword(){
        return Dice.rollD8(1, attackMod);
    }

    private int mace(){
        return Dice.rollD6(1, attackMod);
    }
    private int firebolt(){
        return Dice.rollD10(1, attackMod);
    }

    /**
     * uses the ability corresponding to the player class if a charge is available
     * @return the damage dealt or health healed by ability
     */
    public int useAbility(){
        int hp = 0;
        if(playerClass == 1 && !abilityUsed){
            abilityUsed = true;
        }
        else if(playerClass == 2 && !abilityUsed) {
            abilityUsed = true;
            hp = -1 * Dice.rollD8(2, 0);
        }
        else if(playerClass == 3 && !abilityUsed){
            hp = Dice.rollD6(4, 0);
        }
        return hp;
    }

    /**
     * rolls initiative for player
     * @return - initiative value
     */
    @Override
    public int rollInitiative() {
        return Dice.rollD20(1, initiativeMod);
    }


    @Override
    public int attackPlayer(int players) {
        return 0;
    }

    @Override
    public boolean equals(Object o) {
        if(super.equals(o) && getClass() == o.getClass()) {
            if(name.equals(((Player) o).getName()) && playerAc == ((Player) o).getAc() && health == ((Player) o).checkHealth()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean isALive() {
        return status != 1;
    }
}
