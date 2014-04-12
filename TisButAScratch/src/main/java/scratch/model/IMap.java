package scratch.model;

import java.awt.*;
import java.util.List;

public interface IMap {
    public boolean isColliding(Vector2D coordinate);
    public int getHeight();
    public int getWidth();
    public List<Rectangle> getNPCRectangles();
}
