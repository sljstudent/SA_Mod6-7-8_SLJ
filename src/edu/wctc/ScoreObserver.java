package edu.wctc;

public interface ScoreObserver {
    void scoreChanged(int pointsAdded, int newTotal);
}
