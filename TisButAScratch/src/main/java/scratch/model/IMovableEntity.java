package scratch.model;

import java.awt.geom.Rectangle2D;

/**
 * Created by pippin on 5/23/14.
 */
public interface IMovableEntity {
    MoveDirection getMoveDirection();
    void setMoveDirection();
    void setPosition(Vector2D newPosition);
    Vector2D getPosition();
    Rectangle2D.Double getUnitTile();
}
