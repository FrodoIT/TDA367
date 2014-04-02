package controller;

import java.awt.Dimension;

import model.Room;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

public class TempRoomFactory {

	private Room room;
	private TiledMap map;
	
	public TempRoomFactory() {
		try {
			map = new TiledMap("res/spawn.tmx");
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
		
		room = new Room(mapRepresentation, new Dimension(map.getWidth(), map.getHeight()));
	}
	
	
	public Room getRoom() {
		
		return room;
	}
	
	public TiledMap getTiledMap() {
		return map;
	}
}
