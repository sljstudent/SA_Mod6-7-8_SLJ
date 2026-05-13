package edu.wctc;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

class GameConfigTest {
    @Test
    void getInstanceAlwaysReturnsSameSingleton() {
        assertSame(GameConfig.getInstance(), GameConfig.getInstance());
    }

    @Test
    void totalPossibleScoreUsesConfiguredAwards() {
        assertEquals(60, GameConfig.getInstance().getTotalPossibleScore());
    }
}
