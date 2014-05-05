package scratch.model;


import com.google.inject.ImplementedBy;
import scratch.construction.plugin.exported.SimpleMovePlugin;

import java.awt.*;
@ImplementedBy(SimpleMovePlugin.class)
public interface INPCMove {
    public Vector2D calculateNewPosition(IRoomData roomData, INpc npc);
}
