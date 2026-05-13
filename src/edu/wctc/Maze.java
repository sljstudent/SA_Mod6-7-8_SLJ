package edu.wctc;

public class Maze {

    private Room currentRoom;
    private final Player player;
    private final GameConfig config;
    private final ScoreAuditLog scoreAuditLog = new ScoreAuditLog();
    private boolean isFinished = false;

    // When true the next loop should show the current room's description+exits
    private boolean showRoomDescription = true;

    public Maze() {
        this(new Player(), new MazeRoomCreator(), GameConfig.getInstance());
    }

    public Maze(Player player, RoomCreator roomCreator, GameConfig config) {
        this.player = player;
        this.config = config;
        this.player.addScoreObserver(scoreAuditLog);

        //use the enums from RoomType
        PuzzleRoom puzzleRoom = (PuzzleRoom) roomCreator.create(RoomType.PUZZLE);
        Room treasureRoom = roomCreator.create(RoomType.TREASURE);
        HiddenExitRoom exitRoom = (HiddenExitRoom) roomCreator.create(RoomType.EXIT);
        SecretRoom secretRoom = (SecretRoom) roomCreator.create(RoomType.SECRET);
        DownSecretRoom downRoom = (DownSecretRoom) roomCreator.create(RoomType.HIDDEN_DOWN);

        secretRoom.setSouth(puzzleRoom);
        secretRoom.setExitRoom(exitRoom);

        exitRoom.setDown(downRoom);
        downRoom.setExitRoom(exitRoom);

        puzzleRoom.setEast(treasureRoom);
        treasureRoom.setWest(puzzleRoom);

        treasureRoom.setEast(exitRoom);
        exitRoom.setWest(treasureRoom);

        puzzleRoom.setSecretRoom(secretRoom);

        currentRoom = puzzleRoom;
    }

    // show discription
    public boolean shouldShowRoomDescription() {
        return showRoomDescription;
    }

    // has the description been shown?
    public void setRoomDescriptionShown() {
        showRoomDescription = false;
    }

    public String exitCurrentRoom() {
        if (currentRoom instanceof Exitable) {
            String result = ((Exitable) currentRoom).exit(player);

            // how do we exit? if Exitroom and if its unlocked!
            if (currentRoom instanceof ExitRoom && ((ExitRoom) currentRoom).isUnlocked()) {
                isFinished = true;
            } else if (!(currentRoom instanceof ExitRoom)) {
                // If it's some other Exitable room... that I don't have yet... ....
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
        return config.getTotalPossibleScore();
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

    public void quit() {
        isFinished = true;
    }

    public String getCurrentRoomName() {
        return currentRoom.getName();
    }

    public ScoreAuditLog getScoreAuditLog() {
        return scoreAuditLog;
    }
}
