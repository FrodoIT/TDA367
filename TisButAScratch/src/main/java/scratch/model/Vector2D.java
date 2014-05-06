package scratch.model;

import java.awt.geom.Point2D;

public class Vector2D {
    private final Point2D.Double vectorInfo;

    private final double magnitude;

    public Vector2D(Point2D.Double startPoint, Point2D.Double endPoint){
        vectorInfo = new Point2D.Double(endPoint.x - startPoint.x, endPoint.y - startPoint.y);
        this.magnitude = Math.sqrt((vectorInfo.x) * (vectorInfo.x) + (vectorInfo.y) * (vectorInfo.y));
    }

    //Position vector. Used instead of Point.
    public Vector2D(double x, double y){
        vectorInfo = new Point2D.Double(x, y);
        magnitude = Math.sqrt(x*x + y*y);
    }

    public double getMagnitude() {
        return magnitude;
    }

    public Vector2D getNormalisedVector() {
        return new Vector2D(new Point2D.Double(0,0), new Point2D.Double(vectorInfo.x / magnitude , vectorInfo.y / magnitude));
    }

    public double getX() {
        return vectorInfo.x;
    }

    public double getY() {
        return vectorInfo.y;
    }

	@Override
	public String toString() {
		return String.format("(%f, %f) %f", vectorInfo.x, vectorInfo.y, magnitude );
	}
}