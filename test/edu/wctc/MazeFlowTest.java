package edu.wctc;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MazeFlowTest {
    @Test
    void playerCanEarnAllConfiguredPointsAndFinish() {
        Maze maze = new Maze();

        maze.interactWithCurrentRoom();
        maze.move('e');
        maze.lootCurrentRoom();
        maze.move('w');
        maze.move('n');
        maze.lootCurrentRoom();
        maze.interactWithCurrentRoom();
        maze.move('s');
        maze.move('e');
        maze.move('e');
        maze.move('d');
        maze.lootCurrentRoom();
        maze.move('u');

        assertFalse(maze.isFinished());
        maze.exitCurrentRoom();

        assertTrue(maze.isFinished());
        assertEquals(maze.getTotalPossibleScore(), maze.getPlayerScore());
    }

    @Test
    void observerRecordsScoreChanges() {
        Maze maze = new Maze();

        maze.interactWithCurrentRoom();

        assertEquals(1, maze.getScoreAuditLog().getEntries().size());
        assertEquals("Score +5 => 5", maze.getScoreAuditLog().getEntries().get(0));
    }
}
