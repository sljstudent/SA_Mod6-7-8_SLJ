package edu.wctc;

public class CommandParser {
    public CommandStrategy parse(String input) {
        String token = input.trim().toLowerCase();
        if (token.isEmpty()) {
            return new InvalidCommandStrategy("Enter a command.");
        }
        if (token.equals("?") || token.equals("help")) {
            return new HelpCommandStrategy();
        }

        char command = token.charAt(0);
        if (token.startsWith("north")) command = 'n';
        else if (token.startsWith("south")) command = 's';
        else if (token.startsWith("east")) command = 'e';
        else if (token.startsWith("west")) command = 'w';
        else if (token.startsWith("up")) command = 'u';
        else if (token.startsWith("down")) command = 'd';
        else if (token.startsWith("interact")
                || token.startsWith("pull")
                || token.startsWith("use")
                || token.startsWith("press")) command = 'i';
        else if (token.startsWith("exit")) command = 'x';
        else if (token.startsWith("inventory")) command = 'v';
        else if (token.startsWith("quit")) command = 'q';

        return switch (command) {
            case 'n', 's', 'e', 'w', 'u', 'd' -> new MoveCommandStrategy(command);
            case 'i' -> new InteractCommandStrategy();
            case 'l' -> new LootCommandStrategy();
            case 'x' -> new ExitCommandStrategy();
            case 'v' -> new InventoryCommandStrategy();
            case 'q' -> new QuitCommandStrategy();
            default -> new InvalidCommandStrategy("Invalid command.");
        };
    }
}
