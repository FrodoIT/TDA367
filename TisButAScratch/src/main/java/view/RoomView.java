package view;

import org.newdawn.slick.Renderable;
import org.newdawn.slick.tiled.TiledMap;

/**
 * 
 * @author André
 *
 */
public class RoomView implements Renderable {
	
	TiledMap tiledMap;
	
	public RoomView(TiledMap tiledMap) {
		this.tiledMap = tiledMap;
	}

	@Override
	public void draw(float x, float y) {
		tiledMap.render(0, 0);
	}
	
}
