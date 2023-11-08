import java.util.ArrayList;

public class Room {//maybe should be static class like Node?
    //Attributes
    private Room back, forward,left, right;//may need to be static as well?
    //private ArrayList<Player> players;//Player in array if placed in bucket corresponding to room
    private static ArrayList<Player> players;//maybe?
    //private ArrayList<Enemy> enemies;
    private static ArrayList<Enemy> enemies;//maybe part 2?
    private Item item;//may have none
    private boolean locked;
    //Methods
    public Room(boolean isLocked) {
        this(null, null, null, null, isLocked);
    }
    public Room(Room prev, boolean isLocked) {
        this(prev, null, null, null, isLocked);
    }
    public Room(Room prev, Room front, Room sideL, Room sideR, boolean isLocked) {
        back = prev;
        forward = front;
        left = sideL;
        right = sideR;
        players = new ArrayList<>();
        enemies = new ArrayList<>();
        item = null;
        locked = isLocked;
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
    public void setPaths(Room prev, Room front, Room sideL, Room sideR) {
        back = prev;
        forward = front;
        left = sideL;
        right = sideR;
    }
    public void addItem(Item i) {
        item = i;
    }
    public void addEnemies(Enemy eType, int number) {
        for(int i = 0; i < number; i++) {
            enemies.add(eType);
        }
    }
}
