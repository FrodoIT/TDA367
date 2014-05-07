package scratch.model.mockModules;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;
import scratch.model.IMap;
import scratch.model.Vector2D;

import java.awt.geom.Rectangle2D;
import java.util.*;

/**
 * Created by Anna on 2014-05-05.
 */
public class MockIMap implements IMap {
	private TiledMap map;

    public void createMockObject(){
		try {
			map = new TiledMap("res/untitled.tmx");
		} catch (SlickException e) {
			e.printStackTrace();
		}
    }

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
    public Map<String, Rectangle2D.Double> getNpcRectangleMap() {
        return null;
    }

    @Override
    public Map<String, Rectangle2D.Double> getObjectRectangles() {
        return null;
    }

    @Override
    public Map<String, Rectangle2D.Double> getObjectRectangleMap() {
        return null;
    }

    @Override
    public Set<String> getObjectNameSet() {
        return null;
    }

    @Override
    public Set<String> getNpcNameSet() {
        return null;
    }
}
