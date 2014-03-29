package controller;
import java.awt.Dimension;

import model.Model;
import model.Room;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

import view.RoomView;
import view.View;

/**
 * 
 * @author André
 *
 */
public class RoomController {

	private RoomView roomView;
	private TiledMap map = null;
	
	/**
	 * 
	 * @param res: the relative linking to the file. usually res/<filename>
	 */
	public RoomController(Model model, View view , String res) {
		try {
			map = new TiledMap(res);
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
		
		roomView = new RoomView(map);
		model.addRoom(
				new Room(
						mapRepresentation,
						new Dimension(map.getWidth(), map.getHeight() ),
						new Dimension(map.getTileWidth(), map.getTileHeight() )
						)
				);
	}
	
	/**
	 * call this to draw the room
	 */
	public void render() {
		roomView.draw(0, 0);
	}
	
}
