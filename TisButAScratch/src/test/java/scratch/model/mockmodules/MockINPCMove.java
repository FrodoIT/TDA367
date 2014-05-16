package scratch.model.mockmodules;

import scratch.model.INPCMove;
import scratch.model.IRoomData;
import scratch.model.NpcType;
import scratch.model.Vector2D;

import java.awt.geom.Point2D;

public class MockINPCMove implements INPCMove {

    @Override
    public Vector2D calculateNewPosition(NpcType npc) {

        final Vector2D npcPos = npc.getPosition();
        if (npcPos.equals(new Vector2D(50,50))){
            return new Vector2D(50,50);
        }
        final Vector2D directionVector = new Vector2D(new Point2D.Double(npcPos.getX(), npcPos.getY()),
                    new Point2D.Double(50, 50)).getNormalisedVector();
        return new Vector2D(npcPos.getX() + directionVector.getX(),
                    npcPos.getY() + directionVector.getY());
    }

    @Override
    public boolean isPromptingAnAttack(NpcType npc) {
        return true;
    }

    @Override
    public void setRoomData(IRoomData roomData) {

    }
}
