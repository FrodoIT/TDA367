package scratch.model;

import com.google.inject.ImplementedBy;
import scratch.construction.NpcSpecification;
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
    List<NpcSpecification> getNpcSpecifications();
	Map<String, Rectangle2D.Double> getPlayerRectangleMap();
}
