public class Room {
    private String description;
    private Enemy enemy;
    private Item item;
    private Room nextRoom;

    public Room(String description, Enemy enemy, Item item, Room nextRoom) {
        this.description = description;
        this.enemy = enemy;
        this.item = item;
        this.nextRoom = nextRoom;
    }

    public String getDescription() {
        return description;
    }

    public Enemy getEnemy() {
        return enemy;
    }

    public Item getItem() {
        return item;
    }

    public Room getNextRoom() {
        return nextRoom;
    }

    // Setter method to set the next room
    public void setNextRoom(Room nextRoom) {
        this.nextRoom = nextRoom;
    }
}