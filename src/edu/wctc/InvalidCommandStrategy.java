package edu.wctc;

public class InvalidCommandStrategy implements CommandStrategy {
    private final String message;

    public InvalidCommandStrategy(String message) {
        this.message = message;
    }

    @Override
    public String execute(Maze maze) {
        return message;
    }
}
