package edu.wctc;

public class HiddenExitRoom extends ExitRoom {

    public HiddenExitRoom(String name) {
        super(name);
    }

    @Override
    public String getExits() {
        String exits = super.getExits();
        if (exits.equals("Exits: none")) {
            return exits;
        }

        // Remove any standalone 'd' token from the exits list
        String prefix = "Exits:";
        String rest = exits.substring(prefix.length()).trim();
        if (rest.isEmpty()) {
            return "Exits: none";
        }

        StringBuilder sb = new StringBuilder("Exits:");
        for (String tok : rest.split("\\s+")) {
            if (!tok.equals("d")) {
                sb.append(" ").append(tok);
            }
        }

        String result = sb.toString();
        return result.equals("Exits:") ? "Exits: none" : result;
    }
}
