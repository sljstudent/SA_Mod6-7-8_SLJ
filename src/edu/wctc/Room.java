package edu.wctc;

public abstract class Room {

    private final String name;

    private Room north;
    private Room south;
    private Room east;
    private Room west;
    private Room up;
    private Room down;

    public Room(String name) {
        this.name = name;
    }

    public abstract String getDescription();

    public Room getAdjoiningRoom(char direction) {
        switch (Character.toLowerCase(direction)) {
            case 'n': return north;
            case 's': return south;
            case 'e': return east;
            case 'w': return west;
            case 'u': return up;
            case 'd': return down;
            default:  return null;
        }
    }

    public boolean isValidDirection(char direction) {
        return getAdjoiningRoom(direction) != null;
    }

    public String getExits() {
        StringBuilder exits = new StringBuilder("Exits: ");

        if (north != null) exits.append("n ");
        if (south != null) exits.append("s ");
        if (east  != null) exits.append("e ");
        if (west  != null) exits.append("w ");
        if (up    != null) exits.append("u ");
        if (down  != null) exits.append("d ");

        String result = exits.toString().trim();
        return result.equals("Exits:") ? "Exits: none" : result;
    }

    public String getName() {
        return name;
    }

    // Direction setters
    public void setNorth(Room north) { this.north = north; }
    public void setSouth(Room south) { this.south = south; }
    public void setEast(Room east)   { this.east = east; }
    public void setWest(Room west)   { this.west = west; }
    public void setUp(Room up)       { this.up = up; }
    public void setDown(Room down)   { this.down = down; }
}

