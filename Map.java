import java.util.ArrayList;
import java.util.HashMap;
/**
 * Creates a HashMap containing Room objects with pointers representing the keys of other Rooms.
 * The resulting object acts as a traversable map for players to advance through.
 * @author Jonathan Murphy, Evelyn Totman, Salim Jday
 */
public class Map {
    //Attributes
    private HashMap<Integer, Room> rooms;
    private ArrayList<Integer> keys;
    //Methods
    /**
     * Constructor. Creates a default map of Room objects complete with pointers and types then calls setMap
     */
    public Map() {//build preset map

        /*rooms = new HashMap<>(27);//Room types: 0=start, 1=combat, 2=objective, 3=item, 4=heal, 5=boss, 6==end
        rooms.put(0, new Room("Start",null, 1, null, null, 0));
        rooms.put(1, new Room("Combat1", 0, null, 2, 3, 1));
        rooms.put(2, new Room("Combat2", 1, null, null, 4, 1));
        rooms.put(3, new Room("Objective1", 1, 5, null, null, 2));
        rooms.put(4, new Room("Item1", 2, null, null, null, 3));
        rooms.put(5, new Room("Heal1", 3, 8, 7, 6, 4));
        rooms.put(6, new Room("Combat3", 5, 9, null, null, 1));
        rooms.put(7, new Room("Combat4", 5, 10, null, null, 1));
        rooms.put(8, new Room("Combat5", 5, 11, null, null, 1));
        rooms.put(9, new Room("Item2", 6, null, null, null, 3));
        rooms.put(10, new Room("Heal2", 7, null, null, null, 4));
        rooms.put(11, new Room("Boss", 8, null, 12, null, 5));
        rooms.put(12, new Room("Exit", 11, null, null, null, 6));
         */

        rooms = new HashMap<>(27);
        keys = new ArrayList<>();
        Room roomBffr;
        roomBffr = new Room("Start", 0);
        keys.add(roomBffr.hashCode());
        rooms.put(keys.get(0), roomBffr);

        roomBffr = new Room("Combat 1", 1);
        keys.add(roomBffr.hashCode());
        rooms.put(keys.get(1), roomBffr);

        roomBffr = new Room("Combat 2", 1);
        keys.add(roomBffr.hashCode());
        rooms.put(keys.get(2), roomBffr);

        roomBffr = new Room("Objective 1", 2);
        keys.add(roomBffr.hashCode());
        rooms.put(keys.get(3), roomBffr);

        roomBffr = new Room("Item 1", 3);
        keys.add(roomBffr.hashCode());
        rooms.put(keys.get(4), roomBffr);

        roomBffr = new Room("Heal 1", 4);
        keys.add(roomBffr.hashCode());
        rooms.put(keys.get(5), roomBffr);

        roomBffr = new Room("Combat 3", 1);
        keys.add(roomBffr.hashCode());
        rooms.put(keys.get(6), roomBffr);

        roomBffr = new Room("Combat 4", 1);
        keys.add(roomBffr.hashCode());
        rooms.put(keys.get(7), roomBffr);

        roomBffr = new Room("Combat 5", 1);
        keys.add(roomBffr.hashCode());
        rooms.put(keys.get(8), roomBffr);

        roomBffr = new Room("Item 2", 3);
        keys.add(roomBffr.hashCode());
        rooms.put(keys.get(9), roomBffr);

        roomBffr = new Room("Heal 2", 4);
        keys.add(roomBffr.hashCode());
        rooms.put(keys.get(10), roomBffr);

        roomBffr = new Room("Combat 6", 1);
        keys.add(roomBffr.hashCode());
        rooms.put(keys.get(11), roomBffr);

        roomBffr = new Room("Fluffy's Pen", 5);
        keys.add(roomBffr.hashCode());
        rooms.put(keys.get(12), roomBffr);

        setMap();
    }
    /**
     * Initializes all the pointers for the room map
     */
    private void setMap(){
        Room bffr;
        //sets GameStart
        bffr = rooms.get(keys.get(0));
        bffr.setForward(keys.get(1));

        bffr = rooms.get(keys.get(1));
        bffr.setLeft(keys.get(2));
        bffr.setRight(keys.get(3));

        bffr = rooms.get(keys.get(2));
        bffr.setBack(keys.get(1));
        bffr.setRight(keys.get(4));

        bffr = rooms.get(keys.get(3));
        bffr.setBack(keys.get(1));
        bffr.setForward(keys.get(5));

        bffr = rooms.get(keys.get(4));
        bffr.setBack(keys.get(2));

        bffr = rooms.get(keys.get(5));
        bffr.setBack(keys.get(3));
        bffr.setForward(keys.get(8));
        bffr.setLeft(keys.get(7));
        bffr.setRight(keys.get(6));

        bffr = rooms.get(keys.get(6));
        bffr.setBack(keys.get(5));
        bffr.setForward(keys.get(9));

        bffr = rooms.get(keys.get(7));
        bffr.setBack(keys.get(5));
        bffr.setForward(keys.get(10));

        bffr = rooms.get(keys.get(8));
        bffr.setBack(keys.get(5));
        bffr.setForward(keys.get(11));

        bffr = rooms.get(keys.get(9));
        bffr.setBack(keys.get(6));

        bffr = rooms.get(keys.get(10));
        bffr.setBack(keys.get(7));

        bffr = rooms.get(keys.get(11));
        bffr.setBack(keys.get(8));
        bffr.setForward(keys.get(12));

        bffr = rooms.get(keys.get(12));
        bffr.setBack(keys.get(11));
    }

    /**
     * Basic getter.
     * @return attribute ArrayList<Integer> keys
     */
    public ArrayList<Integer> getKeys() {
        return keys;
    }
    /**
     * Returns a Room from the map corresponding to the Integer key.
     * @param i Integer representing Map key
     * @return Room corresponding to Map key
     */
    public Room getRoom(Integer i) {
        if(rooms.containsKey(i)) {
            return rooms.get(i);
        }
        return null;
    }
}
