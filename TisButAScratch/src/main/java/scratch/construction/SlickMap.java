/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package scratch.construction;

import com.google.inject.Inject;
import scratch.model.IInteractiveObject;
import scratch.model.IMap;
import scratch.model.Vector2D;

import java.awt.geom.Rectangle2D;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 *
 * @author Ivar
 */
//NOTE!!! If changed please edit the MockIMap too since the code should be identical.

public class SlickMap implements IMap{
    private final TiledMapPlus map;
    private final int collisionIndex;
    //Object map holds the start position of each object,
    //as well as the name of the object.
    
    private Map<String, Rectangle2D.Double> npcRectangleMap;
    private Map<String, Rectangle2D.Double> objectRectangleMap = new TreeMap<>();
    private Map<String, Rectangle2D.Double> playerRectangleMap;

    private final int height, width;

    @Inject
    public SlickMap(TiledMapPlus map){
        this.map = map;
        height = map.getHeight()*map.getTileHeight();
        width = map.getWidth()*map.getTileWidth();
        collisionIndex = map.getLayerIndex("collision");

	    npcRectangleMap = map.getNpcRectangleMap();
		playerRectangleMap = map.getPlayerRectangleMap();
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
        return objectRectangleMap.isEmpty();
    }

    @Override
    public boolean hasNpc(){
        return npcRectangleMap.isEmpty();
    }

    @Override
    public int getHeight() {

        return height;
    }
    @Override
    public int getWidth() {
        return width;
    }

	@Override
	public List<IInteractiveObject> getInteractiveObjects() {
		return map.getInteractiveObjects();
	}

    @Override
    public Map<String, Rectangle2D.Double> getNpcRectangleMap() {
        return npcRectangleMap;
    }

	@Override
	public Map<String, Rectangle2D.Double> getPlayerRectangleMap() {
		return null;
	}
}
