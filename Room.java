import java.util.ArrayList;

public class Room {
    //Attributes
    private String roomName;
    private Integer back, forward, left, right;
    private ArrayList<Enemy> enemies;
    private int type;
    private Item item;
    private boolean locked;
    //Methods
    /**
     * Constructor, sets room name, pointers to other rooms, initializes player and Enemy lists, and sets room type then
     * calls setType() to finish construction.
     * @param name
     * @param prev
     * @param front
     * @param sideL
     * @param sideR
     * @param rType
     */
    public Room(String name, Integer prev, Integer front, Integer sideL, Integer sideR, int rType) {
        roomName = name;
        back = prev;
        forward = front;
        left = sideL;
        right = sideR;
        enemies = new ArrayList<>();
        type = rType;
        setType();
    }
    //getter methods
    public String getRoomName() {
        return roomName;
    }
    public Integer getBack(){
        return back;
    }
    public Integer getForward(){
        return forward;
    }
    public Integer getLeft(){
        return left;
    }
    public Integer getRight(){
        return right;
    }
    public ArrayList<Enemy> getEnemies() {
        return enemies;
    }
    public int getType() {
        return type;
    }
    public Item getItem() {
        return item;
    }
    public boolean hasItem() {
        return item != null;
    }
    public boolean isLocked() {
        return locked;
    }
    //setter type methods
    private void setType() {
        if(type == 1) {
            item = null;
            locked = false;
            enemies.add(new Zombie());
            enemies.add(new Zombie());
            enemies.add(new Zombie());
        }
        else if(type == 2) {
            item = null;
            locked = true;
        }
        else if(type == 3) {
            item = new Item(0);
            locked = false;
            //something for item pickups
        }
        else if(type == 4) {
            item = null;
            locked = false;
            //something for healing
        }
        else if(type == 5) {
            item = null;
            locked = false;
            enemies.add(new Boss());
        }
        else {
            item = null;
            locked = false;
        }
    }
    public void setItem(Item i) {
        item = i;
    }
    public void addEnemy(Enemy e) {
        enemies.add(e);
    }
    /**
     * If a player has a key, set locked to false and return true, else return false.
     * @param player Player trying to unlock
     * @return true if successful
     */
    public boolean unlock(Player player) {
        if(player.useItem("key")) {
            locked = false;
            return true;//If player has key, set locked to false and remove key
        }
        return false;
    }
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