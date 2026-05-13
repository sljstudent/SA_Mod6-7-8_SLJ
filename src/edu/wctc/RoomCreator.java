package edu.wctc;

public abstract class RoomCreator {
    public final Room create(RoomType type) {
        return createRoom(type);
    }

    protected abstract Room createRoom(RoomType type);
}
