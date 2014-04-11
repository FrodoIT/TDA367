package scratch.construction.plugin.exported;


import scratch.construction.plugin.AIPlugin;
import scratch.construction.plugin.Pluggable;
import scratch.model.INPCMove;
import scratch.model.INpc;
import scratch.model.IRoomData;
import scratch.model.Vector2D;

import java.awt.*;

@AIPlugin(id = 1)
public class SimpleMovePlugin implements Pluggable<SimpleMovePlugin>, INPCMove{

    @Override
    public SimpleMovePlugin get() {
        return this;
    }

    @Override
    public Point calculateNewPosition(IRoomData roomData, INpc npc) {
        return new Point(npc.getPosition().x +1, npc.getPosition().y);
        /*
        Point playerPos = roomData.getPlayers().get(0).getPosition();
        Point npcPos = npc.getPosition();
        Vector2D directionVector = new Vector2D(npcPos, playerPos).getNormalisedVector();
        int moveSpeed = npc.getMovementSpeed();
        return new Point((int)(npcPos.getX()+directionVector.getX())*moveSpeed, (int)(npcPos.getY() + directionVector.getY())*moveSpeed);
        */
    }
}
