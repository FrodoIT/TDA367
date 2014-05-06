package scratch.model.mockModules;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;
import scratch.model.IMap;
import scratch.model.Vector2D;

import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by Anna on 2014-05-05.
 */
public class MockIMap implements IMap {
	private TiledMap map;
	private int collisionIndex;
	private int interactiveObjectIndex;

	public void createMockObject(){
		try {
			map = new TiledMap("res/untitled.tmx");
		} catch (SlickException e) {
			e.printStackTrace();
		}
		collisionIndex = map.getLayerIndex("collision");
		interactiveObjectIndex = map.getLayerIndex("interactiveObject");
	}


	@Override
	public boolean isColliding(Vector2D coordinate) {
		try {
			return (map.getTileId((int) coordinate.getX() / map.getTileWidth(),(int) coordinate.getY() / map.getTileHeight(), collisionIndex) != 0);
		}catch(IndexOutOfBoundsException e){
			return false;
		}
	}

	@Override
	public boolean hasInteractiveObject() {
		boolean found = false;
		for(int i = 0; i < map.getWidth(); i++){
			for(int j = 0; j < map.getHeight(); j++){
				found = map.getTileId(i, j, interactiveObjectIndex) != -1;
			}
		}
		return found;
	}

	@Override
	public int getHeight() {

		return map.getHeight()*map.getTileHeight();
	}

	@Override
	public int getWidth()  {
		return map.getWidth()*map.getTileWidth();
	}

    @Override
    public Map<String, Rectangle2D.Double> getObjectRectangles() {
        return null;
    }

	public Map<String, Rectangle2D.Double> getNPCRectangles() {
        Map<String, Rectangle2D.Double> npcRectangles = new TreeMap<String, Rectangle2D.Double>();
		npcRectangles.put("mock", new Rectangle2D.Double(32, 32, 32, 32));

		return npcRectangles;
	}

    public TiledMap getMap() {
        return map;
    }

    public int getCollisionIndex() {
        return collisionIndex;
    }

    public int getInteractiveObjectIndex() {
        return interactiveObjectIndex;
    }
}
