/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package construction;

import java.awt.Point;

import org.lwjgl.Sys;
import org.newdawn.slick.tiled.TiledMap;

import model.IMap;

/**
 *
 * @author Ivar
 */
public class SlickMap implements IMap{
	private final TiledMap map;
	private final int collisionIndex;
	
    public SlickMap(TiledMap map){
        this.map = map;
        collisionIndex = map.getLayerIndex("collision");
    }
    
    public boolean isColliding(Point point) {
    	try {
    		return (map.getTileId((int)point.getX() / map.getTileWidth(), (int)point.getY() / map.getTileHeight(), collisionIndex) != 0);
    	}catch(IndexOutOfBoundsException e){
    		return false;
    	}
    }

    public int getHeight() {
    	
        return map.getHeight()*map.getTileHeight();
    }

    public int getWidth() {
        return map.getWidth()*map.getTileWidth();
    }
    
}
