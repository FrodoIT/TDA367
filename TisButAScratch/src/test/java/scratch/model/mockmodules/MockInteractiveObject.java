package scratch.model.mockmodules;

import scratch.model.InteractiveObject;
import scratch.model.Vector2D;

import java.awt.geom.Rectangle2D;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by pippin on 5/16/14.
 */
public class MockInteractiveObject extends InteractiveObject {

    @Override
    public Vector2D getPosition() {
        return null;
    }

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
        return new Rectangle2D.Double(45, 50, 200, 200);
    }

    @Override
    public Map<String, String> getProperties() {
        return new HashMap();
    }
}
