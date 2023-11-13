public class GameLogic {
    private Player player;
    private Room currentRoom;
    private Map map;

    public GameLogic(Player aPlayer, Room startingRoom, Map gameMap) {
        this.player = aPlayer;
        this.currentRoom = startingRoom;
        this.map = new Map();
        player.setCurrentRoom(0);
    }

    public String moveBack() {
        Room currentRoom = map.getRoom(player.getCurrentRoom());
        if (currentRoom.getBack() != null) {
            player.move(0);
            return "Proceeding backward.";
        } else {
            return "No way to move backward.";
        }
    }

    public String moveForward() {
        Room currentRoom = map.getRoom(player.getCurrentRoom());
        if (currentRoom.getForward() != null) {
            player.move(1);
            return "Proceeding forward.";
        } else {
            return "No way to move forward.";
        }
    }

    public String moveLeft() {
        Room currentRoom = map.getRoom(player.getCurrentRoom());
        if (currentRoom.getLeft() != null) {
            player.move(2);
            return "Moving left.";
        } else {
            return "No way to move left.";
        }
    }

    public String moveRight() {
        Room currentRoom = map.getRoom(player.getCurrentRoom());
        if (currentRoom.getRight() != null) {
            player.move(3);
            return "Moving right.";
        } else {
            return "No way to move right.";
        }
    }
}