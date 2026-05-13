package edu.wctc;

public class LootCommandStrategy implements CommandStrategy {
    @Override
    public String execute(Maze maze) {
        return maze.lootCurrentRoom();
    }
}
