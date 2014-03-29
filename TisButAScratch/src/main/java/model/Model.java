package model;

import java.awt.Point;
import java.util.List;
 
/**
 * Contains the internal representation of the game.
 * @author Ivar Cannonbait Josefsson
 *
 */
public class Model {
	
	Room room;
	PlayerInput input;
	
	public Model(){
		//TODO init map
		room = new Room();
		input = new PlayerInput();
	}
	
	public void update () {
		room.update(input);
	}
	
	public void queuePlayerInput(PlayerInput input) {
		this.input = input;
	}
	
	public Point getPlayerPosition() {
		return room.getPlayerPosition();
	}
}