package model;

import java.awt.Point;

public interface IMap {
    public boolean collisionAt(Point point);
    public int getHeight();
    public int getWidth();
}
