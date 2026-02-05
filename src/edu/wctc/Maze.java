package edu.wctc;

public class Maze {

    private Room currentRoom;
    private final Player player;
    private boolean isFinished = false;

    // When true the next loop should show the current room's description+exits
    private boolean showRoomDescription = true;

    public Maze() {
        player = new Player();

        // Create rooms (3-room mini crawl)
        PuzzleRoom puzzleRoom = new PuzzleRoom("Puzzle Room");
        Room treasureRoom = new TreasureRoom("Treasure Room");
        HiddenExitRoom exitRoom = new HiddenExitRoom("Exit Room");

        // Create secret room (hidden until lever is pulled)
        SecretRoom secretRoom = new SecretRoom("Secret Alcove");
        // Secret room connects back to the puzzle room only (no direct path to exit)
        secretRoom.setSouth(puzzleRoom);

        // Wire secret so it can unlock the exit (keeps reference for unlocking)
        secretRoom.setExitRoom(exitRoom);

        // Create the hidden down-room under the exit (not declared in exit's exits)
        DownSecretRoom downRoom = new DownSecretRoom("Subterranean Niche");
        exitRoom.setDown(downRoom);
        // Ensure the down-room has an 'up' back to the exit
        downRoom.setExitRoom(exitRoom);

        // Connect rooms: Puzzle -> Treasure -> Exit
        puzzleRoom.setEast(treasureRoom);
        treasureRoom.setWest(puzzleRoom);

        treasureRoom.setEast(exitRoom);
        exitRoom.setWest(treasureRoom);

        // Wire secret: pulling the lever in the puzzle room opens a north passage to the secret room
        puzzleRoom.setSecretRoom(secretRoom);

        // Starting room
        currentRoom = puzzleRoom;
    }

    // Called by UI to check if description should be shown
    public boolean shouldShowRoomDescription() {
        return showRoomDescription;
    }

    // Mark that the description has been shown
    public void setRoomDescriptionShown() {
        showRoomDescription = false;
    }

    public String exitCurrentRoom() {
        if (currentRoom instanceof Exitable) {
            String result = ((Exitable) currentRoom).exit(player);

            // If it's the actual ExitRoom and it's unlocked, you win.
            if (currentRoom instanceof ExitRoom && ((ExitRoom) currentRoom).isUnlocked()) {
                isFinished = true;
            } else if (!(currentRoom instanceof ExitRoom)) {
                // If it's some other Exitable room, treat as finish
                isFinished = true;
            }

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
        // entering a new room -> show description next loop
        showRoomDescription = true;
        return true;
    }

    public int getPlayerScore() {
        return player.getScore();
    }

    // Total possible points in this maze configuration:
    // Puzzle lever (+5) + Treasure chest (+10) + Secret chest (+5) + Secret unlock (+5)
    // + Exit escape (+20) + Hidden down-room coin (+15) = 60
    public int getTotalPossibleScore() {
        return 60;
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
