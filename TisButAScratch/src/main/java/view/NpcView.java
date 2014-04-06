/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package view;

import java.awt.Point;
import model.INpc;
import org.newdawn.slick.Animation;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.tiled.TiledMap;

/**
 *
 * @author Ivar
 */
public class NpcView{
    private TiledMap tiledMap;
	
	private Image [] movementNorth = new Image[3];
	private Image [] movementSouth = new Image[3];
	private Image [] movementWest = new Image[3];
	private Image [] movementEast = new Image[3];
	private int [] duration = {100, 100, 100};
	private Animation sprite, north, south, west, east;
        
    public NpcView(TiledMap tiledMap){
        
    }
    
    public void render(Point pos, Graphics g){
        g.draw(new Circle((float)pos.getX(), (float)pos.getY(), 13));

    }
    
    
}
