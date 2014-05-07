package scratch.model;


import com.google.inject.ImplementedBy;
import scratch.construction.plugin.exported.SimpleNPCPlugin;

@ImplementedBy(SimpleNPCPlugin.class)
public interface INPCMove {
    public Vector2D calculateNewPosition(IRoomData roomData, INpc npc);
	public boolean isAttacking(IRoomData roomData, INpc npc);

}
