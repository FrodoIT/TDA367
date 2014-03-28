package model;

import java.awt.Point;
 
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