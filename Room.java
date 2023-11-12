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
    public Room(String name, int rType) {
        this(name, null, null, null, null, rType);
    }
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
    public String getRoomName() {
        return roomName;
    }
    //Currently a placeholder
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
    public ArrayList<Player> getPlayers() {
        return players;
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
        if(item == null) {
            return false;
        }
        return true;
    }
    public boolean isLocked() {
        return locked;
    }
    //setter type methods
    public void setType() {
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
    public void addPlayer(Player x) {
        players.add(x);
    }
    public void removePlayer(Player x) {
        if(players.contains(x)) {
            players.remove(x);
        }
    }
    public void setItem(Item i) {
        item = i;
    }
    public void addEnemy(Enemy e) {
        enemies.add(e);
    }
    /**
     * Checks the enemies list for defeated enemies, then removes them from the list
     */
    public void removeEnemy() {
        for(Enemy e: enemies) {
            if(!e.isAlive()) {
                enemies.remove(e);
            }
        }
    }

    /**
     * placeholder method for unlocking a room
     * @param player x
     * @return true if success
     */
    public boolean unlock(Player player) {
        if(true/*placeholder: player has key*/) {
            locked = false;
            //remove key
            return true;//If player has key, set locked to false and remove key
        }
        return false;
    }
    //Functional methods
    /*public boolean moveRooms(Player player, Room destination) {//maybe put in Map class instead
        if(destination == null || !players.contains(player)) {
            return false;
        }
        if(destination.isLocked()) {
            if(!unlock(player)) {
                return false;
            }
        }
        players.remove(player);
        destination.addPlayer(player);
        //move player, set room player is in to destination
        return true;
    }*/
    /*public boolean adjacent(Room room) {
        if(back.equals(room)) {
            return true;
        }
        if(forward.equals(room)) {
            return true;
        }
        if(left.equals(room)) {
            return true;
        }
        if(right.equals(room)) {
            return true;
        }
        return false;
    }*/
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
