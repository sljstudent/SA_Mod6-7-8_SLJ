package edu.wctc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ScoreAuditLog implements ScoreObserver {
    private final List<String> entries = new ArrayList<>();

    @Override
    public void scoreChanged(int pointsAdded, int newTotal) {
        entries.add("Score +" + pointsAdded + " => " + newTotal);
    }

    public List<String> getEntries() {
        return Collections.unmodifiableList(entries);
    }
}
