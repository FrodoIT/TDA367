package scratch.model.mockmodules;

import scratch.construction.NpcSpecification;
import scratch.model.IMap;
import scratch.model.InteractiveObject;
import scratch.model.Vector2D;

import java.util.List;

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
		return 10000;
	}

	@Override
	public int getWidth() {
		return 10000;
	}

	@Override
	public List<InteractiveObject> getInteractiveObjects() {
		return null;
	}

	@Override
	public List<NpcSpecification> getNpcSpecifications() {
		return null;
	}

    @Override
    public int getId() {
        return 1;

    }
}