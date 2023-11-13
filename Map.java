import java.util.HashMap;

public class Map {
    //Attributes
    private HashMap<Integer, Room> rooms;
    //Methods
    public Map() {//build preset map
        rooms = new HashMap<>(13);//Room types: 0=start/end, 1=combat, 2=objective, 3=item, 4=heal, 5=boss
        rooms.put(0, new Room("Start",null, 1, null, null, 0));
        rooms.put(1, new Room("Combat1", 0, null, 2, 3, 1));
        rooms.put(2, new Room("Combat2", 1, null, null, 4, 1));
        rooms.put(3, new Room("Objective1", 1, 5, null, null, 2));
        rooms.put(4, new Room("Item1", 2, null, null, null, 3));
        rooms.put(5, new Room("Heal1", 3, 8, 7, 6, 4));
        rooms.put(6, new Room("Combat3", 5, 9, null, null, 1));
        rooms.put(7, new Room("Combat4", 5, 10, null, null, 1));
        rooms.put(8, new Room("Objective2", 5, 9, null, null, 2));
        rooms.put(9, new Room("Item2", 6, null, null, null, 3));
        rooms.put(10, new Room("Heal2", 7, null, null, null, 4));
        rooms.put(11, new Room("Combat5", 8, null, 12, null, 5));
        rooms.put(12, new Room("Exit", 11, null, null, null, 0));
    }
    public Room getRoom(Integer i) {
        if(rooms.containsKey(i)) {
            return rooms.get(i);
        }
        return null;
    }
}