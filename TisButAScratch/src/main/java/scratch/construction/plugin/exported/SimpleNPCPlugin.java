package scratch.construction.plugin.exported;

import com.google.inject.Inject;
import scratch.construction.plugin.AIPlugin;
import scratch.construction.plugin.Pluggable;
import scratch.model.INPCMove;
import scratch.model.IRoomData;
import scratch.model.NpcType;
import scratch.model.Vector2D;

import java.awt.geom.Point2D;

/**
 * Moves NPC toward the Player.
 */
@AIPlugin(id = 1)
public final class SimpleNPCPlugin implements Pluggable<SimpleNPCPlugin>, INPCMove {

    @Inject

    private IRoomData roomData;

    @Override
    public SimpleNPCPlugin get() {
        return this;
    }

    @Override
    public SimpleNPCPlugin clone() throws CloneNotSupportedException{
	    super.clone();
        final SimpleNPCPlugin plugin = new SimpleNPCPlugin();
        plugin.roomData = this.roomData;
        return plugin;
    }

    @Override
    public Vector2D calculateNewPosition(NpcType npc) {
        final Vector2D npcPosition = npc.getPosition();
        final Vector2D playerPos = roomData.getClosestPlayerPosition(npcPosition);
        if (playerPos == null){
            return npcPosition;
        }
        final Vector2D directionVector = new Vector2D(new Point2D.Double(npcPosition.getX(), npcPosition.getY()),
                new Point2D.Double(playerPos.getX(), playerPos.getY())).getNormalisedVector();
        final int moveSpeed = npc.getMovementSpeed();
        return new Vector2D(npcPosition.getX() + directionVector.getX() * moveSpeed,
                npcPosition.getY() + directionVector.getY() * moveSpeed);

    }

    @Override
    public boolean isPromptingAnAttack(NpcType npc) {

        final Vector2D pos = npc.getPosition();
        final Vector2D closestPlayer = roomData.getClosestPlayerPosition(pos);
        if (closestPlayer != null && isWithinRange(closestPlayer, pos)) {
            return true;
        }
        return false;
    }

    @Override
    public void setRoomData(IRoomData roomData
    ) {
        this.roomData = roomData;
    }

    private boolean isWithinRange(Vector2D playerPos, Vector2D npcPos) {
        //+16 to get the center of the character, playerpos returns the point of the right high corner
        final Vector2D vector2D = new Vector2D(new Point2D.Double(playerPos.getX() + 16, playerPos.getY() + 16),
                new Point2D.Double(npcPos.getX() + 16, npcPos.getY() + 16));
        return vector2D.getMagnitude() <= 48;
    }

}
