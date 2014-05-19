package scratch.model;

import java.awt.geom.Rectangle2D;
import java.util.Properties;

public class InteractiveObject implements IInteractiveObject {

    private final String name, type;
    private final Rectangle2D.Double rect;
    private final Properties properties;

    public InteractiveObject(String name, String type, int x, int y, int width, int height, Properties properties) {
        this.name = name;
        this.type = type;
        this.rect = new Rectangle2D.Double(x, y, width, height);
        this.properties = properties;
    }

    @Override
    public void setPosition(Vector2D newPos) {
        rect.setRect(newPos.getX(), newPos.getY(), rect.getWidth(), rect.getHeight());
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public Rectangle2D.Double getUnitTile() {
        return rect;
    }

    @Override
    public Properties getProperties() {
        return properties;
    }

    @Override
    public void update() {

    }
}