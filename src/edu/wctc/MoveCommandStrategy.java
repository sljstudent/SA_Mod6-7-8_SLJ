package edu.wctc;

public class MoveCommandStrategy implements CommandStrategy {
    private final char direction;

    public MoveCommandStrategy(char direction) {
        this.direction = direction;
    }

    @Override
    public String execute(Maze maze) {
        if (maze.move(direction)) {
            return "";
        }
        return "You can't go that way.";
    }
}
