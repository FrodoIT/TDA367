package scratch.model;


import java.awt.*;

public interface INPCMove {
    public Vector2D calculateNewPosition(IRoomData roomData, INpc npc);
}
