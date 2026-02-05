// java
package edu.wctc;

public class TreasureRoom extends Room implements Lootable {

    private boolean looted = false;

    public TreasureRoom(String name) {
        super(name);
    }

    @Override
    public String getDescription() {
        if (!looted) {
            return "A larger room with a dusty chest that sits in the corner. What could it be?";
        }
        return "Back to the largeer room with an open, empty chest. Dust swirls where something used to be.";
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
