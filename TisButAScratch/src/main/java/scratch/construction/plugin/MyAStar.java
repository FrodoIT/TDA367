package scratch.construction.plugin;

import com.sun.net.httpserver.Authenticator;
import scratch.model.IMap;
import scratch.model.IRoomData;
import scratch.model.MoveDirection;
import scratch.model.Vector2D;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tejp on 2014-05-22.
 */
public class MyAStar extends AStar<Vector2D> {

    private final IRoomData roomData;

    public MyAStar(IRoomData roomData) {
        this.roomData = roomData;
    }

    @Override
    protected boolean isGoal(Vector2D node) {
        return roomData.getClosestPlayerPosition(node).distance(node) < 16;
    }

    //Cost of moving from one point to another
    @Override
    protected Double g(Vector2D from, Vector2D to) {
        return 32.d;
    }

    @Override
    protected Double h(Vector2D from, Vector2D to) {
        return Math.abs(from.getX() - to.getX()) + Math.abs(from.getY() - to.getY());
    }

    @Override
    protected List<Vector2D> generateSuccessors(Vector2D node) {
        final List<Vector2D> successors = new ArrayList<>();
        final IMap map = roomData.getMap();
        final int x = (int) ((node.getX()/32)*32);
        final int y = (int) ((node.getY()/32)*32);

        final MoveDirection[] directions = {
                MoveDirection.NORTH,
                MoveDirection.SOUTH,
                MoveDirection.WEST,
                MoveDirection.EAST
        };

        for (final MoveDirection direction : directions) {
            final Vector2D adjacentLoc = moveSquares(x, y, direction);
            if ( ! map.isColliding(adjacentLoc)) {
                successors.add(adjacentLoc);
                System.out.println("NEW SUCCESSOR: " + adjacentLoc);
            }
        }

        return successors;
    }

    private Vector2D moveSquares(int fromX, int fromY, MoveDirection direction) {
        System.out.print("FROM: " +fromX);
        System.out.println(";" + fromY);
        return new Vector2D(fromX + direction.getX() * 32, fromY + direction.getY() * 32);
    }
}
