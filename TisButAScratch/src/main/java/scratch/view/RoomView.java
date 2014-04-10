package scratch.view;

import java.util.Map;
import scratch.model.INpc;
import scratch.model.Room;

import org.newdawn.slick.GameContainer;
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
	private TiledMap map;
        private Map<Integer, NpcView> npcViews;
	
	public RoomView(Room room, TiledMap map, Map<Integer, NpcView> npcViews) {
		this.room = room;
		this.map = map;
                this.npcViews = npcViews;
	}
        
	public void render(GameContainer c, Graphics g) {
		map.render(0, 0);
                
                for(INpc npc : room.getNpcs()){
                    int id = npc.getID();
                    if(npcViews.containsKey(id)){
                        npcViews.get(id).render(npc, g);
                    }
                }
	}

}
