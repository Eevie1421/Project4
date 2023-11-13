import java.util.ArrayList;

public class Room {
    //Attributes
    private String roomName;
    private Integer back, forward, left, right;
    private ArrayList<Player> players;//Player in array if placed in bucket corresponding to room
    private ArrayList<Enemy> enemies;
    private int type;
    private Item item;
    private boolean locked;
    //Methods
    /**
     * Constructor, sets room name, pointers to other rooms, initializes player and Enemy lists, and sets room type then
     * calls setType() to finish construction.
     * @param name String set to roomName
     * @param prev Integer set to back pointer
     * @param front Integer set to forward pointer
     * @param sideL Integer set to left pointer
     * @param sideR Integer set to right pointer
     * @param rType int set to type
     */
    public Room(String name, Integer prev, Integer front, Integer sideL, Integer sideR, int rType) {
        roomName = name;
        back = prev;
        forward = front;
        left = sideL;
        right = sideR;
        players = new ArrayList<>();
        enemies = new ArrayList<>();
        type = rType;
        setType();
    }
    //getter methods
    /**
     * Basic getter.
     * @return attribute String roomName
     */
    public String getRoomName() {
        return roomName;
    }
    /**
     * Basic getter.
     * @return attribute Integer back
     */
    public Integer getBack(){
        return back;
    }
    /**
     * Basic getter.
     * @return attribute Integer forward
     */
    public Integer getForward(){
        return forward;
    }
    /**
     * Basic getter.
     * @return attribute Integer left
     */
    public Integer getLeft(){
        return left;
    }
    /**
     * Basic getter.
     * @return attribute Integer right
     */
    public Integer getRight(){
        return right;
    }
    /**
     * Basic getter.
     * @return attribute ArrayList<String> players
     */
    public ArrayList<Player> getPlayers() {
        return players;
    }
    /**
     * Basic getter.
     * @return attribute ArrayList<String> enemies
     */
    public ArrayList<Enemy> getEnemies() {
        return enemies;
    }
    /**
     * Basic getter.
     * @return attribute int type
     */
    public int getType() {
        return type;
    }
    /**
     * Basic getter.
     * @return attribute Item item
     */
    public Item getItem() {
        return item;
    }
    /**
     * Checks if item is null or not.
     * @return false if item is null
     */
    public boolean hasItem() {
        if(item == null) {
            return false;
        }
        return true;
    }
    /**
     * Basic getter.
     * @return attribute boolean locked
     */
    public boolean isLocked() {
        return locked;
    }
    //setter type methods
    /**
     * Continuation of constructor. Sets item, locked, and adds enemies depending on the value of type.
     * Room types: 0=start, 1=combat, 2=objective, 3=item, 4=heal, 5=boss, 6=end
     */
    private void setType() {
        if(type == 1) {//1=combat
            item = null;
            locked = false;
            enemies.add(new Zombie());
            enemies.add(new Zombie());
            enemies.add(new Zombie());
        }
        else if(type == 2) {//2=objective
            item = null;
            locked = true;
        }
        else if(type == 3) {//3=item
            item = new Item(0);
            locked = false;
            //something for item pickups
        }
        else if(type == 4) {//4=sanctuary
            item = null;
            locked = false;
            //something for healing
        }
        else if(type == 5) {//5=boss
            item = null;
            locked = false;
            enemies.add(new Boss());
        }
        else {//0=start, //6=end
            item = null;
            locked = false;
        }
    }
    /**
     * Adds a Player to players list.
     * @param p Player added to players
     */
    public void addPlayer(Player p) {
        players.add(p);
    }
    /**
     * Removes Player p if it exists in players list.
     * @param p Player to be removed
     */
    public void removePlayer(Player p) {
        if(players.contains(p)) {
            players.remove(p);
        }
    }
    /**
     * Sets item to Item i.
     * @param i Item set to item
     */
    public void setItem(Item i) {
        item = i;
    }
    /**
     * Adds an Enemy to enemies list.
     * @param e Enemy added to enemies
     */
    public void addEnemy(Enemy e) {
        enemies.add(e);
    }
    //Functional methods
    /**
     * Checks the enemies list for defeated enemies, then removes them from the list.
     */
    public void removeEnemy() {
        for(Enemy e: enemies) {
            if(!e.isAlive()) {
                enemies.remove(e);
            }
        }
    }
    /**
     * If a player has a key, set locked to false and return true, else return false.
     * @param p Player trying to unlock
     * @return true if successful
     */
    public boolean unlock(Player p) {
        if(p.useItem("key")) {
            locked = false;
            return true;//If player has key, set locked to false and remove key
        }
        return false;
    }
    /**
     * Compares this Object to another.
     * @param o Object to be compared
     * @return true if Object o matches this Object
     */
    @Override
    public boolean equals(Object o) {
        if(super.equals(o) && getClass() == o.getClass()) {
            if(roomName.equals(((Room) o).getRoomName()) && back.equals(((Room) o).getBack()) && forward.equals(((Room) o).getForward())
                    && left.equals(((Room) o).getLeft()) && right.equals(((Room) o).getRight())) {
                return true;
            }
        }
        return false;
    }
}
