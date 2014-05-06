package scratch.model;

import com.google.inject.ImplementedBy;
import scratch.construction.SlickMap;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.List;
import java.util.Map;
import java.util.Set;

@ImplementedBy(SlickMap.class)
public interface IMap {
	public boolean isColliding(Vector2D coordinate);
    public boolean hasInteractiveObject();
    public boolean hasNpc();
    public int getHeight();
    public int getWidth();
    public Map<String, Rectangle2D.Double> getNpcRectangleMap();
    public Map<String, Rectangle2D.Double> getObjectRectangles();
    public Map<String, Rectangle2D.Double> getObjectRectangleMap();
    public Set<String> getObjectNameSet();
    public Set<String> getNpcNameSet();
}
