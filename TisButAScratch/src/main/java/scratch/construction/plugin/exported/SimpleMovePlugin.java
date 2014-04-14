package scratch.construction.plugin.exported;


import scratch.construction.plugin.AIPlugin;
import scratch.construction.plugin.Pluggable;
import scratch.model.INPCMove;
import scratch.model.INpc;
import scratch.model.IRoomData;
import scratch.model.Vector2D;

import java.awt.*;
import java.awt.geom.Point2D;

/**
 * Moves NPC toward the Player.
 */
@AIPlugin(id = 1)
public final class SimpleMovePlugin implements Pluggable<SimpleMovePlugin>, INPCMove{

    @Override
    public SimpleMovePlugin get() {
        return this;
    }

    @Override
    public Vector2D calculateNewPosition(IRoomData roomData, INpc npc) {
        Vector2D playerPos = roomData.getPlayers().get(0).getPosition();
        Vector2D npcPos = npc.getPosition();
        Vector2D directionVector = new Vector2D(new Point2D.Double(npcPos.getX(), npcPos.getY()), new Point2D.Double(playerPos.getX(), playerPos.getY())).getNormalisedVector();
        int moveSpeed = npc.getMovementSpeed();
        return new Vector2D((npcPos.getX() + directionVector.getX()) * moveSpeed, (npcPos.getY() + directionVector.getY()) * moveSpeed);

    }
}
