package scratch.construction.plugin.exported;


import scratch.construction.plugin.AIPlugin;
import scratch.construction.plugin.Pluggable;
import scratch.model.INPCMove;
import scratch.model.INpc;
import scratch.model.IRoomData;

import java.awt.*;

@AIPlugin(id = 1)
public class SimpleMovePlugin implements Pluggable<SimpleMovePlugin>, INPCMove{

    @Override
    public SimpleMovePlugin get() {
        return this;
    }

    @Override
    public Point calculateNewPosition(IRoomData roomData, INpc npc) {
        /*Point playerPos= roomData.getPlayers().get(0).getPosition();
        Point npcPos = npc.getPosition();
        int moveSpeed = npc.getMovementSpeed();
        int diffX = playerPos.x - npcPos.x
        int diffY = playerPos.y - npcPos.y;
        */


        return new Point(npc.getPosition().x+1, npc.getPosition().y);
    }
}
