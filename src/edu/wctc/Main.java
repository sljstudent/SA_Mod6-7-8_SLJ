package edu.wctc;

import java.util.Scanner;

public class Main {
    static void main() {

        Maze maze = new Maze();
        Scanner sc = new Scanner(System.in);

        while (!maze.isFinished()) {
            System.out.println();
            System.out.println(maze.getCurrentRoomDescription());
            System.out.println(maze.getCurrentRoomExits());
            System.out.print("Command (n/s/e/w/u/d, i=interact, l=loot, x=exit, v=inventory): ");

            String input = sc.nextLine().trim();
            if (input.isEmpty()) {
                System.out.println("Enter a command.");
                continue;
            }

            char cmd = Character.toLowerCase(input.charAt(0));

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

        System.out.println("\nMaze finished! Final score: " + maze.getPlayerScore());
        sc.close();
    }
}

