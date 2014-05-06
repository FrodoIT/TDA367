/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package scratch.construction;

import com.google.inject.Inject;
import org.newdawn.slick.tiled.TiledMap;
import scratch.model.IMap;
import scratch.model.Vector2D;

import java.awt.geom.Rectangle2D;
import java.util.*;

/**
 *
 * @author Ivar
 */
//NOTE!!! If changed please edit the MockIMap too since the code should be identical.

public class SlickMap implements IMap{
    private final TiledMap map;
    private final int collisionIndex;
    //Object map holds the start position of each object,
    //as well as the name of the object.
    private Map<String, Vector2D> objectMap;
    private Set<String> objectNameSet;

    public TiledMap getMap() {
        return map;
    }

    public int getCollisionIndex() {
        return collisionIndex;
    }

    public Map<String, Vector2D> getObjectMap() {
        return objectMap;
    }

    public Set<String> getObjectNameSet() {
        return objectNameSet;
    }

    @Inject
    public SlickMap(TiledMap map){
        this.map = map;


        initialiseObjectMap(map);
        collisionIndex = map.getLayerIndex("collision");
        objectNameSet = objectMap.keySet();
    }

    /**
     * A wrap-around for the confusing and dysfunctional TiledMap API.
     * @param map is the room map
     */
    private void initialiseObjectMap(TiledMap map) {
        objectMap = new TreeMap<>();
        int objectGroupIndex = map.getObjectGroupCount();
        for(int i = 0; i < objectGroupIndex; i ++){//
            int objectIndex = map.getObjectCount(i);
            for(int j = 0; j < objectIndex; j++){
                //Un-comment below to see what indexes each object has.
                //System.out.println(map.getObjectName(i,j) + "   " + i + " object group index " + j + " object index" );
                Vector2D objectPosition = new Vector2D(map.getObjectX(i,j), map.getObjectY(i,j));

                objectMap.put(map.getObjectName(i,j), objectPosition);
            }
        }
    }

    public boolean isColliding(Vector2D coordinate) {
        try {
            return (map.getTileId((int) coordinate.getX() / map.getTileWidth(),(int) coordinate.getY() / map.getTileHeight(), collisionIndex) != 0);
        }catch(IndexOutOfBoundsException e){
            return false;
        }
    }

    @Override
    public boolean hasInteractiveObject() {
        return objectMap.isEmpty();
    }

    public int getHeight() {

        return map.getHeight()*map.getTileHeight();
    }

    public int getWidth() {
        return map.getWidth()*map.getTileWidth();
    }

    @Override
    public java.util.List<Rectangle2D.Double> getNPCRectangles() {
        //TODO implement this correcly. should load from tiled map. with layer npc
        java.util.List<Rectangle2D.Double> npcRectangles = new ArrayList<>();

        npcRectangles.add(new Rectangle2D.Double(32, 32, 32, 32));

        return npcRectangles;
    }

}
