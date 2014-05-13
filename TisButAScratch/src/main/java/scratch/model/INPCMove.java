package scratch.model;


import com.google.inject.ImplementedBy;
import scratch.construction.plugin.exported.SimpleNPCPlugin;

@ImplementedBy(SimpleNPCPlugin.class)
public interface INPCMove {
    public Vector2D calculateNewPosition(NpcType npc);
    public boolean isAttacking(NpcType npc);
    public void setRoomData(IRoomData roomData);
}
