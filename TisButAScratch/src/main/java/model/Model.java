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
	PlayerCommand command;
	
	public Model(){
		//TODO init map
		room = new Room();
		command = PlayerCommand.NONE;
	}
	
	public void update () {
		room.update(command);
		clearQueuedInput();
	}
	
	public void queuePlayerInput(PlayerCommand command) {
		this.command = command;
	}
	
	private void clearQueuedInput() {
		command = PlayerCommand.NONE;
	}
	
	public Point getPlayerPosition() {
		return room.getPlayerPosition();
	}
}