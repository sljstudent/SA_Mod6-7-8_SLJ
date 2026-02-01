package edu.wctc;

public class Maze {

    private Room currentRoom;
    private final Player player;
    private boolean isFinished = false;

    public Maze() {
        player = new Player();

        // Create rooms (3-room mini crawl)
        Room puzzleRoom = new PuzzleRoom("Puzzle Room");
        Room treasureRoom = new TreasureRoom("Treasure Room");
        Room exitRoom = new ExitRoom("Exit Room");

        // Connect rooms: Puzzle -> Treasure -> Exit
        puzzleRoom.setEast(treasureRoom);
        treasureRoom.setWest(puzzleRoom);

        treasureRoom.setEast(exitRoom);
        exitRoom.setWest(treasureRoom);

        // Starting room
        currentRoom = puzzleRoom;
    }

    public String exitCurrentRoom() {
        if (currentRoom instanceof Exitable) {
            String result = ((Exitable) currentRoom).exit(player);
            isFinished = true;
            return result;
        }
        return "This room is not exitable.";
    }

    public String interactWithCurrentRoom() {
        if (currentRoom instanceof Interactable) {
            return ((Interactable) currentRoom).interact(player);
        }
        return "No interactions are possible here.";
    }

    public String lootCurrentRoom() {
        if (currentRoom instanceof Lootable) {
            return ((Lootable) currentRoom).loot(player);
        }
        return "This room is not lootable.";
    }

    public boolean move(char direction) {
        if (!currentRoom.isValidDirection(direction)) {
            return false;
        }
        currentRoom = currentRoom.getAdjoiningRoom(direction);
        return true;
    }

    public int getPlayerScore() {
        return player.getScore();
    }

    public String getPlayerInventory() {
        return player.getInventory();
    }

    public String getCurrentRoomDescription() {
        return currentRoom.getDescription();
    }

    public String getCurrentRoomExits() {
        return currentRoom.getExits();
    }

    public boolean isFinished() {
        return isFinished;
    }
}
