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
    //Object map holds the object group index and object index
    //as well as the name of the object.
    private Map<String, List<Integer>> objectMap;
    private Set<String> objectNameSet;
    @Inject
    public SlickMap(TiledMap map){
        this.map = map;

        //A wrap-around for the confusing and dysfunctional TiledMap API.
        objectMap = new TreeMap<>();
        int objectGroupIndex = map.getObjectGroupCount();
        for(int i = 0; i < objectGroupIndex; i ++){//
            int objectIndex = map.getObjectCount(i);
            for(int j = 0; j < objectIndex; j++){
                //System.out.println(map.getObjectName(i,j) + "   " + i + " object group index " + j + " object index" );
                List<Integer> objectID= new ArrayList<Integer>();
                objectID.add(objectGroupIndex);
                objectID.add(objectIndex);
                objectMap.put(map.getObjectName(i, j), objectID);
            }
        }
        objectNameSet = objectMap.keySet();
        collisionIndex = map.getLayerIndex("collision");
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
