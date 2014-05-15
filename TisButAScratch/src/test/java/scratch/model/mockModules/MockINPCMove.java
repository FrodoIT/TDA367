package scratch.model.mockModules;

import scratch.model.INPCMove;
import scratch.model.IRoomData;
import scratch.model.NpcType;
import scratch.model.Vector2D;

import java.awt.geom.Point2D;

public class MockINPCMove implements INPCMove {

    @Override
    public Vector2D calculateNewPosition(NpcType npc) {

            Vector2D npcPos = npc.getPosition();
        if (npcPos.equals(new Vector2D(50,50))){
            return (new Vector2D(50,50));
        }
            Vector2D directionVector = new Vector2D(new Point2D.Double(npcPos.getX(), npcPos.getY()),
                    new Point2D.Double(50, 50)).getNormalisedVector();
            return new Vector2D(npcPos.getX() + directionVector.getX(),
                    npcPos.getY() + directionVector.getY());
    }

    @Override
    public boolean isAttacking(NpcType npc) {
        return false;
    }

    @Override
    public void setRoomData(IRoomData roomData) {

    }
}
