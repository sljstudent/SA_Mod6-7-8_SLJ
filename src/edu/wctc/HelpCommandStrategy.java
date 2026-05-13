package edu.wctc;

public class HelpCommandStrategy implements CommandStrategy {
    @Override
    public String execute(Maze maze) {
        return "Commands: n/s/e/w/u/d or north/south/east/west/up/down,\n"
                + "i=interact, l=loot, x=exit room, v=inventory, q=quit";
    }
}
