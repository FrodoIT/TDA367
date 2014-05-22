package scratch.construction.plugin.exported;

import com.sun.org.apache.bcel.internal.generic.ASTORE;
import scratch.construction.plugin.AIPlugin;
import scratch.construction.plugin.AStar;
import scratch.construction.plugin.MyAStar;
import scratch.construction.plugin.Pluggable;
import scratch.model.INPCMove;
import scratch.model.IRoomData;
import scratch.model.NpcType;
import scratch.model.Vector2D;

import java.awt.geom.Point2D;
import java.util.List;
import java.util.Random;

/**
 * Created by tejp on 2014-05-21.
 */
@AIPlugin(id = 3)
public class AStarNpcPlugin implements Pluggable, INPCMove{

    private IRoomData roomData;

    private List<Vector2D> path;
    private Vector2D lastPos;

    @Override
    public AStarNpcPlugin clone() {
        AStarNpcPlugin plugin = new AStarNpcPlugin();
        plugin.roomData = this.roomData;
        return plugin;
    }

    @Override
    public Vector2D calculateNewPosition(NpcType npc) {
        if (path == null || path.isEmpty()) {
            calculateNewPath(npc);
        }

        final Vector2D playerPos = path == null ? npc.getPosition() : path.get(0);
        final Vector2D npcPos = npc.getPosition();
        final Vector2D directionVector = new Vector2D(
                new Point2D.Double(npcPos.getX(), npcPos.getY()),
                new Point2D.Double(playerPos.getX(), playerPos.getY())).getNormalisedVector();
        final int moveSpeed = npc.getMovementSpeed();

        if (npc.getPosition().distance(path.get(0)) < 4) {
            System.out.println("GOAL!" + path.remove(0));
        }

        Vector2D calculatedPos = new Vector2D(npcPos.getX() + directionVector.getX() * moveSpeed, npcPos.getY() + directionVector.getY() * moveSpeed);
        if (calculatedPos.equals(lastPos)) {
            calculateNewPath(npc);
        }
        lastPos = calculatedPos;
        return calculatedPos;
    }

    private void calculateNewPath(NpcType npc) {
        final AStar<Vector2D> aStar = new MyAStar(roomData);
        path = aStar.compute(npc.getPosition());
        if (path == null)
            calculateNewPath(npc);
    }

    @Override
    public boolean isPromptingAnAttack(NpcType npc) {
        return false;
    }

    @Override
    public void setRoomData(IRoomData roomData) {
        this.roomData = roomData;
    }

    @Override
    public Object get() {
        return null;
    }
}
