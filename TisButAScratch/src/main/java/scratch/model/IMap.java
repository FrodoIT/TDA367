package scratch.model;

import com.google.inject.ImplementedBy;
import scratch.construction.SlickMap;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.List;
import java.util.Map;

@ImplementedBy(SlickMap.class)
public interface IMap {
	public boolean isColliding(Vector2D coordinate);
    public boolean hasInteractiveObject();
    public int getHeight();
    public int getWidth();
    public Map<String, Rectangle2D.Double> getObjectRectangles();
}
