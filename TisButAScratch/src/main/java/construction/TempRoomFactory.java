package construction;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

import model.Room;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

/**
 * This class is only temporaray so we can load a map into the game.
 * @author André Samuelsson
 *
 */
public class TempRoomFactory {
	
	private TiledMap map;
	private Room room;
	
	public TempRoomFactory() {
		try {
			map = new TiledMap("res/untitled.tmx");
		} catch (SlickException e) {
			e.printStackTrace();
		}
		
		int collisionLayerIndex = map.getLayerIndex("collision");

		int[][] mapRepresentation = new int[ map.getWidth() ][ map.getHeight() ];

		for (int x = 0 ; x < map.getWidth() ; x++ ) {
			for (int y = 0 ; y < map.getHeight() ; y++ ) {
				mapRepresentation[x][y] = map.getTileId(x, y, collisionLayerIndex);
			}
		}
		
		this.room = new Room(new SlickMap());
	}
	
	public TiledMap getTiledMap() {
		
		return map;
	}
        
        	
	public List<Room> getRooms() {
		
		List<Room> rooms = new ArrayList<Room>();
		rooms.add(room);
		return rooms;
	}
	
	public Room getRoom() {
		
		return room;
	}
}
