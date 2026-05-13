package edu.wctc;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;

class MazeRoomCreatorTest {
    @Test
    void factoryMethodCreatesExpectedRoomTypes() {
        RoomCreator creator = new MazeRoomCreator();

        assertInstanceOf(PuzzleRoom.class, creator.create(RoomType.PUZZLE));
        assertInstanceOf(TreasureRoom.class, creator.create(RoomType.TREASURE));
        assertInstanceOf(SecretRoom.class, creator.create(RoomType.SECRET));
        assertInstanceOf(HiddenExitRoom.class, creator.create(RoomType.EXIT));
        assertInstanceOf(DownSecretRoom.class, creator.create(RoomType.HIDDEN_DOWN));
    }
}
