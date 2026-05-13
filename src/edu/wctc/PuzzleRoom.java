// java
package edu.wctc;

public class PuzzleRoom extends Room implements Interactable {

    private boolean solved = false;
    private Room secretRoom; // will be opened when lever is pulled

    public PuzzleRoom(String name) {
        super(name);
    }

    public void setSecretRoom(Room secretRoom) {
        this.secretRoom = secretRoom;
    }

    @Override
    public String getDescription() {
        if (!solved) {
            return "You are in a cramped stone room. A rusty lever sticks out of the wall.";
        }
        if (secretRoom != null) {
            return "The lever has been pulled. A hidden door to the north stands open.";
        }
        return "The lever has been pulled. Something feels... different now.";
    }

    @Override
    public String interact(Player player) {
        if (solved) {
            return "You pull the lever again. Nothing happens.";
        }
        solved = true;
        player.addToScore(GameConfig.getInstance().getPuzzleSolvedPoints());
        if (secretRoom != null) {
            // open the secret passage
            setNorth(secretRoom);
            return "You pull the lever. A loud *CLUNK* echoes through the dungeon. A hidden door opens to the north! (+5 score)";
        }
        return "You pull the lever. A loud *CLUNK* echoes through the dungeon. (+5 score)";
    }

}
