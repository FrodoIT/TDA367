package scratch.model;

import com.google.inject.ImplementedBy;
import scratch.construction.SlickMap;

import java.awt.geom.Rectangle2D;
import java.util.List;
import java.util.Map;
import java.util.Set;

@ImplementedBy(SlickMap.class)
public interface IMap {
	boolean isColliding(Vector2D coordinate);
    boolean hasInteractiveObject();
    boolean hasNpc();
    int getHeight();
    int getWidth();
	List<IInteractiveObject> getInteractiveObjects();
    Map<String, Rectangle2D.Double> getNpcRectangleMap();
	Map<String, Rectangle2D.Double> getPlayerRectangleMap();
}
