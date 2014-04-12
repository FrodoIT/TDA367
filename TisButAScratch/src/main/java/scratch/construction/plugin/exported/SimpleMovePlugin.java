package scratch.construction.plugin.exported;


import scratch.construction.plugin.AIPlugin;
import scratch.construction.plugin.Pluggable;
import scratch.model.INPCMove;
import scratch.model.INpc;
import scratch.model.IRoomData;
import scratch.model.Vector2D;

import java.awt.*;
import java.awt.geom.Point2D;

@AIPlugin(id = 1)
public class SimpleMovePlugin implements Pluggable<SimpleMovePlugin>, INPCMove{

    @Override
    public SimpleMovePlugin get() {
        return this;
    }

    @Override
    public Vector2D calculateNewPosition(IRoomData roomData, INpc npc) {
        Point playerPos = roomData.getPlayers().get(0).getPosition();
        Point npcPos = npc.getPosition();
        Vector2D directionVector = new Vector2D(new Point2D.Double(npcPos.x, npcPos.y), new Point2D.Double(playerPos.x, playerPos.y)).getNormalisedVector();
        int moveSpeed = npc.getMovementSpeed();
        return new Vector2D((npcPos.getX() + directionVector.getX()) * moveSpeed, (npcPos.getY() + directionVector.getY()) * moveSpeed);

    }
}
