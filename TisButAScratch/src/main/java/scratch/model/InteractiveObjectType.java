package scratch.model;

import java.awt.geom.Rectangle2D;
//unitTile, interactionType, id
public final class InteractiveObjectType implements IInteractiveObject{
    private final Rectangle2D unitTile;
    private final  IInteraction interactionType;
    private final int id;
    private final String imagepath;

    public InteractiveObjectType(int id, IInteraction interactionType, String imagepath, Rectangle2D.Double unitTile){
        this.unitTile = unitTile;
        this.id = id;
        this.interactionType = interactionType;
        this.imagepath = imagepath;

    }
    @Override
    public void interact() {
        interactionType.performInteraction();
    }

    @Override
    public IInteractiveObject createCopy(double xPosition, double yPosition) {
        return new InteractiveObjectType(id, interactionType, imagepath,
                new Rectangle2D.Double(unitTile.getX(), unitTile.getY(), unitTile.getWidth(), unitTile.getHeight()));
    }

    @Override
    public double getArea() {
        return unitTile.getHeight()*unitTile.getWidth();
    }

    @Override
    public int getID() {
        return id;
    }

}

