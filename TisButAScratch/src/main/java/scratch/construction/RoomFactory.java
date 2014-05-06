package scratch.construction;

import java.util.ArrayList;
import java.util.List;

import scratch.model.Room;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

/**
 * This class is only temporaray so we can load a map into the game.
 * @author André Samuelsson
 *
 */
public final class RoomFactory {
	
	private TiledMap map;
	private final Room room;
	
	public RoomFactory() {
		try {
			map = new TiledMap("res/untitled.tmx");
		} catch (SlickException e) {
			e.printStackTrace();
		}
		
		this.room = new Room(new SlickMap(map));
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
