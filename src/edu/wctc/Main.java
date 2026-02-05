package edu.wctc;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Maze maze = new Maze();
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
                if (input.isEmpty()) {
                    System.out.println("Enter a command.");
                    continue;
                }

                String token = input.toLowerCase();
                if (token.equals("?") || token.equals("help")) {
                    System.out.println("Commands: n/s/e/w/u/d or north/south/east/west/up/down,");
                    System.out.println("i=interact, l=loot, x=exit room, v=inventory, q=quit");
                    continue;
                }

                // Map full words to single-char commands
                char cmd = token.charAt(0);
                if (token.startsWith("north")) cmd = 'n';
                else if (token.startsWith("south")) cmd = 's';
                else if (token.startsWith("east")) cmd = 'e';
                else if (token.startsWith("west")) cmd = 'w';
                else if (token.startsWith("up")) cmd = 'u';
                else if (token.startsWith("down")) cmd = 'd';
                else if (token.startsWith("interact") || token.startsWith("pull") || token.startsWith("use") || token.startsWith("press")) cmd = 'i';
                else if (token.startsWith("inventory")) cmd = 'v';
                else if (token.startsWith("quit")) cmd = 'q';

                if (cmd == 'q') {
                    System.out.println("Quitting the maze.");
                    break;
                }

                switch (cmd) {
                    case 'n':
                    case 's':
                    case 'e':
                    case 'w':
                    case 'u':
                    case 'd':
                        if (!maze.move(cmd)) {
                            System.out.println("You can't go that way.");
                        }
                        break;

                    case 'i':
                        System.out.println(maze.interactWithCurrentRoom());
                        break;

                    case 'l':
                        System.out.println(maze.lootCurrentRoom());
                        break;

                    case 'x':
                        System.out.println(maze.exitCurrentRoom());
                        break;

                    case 'v':
                        System.out.println(maze.getPlayerInventory());
                        break;

                    default:
                        System.out.println("Invalid command.");
                }
            }

            System.out.println("\nMaze finished! Final score: " + maze.getPlayerScore() +
                    " / " + maze.getTotalPossibleScore());
        } finally {
            sc.close();
        }
    }
}
