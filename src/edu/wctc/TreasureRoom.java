package edu.wctc;

public class TreasureRoom extends Room implements Lootable {

    private boolean looted = false;

    public TreasureRoom(String name) {
        super(name);
    }

    @Override
    public String getDescription() {
        return "A closet-sized room with a dusty chest in the corner. It’s practically begging.";
    }

    @Override
    public String loot(Player player) {
        if (looted) {
            return "You open the chest again. It's empty. So is your luck.";
        }

        looted = true;
        player.addToInventory("Rusty Key");
        player.addToScore(10);

        return "You loot the chest and find a Rusty Key! (+10 score)";
    }
}
