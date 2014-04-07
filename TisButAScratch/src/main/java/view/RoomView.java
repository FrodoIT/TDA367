package view;

import java.util.HashMap;
import java.util.Map;
import model.INpc;
import model.Room;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.tiled.TiledMap;

/**
 * 
 * @author André Samuelsson
 *
 */

public class RoomView {
	//variables
	private Room room;
	private TiledMap map;
        private Map npcViews;
	
	public RoomView(Room room, TiledMap map, Map<Integer, NpcView> npcViews) {
		this.room = room;
		this.map = map;
                this.npcViews = npcViews;
	}
        
	public void render(GameContainer c, Graphics g) {
		map.render(0, 0);
                for(INpc npc : room.getNpcs()){
                    if(npcViews.containsKey(npc.getID())){
                        ((NpcView)npcViews.get(npc.getID())).render(npc.getPosition(), g);
                    }
                }
	}

}
