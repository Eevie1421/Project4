import java.util.ArrayList;
/**
 * Creates a Room object which players occupy and move between to progress through the game.
 * Has a type value that tells the game how to construct them or act when players are present.
 * type 0 = start, type 1 = combat, type 2 = objective, type 3 = item, type 4 = heal, type 5 = boss, type 6 = end.
 * May be locked, or contain enemies or necessary items.
 * @author Jonathan Murphy, Evelyn Totman, Salim Jday
 */
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
     * Main Constructor, sets room name, pointers to other rooms, initializes Enemy list, and sets room type then
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
        enemies = new ArrayList<>();
        type = rType;
        cleared = false;
        setType();
    }
    /**
     * Secondary Constructor. Passes name, tType, and null values for room pointers to the main Constructor.
     * @param name String set to roomName
     * @param rType int set to type
     */
    public Room(String name, int rType){
        this(name, null, null, null, null, rType);
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
    /**
     * Basic getter.
     * @return attribute boolean cleared
     */
    public boolean isCleared() {
        return cleared;
    }
    /**
     * Basic getter.
     * @return attribute boolean intro
     */
    public String getIntro() {
        return intro;
    }
    /**
     * Basic getter.
     * @return attribute String clearedText
     */
    public String getClearedText() {
        return clearedText;
    }
    /**
     * Returns clearedText if attribute cleared is true, or intro if it is false.
     * @return clearedText if cleared is true, else intro
     */
    public String getText(){
        if(isCleared()){
            return clearedText;
        }
        else {
            return intro;
        }
    }
    //setter type methods
    /**
     * Continuation of constructor. Sets item, locked, intro and clearedText, and adds enemies depending on the value of type.
     * Room types: 0=start, 1=combat, 2=objective, 3=item, 4=heal, 5=boss, 6=end
     */
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
    /**
     * Sets the back pointer to a new Integer value.
     * @param b Integer set to back pointer
     */
    public void setBack(Integer b) {
        this.back = b;
    }
    /**
     * Sets the forward pointer to a new Integer value.
     * @param f Integer set to forward pointer
     */
    public void setForward(Integer f){
        this.forward = f;
    }
    /**
     * Sets the left pointer to a new Integer value.
     * @param l Integer set to left pointer
     */
    public void setLeft(Integer l) {
        this.left = l;
    }
    /**
     * Sets the right pointer to a new Integer value.
     * @param r Integer set to right pointer
     */
    public void setRight(Integer r) {
        this.right = r;
    }
    /**
     * Sets item to Item i.
     * @param i Item set to item
     */
    public void setItem(Item i) {
        item = i;
    }
    /**
     * Sets the cleared attribute to a new boolean value
     * @param c boolean set to cleared
     */
    public void setCleared(boolean c) {
        this.cleared = c;
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
    /**
     * Returns a HashCode value based on the HashCode of its attributes
     * @return int HashCode identifier
     */
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
