package scratch.model;

/**
 * Created by pippin on 2014-05-25.
 */
public class StandardNpcPlugin implements INPCMove {
    @Override
    public Vector2D calculateNewPosition(NpcType npc) {
        return npc.getPosition();
    }

    @Override
    public boolean isPromptingAnAttack(NpcType npc) {
        return false;
    }

    @Override
    public void setRoomData(IRoomData roomData) {
        //Not needed
    }
}
