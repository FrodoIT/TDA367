package scratch.model;


import java.awt.*;

public interface INPCMove {
    public Point calculateNewPosition(IRoomData roomData, Point npcPosition);
}
