package scratch.model;

import java.awt.geom.Point2D;

public final class Vector2D {

    private final Point2D.Double vectorInfo;
    private final double magnitude;

    /**
     * Should never be called, needed for serialisation of class.
     */
    public Vector2D() {
        vectorInfo = new Point2D.Double(0, 0);
        magnitude = 0;
    }

    public Vector2D(Point2D.Double startPoint, Point2D.Double endPoint) {
        vectorInfo = new Point2D.Double(endPoint.x - startPoint.x, endPoint.y - startPoint.y);
        this.magnitude = Math.sqrt((vectorInfo.x) * (vectorInfo.x) + (vectorInfo.y) * (vectorInfo.y));
    }

    //Position vector. Used instead of Point.
    public Vector2D(double x, double y) {
        vectorInfo = new Point2D.Double(x, y);
        magnitude = Math.sqrt(x * x + y * y);
    }

    public double getMagnitude() {
        return magnitude;
    }

    public Vector2D getNormalisedVector() {
        if (vectorInfo.getX() == 0 && vectorInfo.getY() == 0) {
            return this;
        }
        return new Vector2D(new Point2D.Double(0, 0), new Point2D.Double(vectorInfo.x / magnitude, vectorInfo.y / magnitude));
    }

    public double getX() {
        return vectorInfo.x;
    }

    public double getY() {
        return vectorInfo.y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Vector2D)) {
            return false;
        }

        final Vector2D vector2D = (Vector2D) o;

        if (Double.compare(vector2D.magnitude, magnitude) != 0) {
            return false;
        }
        if (vectorInfo != null ? !vectorInfo.equals(vector2D.vectorInfo) : vector2D.vectorInfo != null) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        final int result = vectorInfo != null ? vectorInfo.hashCode() : 0;
        final long temp = Double.doubleToLongBits(magnitude);
        return 31 * result + (int) (temp ^ (temp >>> 32));
    }

    public double distance(Vector2D toPosition) {
        final double xDiff = getX() - toPosition.getX();
        final double yDiff = getY() - toPosition.getY();
        return Math.sqrt(xDiff * xDiff + yDiff * yDiff);
    }
}
