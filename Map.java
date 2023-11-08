import java.util.HashMap;

public class Map {
    //Attributes
    private HashMap<Integer, Room> rooms;
    //Methods
    public Map() {//build preset map
        rooms = new HashMap<>(13);
        rooms.put(0, new Room("Start",null, 1, null, null, false));
        rooms.put(1, new Room("Combat1", 0, null, 2, 3, false));
        rooms.put(2, new Room("Combat2", 1, null, null, 4, false));
        rooms.put(3, new Room("Objective1", 1, 5, null, null, true));
        rooms.put(4, new Room("Item1", 2, null, null, null, false));
        rooms.put(5, new Room("Heal1", 3, 8, 7, 6, false));
        rooms.put(6, new Room("Combat3", 5, 9, null, null, false));
        rooms.put(7, new Room("Combat4", 5, 10, null, null, false));
        rooms.put(8, new Room("Objective2", 5, 9, null, null, true));
        rooms.put(9, new Room("Item2", 6, null, null, null, false));
        rooms.put(10, new Room("Heal2", 7, null, null, null, false));
        rooms.put(11, new Room("Combat5", 8, null, 12, null, false));
        rooms.put(12, new Room("Exit", 11, null, null, null, false));
        /*rooms.get(0).setPaths(null, 1, null, null);
        rooms.get(1).setPaths(0, null, 2, 3);
        rooms.get(2).setPaths(1, null, null, 4);
        rooms.get(3).setPaths(1, 5, null, null);
        rooms.get(4).setPaths(2, null, null, null);
        rooms.get(5).setPaths(3, 8, 7, 6);
        rooms.get(6).setPaths(5, 9, null, null);
        rooms.get(7).setPaths(5, 10, null, null);
        rooms.get(8).setPaths(5, 11, null, null);
        rooms.get(9).setPaths(6, null, null, null);
        rooms.get(10).setPaths(7, null, null, null);
        rooms.get(11).setPaths(8, null, 12, null);
        rooms.get(12).setPaths(11, null, null, null);*/
    }
    public Room getRoom(Integer i) {
        if(rooms.containsKey(i)) {
            return rooms.get(i);
        }
        return null;
    }
}
