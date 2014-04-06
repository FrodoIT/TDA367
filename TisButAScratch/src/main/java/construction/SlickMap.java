/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package construction;

import java.awt.Point;

import org.newdawn.slick.tiled.TiledMap;

import model.IMap;

/**
 *
 * @author Ivar
 */
public class SlickMap implements IMap{
	private final TiledMap map;
	
    public SlickMap(TiledMap map){
        this.map = map;
    }
    
    public boolean isColliding(Point point) {
        return false;
    }

    public int getHeight() {
        return map.getHeight()*map.getTileHeight();
    }

    public int getWidth() {
        return map.getWidth()*map.getTileWidth();
    }
    
}
