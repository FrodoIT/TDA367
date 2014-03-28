package model;

import java.awt.Point;
 
/**
 * Contains the internal representation of the game.
 * @author Ivar Cannonbait Josefsson
 *
 */
public class Model {
	
	Room room;
	
	public Model(){
		//TODO init map
		room = new Room();
	}
	
	public void update () {
		
	}
	
	public Point getPlayerPosition() {
		return room.getPlayerPosition();
	}
}