package scratch.model;

import java.awt.geom.Rectangle2D;

/**
 * Created by pippin on 4/13/14.
 */
public interface IInteractiveObject {
    public void interact();
    public IInteractiveObject createCopy(double xPosition, double yPosition);
    public Rectangle2D.Double getArea();
    public int getID();
}
