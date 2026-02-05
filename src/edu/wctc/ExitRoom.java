package edu.wctc;

public class ExitRoom extends Room implements Exitable {

    private boolean unlocked = false;

    public ExitRoom(String name) {
        super(name);
    }

    public void setUnlocked(boolean unlocked) {
        this.unlocked = unlocked;
    }

    public boolean isUnlocked() {
        return unlocked;
    }

    @Override
    public String getDescription() {
        if (!unlocked) {
            return "A heavy door stands before you. It appears to be locked.";
        }
        return "A heavy door stands before you. Freedom is on the other side. Probably.";
    }

    @Override
    public String exit(Player player) {
        if (!unlocked) {
            return "The door is locked. You can't leave yet.";
        }
        player.addToScore(20);
        return "You push the door open and escape! (+20 score)";
    }
}
