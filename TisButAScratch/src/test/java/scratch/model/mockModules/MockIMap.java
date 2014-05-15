package scratch.model.mockModules;

import scratch.construction.NpcSpecification;
import scratch.model.IInteractiveObject;
import scratch.model.IMap;
import scratch.model.Vector2D;

import java.awt.geom.Rectangle2D;
import java.util.List;
import java.util.Map;

/**
 * Created by Anna on 2014-05-05.
 */
public class MockIMap implements IMap {

	@Override
	public boolean isColliding(Vector2D coordinate) {
		return false;
	}

	@Override
	public boolean hasInteractiveObject() {
		return false;
	}

	@Override
	public boolean hasNpc() {
		return false;
	}

	@Override
	public int getHeight() {
		return 0;
	}

	@Override
	public int getWidth() {
		return 0;
	}

	@Override
	public List<IInteractiveObject> getInteractiveObjects() {
		return null;
	}

	@Override
	public List<NpcSpecification> getNpcSpecifications() {
		return null;
	}

	@Override
	public Map<String, Rectangle2D.Double> getPlayerRectangleMap() {
		return null;
	}
}