package scratch.construction.plugin.exported;

import com.google.inject.Inject;
import scratch.construction.plugin.AIPlugin;
import scratch.construction.plugin.Pluggable;
import scratch.model.INPCMove;
import scratch.model.IRoomData;
import scratch.model.NpcType;
import scratch.model.Vector2D;

import java.awt.geom.Point2D;
import java.util.Random;

/**
 * Created by Anna on 2014-05-14.
 */
@AIPlugin(id = 2)
public final class CPNPCPlugin implements Pluggable<CPNPCPlugin>, INPCMove {

    @Inject
    private final Random ran = new Random(System.nanoTime());
    private IRoomData roomData;

    @Override
    public CPNPCPlugin get() {
        return this;
    }

    @Override
    public CPNPCPlugin clone() throws CloneNotSupportedException{
	    super.clone();
        final CPNPCPlugin plugin = new CPNPCPlugin();
        plugin.roomData = this.roomData;
        return plugin;
    }

    @Override
    public Vector2D calculateNewPosition(NpcType npc) {
        final Vector2D currentPos = npc.getPosition();
        return new Vector2D(currentPos.getX() + ran.nextInt(3) - 1, currentPos.getY() + ran.nextInt(3) - 1);
    }

    @Override
    public boolean isPromptingAnAttack(NpcType npc) {
        if (roomData != null) {
            final Vector2D pos = npc.getPosition();
            final Vector2D playerPos = roomData.getClosestPlayerPosition(pos);
            if (playerPos != null && isWithinRange(playerPos, pos)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void setRoomData(IRoomData roomData) {
        this.roomData = roomData;
    }

    private boolean isWithinRange(Vector2D playerPos, Vector2D npcPos) {
        //+16 to get the center of the character, playerpos returns the point of the right high corner
        final Vector2D vector2D = new Vector2D(new Point2D.Double(playerPos.getX() + 16, playerPos.getY() + 16), new Point2D.Double(npcPos.getX() + 16, npcPos.getY() + 16));
        return vector2D.getMagnitude() <= 48;
    }
}
