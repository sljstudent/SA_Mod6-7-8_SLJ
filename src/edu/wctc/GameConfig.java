package edu.wctc;

public final class GameConfig {
    private static final GameConfig INSTANCE = new GameConfig();

    private final int puzzleSolvedPoints = 5;
    private final int treasureLootedPoints = 10;
    private final int secretLootedPoints = 5;
    private final int exitUnlockedPoints = 5;
    private final int exitEscapedPoints = 20;
    private final int hiddenLootedPoints = 15;

    private GameConfig() {
    }

    public static GameConfig getInstance() {
        return INSTANCE;
    }

    public int getPuzzleSolvedPoints() {
        return puzzleSolvedPoints;
    }

    public int getTreasureLootedPoints() {
        return treasureLootedPoints;
    }

    public int getSecretLootedPoints() {
        return secretLootedPoints;
    }

    public int getExitUnlockedPoints() {
        return exitUnlockedPoints;
    }

    public int getExitEscapedPoints() {
        return exitEscapedPoints;
    }

    public int getHiddenLootedPoints() {
        return hiddenLootedPoints;
    }

    public int getTotalPossibleScore() {
        return puzzleSolvedPoints
                + treasureLootedPoints
                + secretLootedPoints
                + exitUnlockedPoints
                + exitEscapedPoints
                + hiddenLootedPoints;
    }
}
