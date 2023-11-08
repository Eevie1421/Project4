import java.util.ArrayList;

public class Room {//maybe should be static class like Node?
    //Attributes
    private String roomName;
    private static int id = 0;
    private Room back, forward, left, right;//may need to be static as well?
    private ArrayList<Player> players;//Player in array if placed in bucket corresponding to room
    //private static ArrayList<Player> players;//maybe?
    private ArrayList<Enemy> enemies;
    //private static ArrayList<Enemy> enemies;//maybe part 2?
    private Item item;//may have none
    private boolean locked;
    //Methods
    public Room(String name, boolean lock) {
        this(name, null, null, null, null, lock);
    }
    /*public Room(String name, Room prev, boolean lock) {
        this(name, prev, null, null, null, lock);
    }*/
    public Room(String name, Room prev, Room front, Room sideL, Room sideR, boolean lock) {
        id += 1;
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
    public int getId() {
        return id;
    }
    public Room getBack(){
        return back;
    }
    public Room getForward(){
        return forward;
    }
    public Room getLeft(){
        return left;
    }
    public Room getRight(){
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
    public boolean isLocked() {
        return locked;
    }
    //setter type methods
    public void setPaths(Room prev, Room front, Room sideL, Room sideR) {
        back = prev;
        forward = front;
        left = sideL;
        right = sideR;
    }
    public void addPlayer(Player x) {
        players.add(x);
    }
    public void removePlayer(Player x) {
        if(players.contains(x)) {
            players.remove(x);
        }
    }
    public void addItem(Item i) {
        item = i;
    }
    public void addEnemies(Enemy eType, int number) {
        for(int i = 0; i < number; i++) {
            enemies.add(eType);
        }
    }
    public boolean unlock(Player player) {
        if(true/*placeholder: player has key*/) {
            locked = false;
            //remove key
            return true;//If player has key, set locked to false and remove key
        }
        return false;
    }
    //Functional methods
    public boolean moveRooms(Player player, Room destination) {
        if(destination == null || !players.contains(player) || !adjacent(destination)) {
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
    }
    public boolean adjacent(Room room) {
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
    }
    @Override
    public boolean equals(Object o) {
        if(super.equals(o) && getClass() == o.getClass()) {
            if(roomName.equals(((Room) o).getRoomName()) && id == ((Room) o).getId()) {
                return true;
            }
        }
        return false;
    }
}
