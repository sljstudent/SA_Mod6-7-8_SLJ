package edu.wctc;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CommandStrategyTest {
    @Test
    void parserReturnsMoveStrategyForDirectionCommands() {
        Maze maze = new Maze();
        CommandParser parser = new CommandParser();

        String result = parser.parse("east").execute(maze);

        assertEquals("", result);
        assertEquals("Treasure Room", maze.getCurrentRoomName());
    }

    @Test
    void parserReturnsInteractionStrategyForShortInteractionCommand() {
        Maze maze = new Maze();
        CommandParser parser = new CommandParser();

        String result = parser.parse("i").execute(maze);

        assertTrue(result.contains("hidden door opens"));
        assertEquals(5, maze.getPlayerScore());
    }

    @Test
    void parserReturnsExitStrategyForExitWord() {
        Maze maze = new Maze();
        CommandParser parser = new CommandParser();

        maze.move('e');
        maze.move('e');
        String result = parser.parse("exit").execute(maze);

        assertEquals("The door is locked. You can't leave yet.", result);
        assertEquals("Exit Room", maze.getCurrentRoomName());
    }

    @Test
    void quitStrategyFinishesMaze() {
        Maze maze = new Maze();

        assertFalse(maze.isFinished());
        String result = new QuitCommandStrategy().execute(maze);

        assertEquals("Quitting the maze. It was toooo tough!", result);
        assertTrue(maze.isFinished());
    }
}
