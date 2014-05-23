package scratch.model;

import java.awt.geom.Rectangle2D;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Created by pippin on 4/13/14.
 */
public interface IInteractiveObject {
    Vector2D getPosition();
    void setPosition(Vector2D newPos);
	String getName();
	String getType();
	Rectangle2D.Double getUnitTile();
	Map<String, String> getProperties();
    void update();
	void setObject(InteractiveObject recievedObject);
}
