package edu.wctc;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Maze maze = new Maze();
        CommandParser commandParser = new CommandParser();
        Scanner sc = new Scanner(System.in);

        try {
            while (!maze.isFinished()) {
                // Show description + exits only when entering a room (or at start)
                if (maze.shouldShowRoomDescription()) {
                    System.out.println();
                    System.out.println(maze.getCurrentRoomDescription());
                    System.out.println(maze.getCurrentRoomExits());
                    maze.setRoomDescriptionShown();
                }

                System.out.print("Command (n/s/e/w/u/d, i=interact, l=loot, x=exit, v=inventory, ?=help, q=quit): ");

                String input = sc.nextLine().trim();
                String result = commandParser.parse(input).execute(maze);
                if (!result.isEmpty()) {
                    System.out.println(result);
                }
            }

            System.out.println("\nMaze finished! Final score: " + maze.getPlayerScore() +
                    " / " + maze.getTotalPossibleScore());
        } finally {
            sc.close();
        }
    }
}
