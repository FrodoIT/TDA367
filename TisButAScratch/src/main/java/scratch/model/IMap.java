package scratch.model;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.List;

public interface IMap {
    public boolean isColliding(Vector2D coordinate);
    public boolean hasInteractiveObject();
    public int getHeight();
    public int getWidth();
    public List<Rectangle2D.Double> getNPCRectangles();
}
