package edu.wctc;

public class DownSecretRoom extends Room implements Lootable {

    private boolean looted = false;

    public DownSecretRoom(String name) {
        super(name);
    }

//this room needs to be able to exit back up to the real exit room!!!
    public void setExitRoom(Room exitRoom) {
        setUp(exitRoom); // requires Room to have setUp(Room)
    }

    @Override
    public String getDescription() {
        if (!looted) {
            return "A cramped chamber hidden beneath the final door. A small pedestal holds a gleaming coin.\nWhat an Easter Egg!!! I hope you got all the points!";
        }
        return "The pedestal is empty. Dust settles on the floor.";
    }

    @Override
    public String loot(Player player) {
        if (looted) {
            return "You search the pedestal again. It's empty.";
        }
        looted = true;
        player.addToInventory("Gleaming Coin");
        player.addToScore(15);
        return "You take the Gleaming Coin! (+15 score)";
    }
}
