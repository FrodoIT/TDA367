package scratch.construction.plugin.exported;

import com.google.inject.Inject;
import scratch.construction.plugin.AIPlugin;
import scratch.construction.plugin.Pluggable;
import scratch.model.*;

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
    public Vector2D calculateNewPosition(NpcType npc) {
        final Vector2D playerPos = getClosestPlayer(npc);
        final Vector2D npcPos = npc.getPosition();
        final Vector2D directionVector = new Vector2D(new Point2D.Double(npcPos.getX(), npcPos.getY()),
                new Point2D.Double(playerPos.getX(), playerPos.getY())).getNormalisedVector();
        final int moveSpeed = npc.getMovementSpeed();
        return new Vector2D(npcPos.getX() + directionVector.getX() * moveSpeed, npcPos.getY() + directionVector.getY() * moveSpeed);

    }

    @Override
    public boolean isPromptingAnAttack(NpcType npc) {
        for (final Player player : roomData.getPlayers()) {
            if (isWithinRange(player.getPosition(), npc.getPosition())) {
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

    private Vector2D getClosestPlayer(NpcType npc) {
        if (roomData.getPlayers().isEmpty()){
            return npc.getPosition();
        } else {
            return roomData.getPlayers().get(0).getPosition();
        }
        
    }
}
