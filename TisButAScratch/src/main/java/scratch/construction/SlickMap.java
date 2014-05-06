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
    
    private Map<String, Rectangle2D.Double> npcRectangleMap;
    private Set<String> npcNameSet;
    private Map<String, Rectangle2D.Double> objectRectangleMap;
    private Set<String> objectNameSet;
    private Map<String, Rectangle2D.Double> playerRectangleMap;
    private Set<String> playerNameSet;


    private final int height, width;

    @Inject
    public SlickMap(TiledMap map){
        this.map = map;
        height = map.getHeight()*map.getTileHeight();
        width = map.getWidth()*map.getTileWidth();
        collisionIndex = map.getLayerIndex("collision");
        initialiseEntityMaps(map);
        npcNameSet = npcRectangleMap.keySet();
        objectNameSet = objectRectangleMap.keySet();
        playerNameSet = playerRectangleMap.keySet();
        List<Rectangle2D.Double> npcRectangles = new ArrayList<>();
        npcRectangles.add(new Rectangle2D.Double(32, 32, 32, 32));

    }

    /**
     * A wrap-around for the confusing and dysfunctional TiledMap API.
     * @param map is the room map
     */
    private void initialiseEntityMaps(TiledMap map) {
        npcRectangleMap = new TreeMap<>();
        playerRectangleMap = new TreeMap<>();
        objectRectangleMap = new TreeMap<>();
        int objectGroupIndex = map.getObjectGroupCount();
        for(int i = 0; i < objectGroupIndex; i ++){//
            int objectIndex = map.getObjectCount(i);
            for(int j = 0; j < objectIndex; j++){
                //Un-comment below to see what indexes each object has.
                //System.out.println(map.getObjectName(i,j) + "   " + i + " object group index " + j + " object index" );                Vector2D objectPosition = new Vector2D(map.getObjectX(i,j), map.getObjectY(i,j));

                Rectangle2D.Double objectArea = new Rectangle2D.Double(map.getObjectX(i,j), map.getObjectY(i,j),
                        map.getObjectWidth(i,j), map.getObjectHeight(i,j));
                if(map.getObjectType(i,j) == "npc"){
                    npcRectangleMap.put(map.getObjectName(i, j), objectArea);
                } else if (map.getObjectType(i,j) == "object"){
                    objectRectangleMap.put(map.getObjectName(i, j), objectArea);
                } else if (map.getObjectType(i,j) == "player"){
                    playerRectangleMap.put(map.getObjectName(i, j), objectArea);
                }
            }
        }
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
    public Set<String> getNpcNameSet() {
        return npcNameSet;
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
    public Map<String, Rectangle2D.Double> getObjectRectangles() {
        return objectRectangleMap;
    }

    @Override
    public Map<String, Rectangle2D.Double> getNpcRectangleMap() {
        return npcRectangleMap;
    }

    @Override
    public Map<String, Rectangle2D.Double> getObjectRectangleMap() {
        return objectRectangleMap;
    }
    @Override
    public Set<String> getObjectNameSet() {
        return objectNameSet;
    }

}
