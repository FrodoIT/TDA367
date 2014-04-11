package scratch.model;

import java.awt.*;

/**
 * Remember that the direction uses Math.atan which does not work the way you would expect.
 * At the moment the normalised vector's decimals are cut off, therefore it might not work as expected either...
 */
public class Vector2D {
    private final Point vectorinfo;

    private final double magnitude, x, y;

    public Vector2D(Point startPoint, Point endPoint){
        vectorinfo = new Point();
        vectorinfo.setLocation(endPoint.x - startPoint.x, endPoint.y - startPoint.y);
        this.magnitude = Math.sqrt((vectorinfo.x) * (vectorinfo.x) + (vectorinfo.y) * (vectorinfo.y));
        x = vectorinfo.getX();
        y = vectorinfo.getY();
    }

    public double getMagnitude() {
        return magnitude;
    }

    public Vector2D getNormalisedVector() {
        return new Vector2D( new Point(0,0) , new Point((int)(vectorinfo.getX() /magnitude ),(int)(vectorinfo.getY()/magnitude)));
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
}