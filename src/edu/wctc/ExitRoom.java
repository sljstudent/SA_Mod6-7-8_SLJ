package edu.wctc;

public class ExitRoom extends Room implements Exitable {

    public ExitRoom(String name) {
        super(name);
    }

    @Override
    public String getDescription() {
        return "A heavy door stands before you. Freedom is on the other side. Probably.";
    }

    @Override
    public String exit(Player player) {
        player.addToScore(20);
        return "You push the door open and escape! (+20 score)";
    }
}
