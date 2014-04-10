package scratch.model;

import java.awt.Point;

public interface IMap {
    public boolean isColliding(Point point);
    public int getHeight();
    public int getWidth();
}
