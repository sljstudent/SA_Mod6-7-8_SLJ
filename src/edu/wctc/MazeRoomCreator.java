package edu.wctc;

public class MazeRoomCreator extends RoomCreator {
    @Override
    protected Room createRoom(RoomType type) {
        return switch (type) {
            case PUZZLE -> new PuzzleRoom("Puzzle Room");
            case TREASURE -> new TreasureRoom("Treasure Room");
            case SECRET -> new SecretRoom("Secret Alcove");
            case EXIT -> new HiddenExitRoom("Exit Room");
            case HIDDEN_DOWN -> new DownSecretRoom("Subterranean Niche");
        };
    }
}
