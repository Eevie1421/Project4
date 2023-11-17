import java.util.ArrayList;

public class Room {
    //Attributes
    private String roomName;
    private Integer back, forward, left, right;
    private ArrayList<Enemy> enemies;
    private int type;
    private Item item;
    private boolean cleared;
    private boolean locked;
    private String intro;
    private String clearedText;
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
        cleared = false;
        setType();
    }

    public Room(String name, int rType){
        this(name, null, null, null, null, rType);
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

    public void setBack(Integer back) {
        this.back = back;
    }

    public void setForward(Integer forward) {
        this.forward = forward;
    }

    public void setLeft(Integer left) {
        this.left = left;
    }

    public void setRight(Integer right) {
        this.right = right;
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

    public boolean isCleared() {
        return cleared;
    }

    public void setCleared(boolean cleared) {
        this.cleared = cleared;
    }

    public String getIntro() {
        return intro;
    }

    public String getClearedText() {
        return clearedText;
    }

    //setter type methods
    private void setType() {
        if(type == 1) {
            item = null;
            locked = false;
            enemies.add(new Zombie());
            enemies.add(new Zombie());
            enemies.add(new Zombie());
            intro = "Upon entering the room you see three zombies in front of you. roll initiative";
            clearedText = "The bodies of the zombies still lay upon the ground. You silently pray it stays that way";
        }
        else if(type == 2) {
            item = null;
            locked = true;
            intro = "You feel a cool breeze sweep through chilling you to your bones";
            clearedText = "You feel a cool breeze sweep through chilling you to your bones";
        }
        else if(type == 3) {
            item = new Item(0);
            locked = false;
            intro = "You see something glinting off the torchlight in the middle of room.\n It looks like you can pick it up.";
            clearedText = "You see nothing but rocks and dirt";
            //something for item pickups
        }
        else if(type == 4) {
            item = null;
            locked = false;
            intro = "You enter into a room with a campfire in the center.\n" + "You feel compelled to take a moment to rest and heal by the fire.\n";
            clearedText = "The warmth has faded from the fire you feel it will not be safe to linger";
            //something for healing
        }
        else if(type == 5) {
            item = null;
            locked = true;
            enemies.add(new Boss());
            intro = "As you enter a large open room you hear load snoring echoing out from deeper within. " +
                    "As you get closer you can see a massive three headed dog sleeping with its head on its paws." +
                    "Suddenly as you approach it wakes up and lets out a massive roar. roll initiative";
            clearedText = "As the dust settles the large creature can be seen slain in the middle of the room";
        }
        else {
            item = null;
            locked = false;
            intro = "";
            clearedText = "";
        }
    }
    public void setItem(Item i) {
        item = i;
    }
    public void addEnemy(Enemy e) {
        enemies.add(e);
    }

    /**
     *
     * @return
     */
    public String getText(){
        if(isCleared()){
            return clearedText;
        }
        else {
            return intro;
        }
    }
    /**
     * If a player has a key, set locked to false and return true, else return false.
     * @param player Player trying to unlock
     * @return true if successful
     */
    public boolean unlock(Player player) {
        if(player.useItem("Key")) {
            locked = false;
            return true;//If player has key, set locked to false and remove key
        }
        else{
            return false;
        }
    }
    @Override
    public boolean equals(Object o) {
        if(o == null) return false;
        if(super.equals(o) && getClass() == o.getClass()) {
            Room temp = (Room) o;
            return roomName.equals(temp.getRoomName()) && type == temp.getType() && cleared == temp.cleared && locked == temp.locked && intro.equals(temp.getIntro()) && clearedText.equals(temp.clearedText);
        }
        return false;
    }

    @Override
    public int hashCode() {
        int result = 7;
        result = result + roomName.hashCode() + intro.hashCode() + clearedText.hashCode() + type;
        if(cleared){
            result = result + 1;
        }
        if(locked){
            result = result + 1;
        }
        return 37 * result;
    }
}