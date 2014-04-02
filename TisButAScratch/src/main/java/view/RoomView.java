package view;

import model.Room;

import org.newdawn.slick.AppletGameContainer.Container;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.tiled.TiledMap;

/**
 * 
 * @author André Samuelsson
 *
 */

public class RoomView {
	//variables
	private Room room;
	TiledMap map;
	
	public RoomView(Room room, TiledMap map) {
		this.room = room;
		this.map = map;
	}
	
	public void render(Container c, Graphics g) {
		map.render(0, 0);
	}

}
