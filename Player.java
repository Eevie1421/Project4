import java.util.Hashtable;
import java.util.Scanner;

public class Player implements Creature{
    //status 0 is default, 1 is dead. Room for more types later.
    private int status;
    private int maxHealth;
    private int health;
    private Hashtable<String, Item> backpack;
    // 1 == fighter, 2 == cleric;
    private int playerClass;
    private String name;
    private int playerAc;
    private int attackMod;
    private int initiativeMod;
    private Integer thisRoom;

    public Player(String name, int playerClass){
        status = 0;
        maxHealth = 50;
        health = 50;
        this.name = name;
        backpack = new Hashtable<>();
        this.playerClass = playerClass;
        setClassStats();
        pickItem(new Item(1));
    }
    public Player(){
        this("John", 1);
    }

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

    public Integer getCurrentRoom() {
        return thisRoom;
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
    public void setClassStats(){
        if(playerClass == 1){
            playerAc = 15;
            attackMod = 3;
            initiativeMod = 0;
        }
        else{
            playerAc = 12;
            attackMod = 2;
            initiativeMod = 3;
        }
    }

    public void setCurrentRoom(Integer roomNum) {
        thisRoom = roomNum;
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
        backpack.put(item.getType(), item);
    }

    public boolean useItem(String key) {
        if(!backpack.contains(key)) {
            return false;
        }
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

    public int attack(int ac){
        int attackRoll = Dice.rollD20(1, attackMod);
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
        return Dice.rollD10(1, attackMod);
    }

    private int mace(){
        return Dice.rollD6(1, attackMod);
    }

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
            if(name.equals(((Player) o).getName()) && playerAc == ((Player) o).getAc() && thisRoom.equals(((Player) o).getCurrentRoom()) && health == ((Player) o).checkHealth()) {
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