package scratch.model;

import com.google.inject.ImplementedBy;
import scratch.construction.SlickMap;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.List;
@ImplementedBy(SlickMap.class)
public interface IMap {
	public boolean isColliding(Vector2D coordinate);
    public boolean hasInteractiveObject();
    public int getHeight();
    public int getWidth();
    public List<Rectangle2D.Double> getNPCRectangles();
}
