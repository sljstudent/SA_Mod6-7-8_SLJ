package edu.wctc;

public class QuitCommandStrategy implements CommandStrategy {
    @Override
    public String execute(Maze maze) {
        maze.quit();
        return "Quitting the maze. It was toooo tough!";
    }
}
