package scratch.model.mockModules;

import scratch.model.IInteractiveObject;
import scratch.model.Vector2D;

import java.awt.geom.Rectangle2D;
import java.util.Properties;

/**
 * Created by pippin on 5/16/14.
 */
public class MockIInteractiveObject implements IInteractiveObject {
    @Override
    public void setPosition(Vector2D newPos) {

    }

    @Override
    public String getName() {
        return "mockDoor";
    }

    @Override
    public String getType() {
        return "DoorInteractPlugin";
    }

    @Override
    public Rectangle2D.Double getUnitTile() {
        return new Rectangle2D.Double(45,50,200,200);
    }

    @Override
    public Properties getProperties() {
        return new Properties();
    }
}
