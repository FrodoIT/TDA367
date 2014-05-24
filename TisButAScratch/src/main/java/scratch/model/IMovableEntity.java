package scratch.model;

import java.awt.geom.Rectangle2D;

/**
 * Created by pippin on 5/23/14.
 */
public interface IMovableEntity {
    Direction getMoveDirection();
    void setMoveDirection(Direction direction);
    void setPosition(Vector2D newPosition);
    Vector2D getPosition();
    Rectangle2D.Double getUnitTile();
}
