package edu.wctc;

public class SecretRoom extends Room implements Lootable, Interactable {

    private boolean looted = false;
    private ExitRoom exitRoom; // reference to the exit that will be unlocked

    public SecretRoom(String name) {
        super(name);
    }

    public void setExitRoom(ExitRoom exitRoom) {
        this.exitRoom = exitRoom;
    }

    @Override
    public String getDescription() {
        boolean unlocked = exitRoom != null && exitRoom.isUnlocked();
        if (!looted && !unlocked) {
            return "A narrow hidden alcove. A dusty chest sits in the corner. A recessed keyhole and a small button are set into the wall.";
        }
        if (!looted) {
            return "A narrow hidden alcove. A dusty chest sits in the corner. The button looks pressed.";
        }
        if (!unlocked) {
            return "The chest is open and empty. A recessed keyhole and a small button remain on the wall.";
        }
        return "The chest is open and empty. The mechanism has been triggered; you hear a faint click from afar.";
    }

    @Override
    public String loot(Player player) {
        if (looted) {
            return "You rummage through the chest again. It's empty.";
        }

        looted = true;
        player.addToInventory("Ancient Coin");
        player.addToScore(5);
        return "You open the chest and find an Ancient Coin! (+5 score)";
    }

    @Override
    public String interact(Player player) {
        if (exitRoom == null) {
            return "There is a button here, but it seems disconnected.";
        }
        if (exitRoom.isUnlocked()) {
            return "You press the button again. Nothing more happens.";
        }
        if (!player.hasItem("Rusty Key")) {
            return "There is a keyhole and a button, but you need a key to operate it.";
        }

        // consume the key, unlock the exit, reward the player
        player.removeFromInventory("Rusty Key");
        exitRoom.setUnlocked(true);
        player.addToScore(5);
        return "You insert the Rusty Key, turn it and press the hidden button. A distant mechanism unlocks the final door! (+5 score)";
    }
}
