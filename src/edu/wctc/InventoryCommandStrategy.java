package edu.wctc;

public class InventoryCommandStrategy implements CommandStrategy {
    @Override
    public String execute(Maze maze) {
        return maze.getPlayerInventory();
    }
}
