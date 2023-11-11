import java.util.ArrayList;

public class Room {
    //Attributes
    private String roomName;
    private Integer back, forward, left, right;
    private ArrayList<Player> players;//Player in array if placed in bucket corresponding to room
    private ArrayList<Enemy> enemies;
    private Item item;
    private boolean locked;
    //Methods
    public Room(String name, boolean lock) {
        this(name, null, null, null, null, lock);
    }
    public Room(String name, Integer prev, Integer front, Integer sideL, Integer sideR, boolean lock) {
        roomName = name;
        back = prev;
        forward = front;
        left = sideL;
        right = sideR;
        players = new ArrayList<>();
        enemies = new ArrayList<>();
        item = null;
        locked = lock;
    }
    //getter methods
    public String getRoomName() {
        return roomName;
    }
    //Currently a placeholder
    public String getDescription() {
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
    public ArrayList<Player> getPlayers() {
        return players;
    }
    public ArrayList<Enemy> getEnemies() {
        return enemies;
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
    //Currently a placeholder
    public Enemy getEnemy() {
        if(enemies.get(0) != null) {
            return enemies.get(0);
        }
        return null;
    }
    //setter type methods
    /*public void setPaths(Integer prev, Integer front, Integer sideL, Integer sideR) {
        back = prev;
        forward = front;
        left = sideL;
        right = sideR;
    }*/
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
