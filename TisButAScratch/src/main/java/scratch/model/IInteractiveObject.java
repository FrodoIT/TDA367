package scratch.model;

/**
 * Created by pippin on 4/13/14.
 */
public interface IInteractiveObject {
    public void interact();
    public IInteractiveObject createCopy(double xPosition, double yPosition);
    public double getArea();
    public int getID();
}
