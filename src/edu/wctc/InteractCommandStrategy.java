package edu.wctc;

public class InteractCommandStrategy implements CommandStrategy {
    @Override
    public String execute(Maze maze) {
        return maze.interactWithCurrentRoom();
    }
}
