package scratch.model;


import com.google.inject.ImplementedBy;
import scratch.construction.plugin.exported.SimpleNPCPlugin;

@ImplementedBy(SimpleNPCPlugin.class)
public interface INPCMove {
    Vector2D calculateNewPosition(NpcType npc);
    boolean isPromptingAnAttack(NpcType npc);
    void setRoomData(IRoomData roomData);
}
