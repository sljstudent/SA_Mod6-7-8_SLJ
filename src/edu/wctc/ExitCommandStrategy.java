package edu.wctc;

public class ExitCommandStrategy implements CommandStrategy {
    @Override
    public String execute(Maze maze) {
        return maze.exitCurrentRoom();
    }
}
