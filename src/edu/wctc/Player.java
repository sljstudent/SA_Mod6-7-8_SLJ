package edu.wctc;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private int score;
    private final List<String> inventory = new ArrayList<>();

    public void addToInventory(String item) {
        inventory.add(item);
    }

    public void addToScore(int points) {
        score += points;
    }

    public String getInventory() {
        if (inventory.isEmpty()) {
            return "Inventory: empty.";
        }
        return "Inventory: " + String.join(", ", inventory);
    }

    public int getScore() {
        return score;
    }
}
