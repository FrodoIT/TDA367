package scratch.view;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.tiled.TiledMap;
import scratch.model.NpcType;
import scratch.model.Room;

import java.util.Map;

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
                
                
                for(Map.Entry<Integer, NpcType> npcEntry : room.getNpcs().entrySet()){
                    
                    int id = npcEntry.getValue().getId();
                    if(npcViews.containsKey(id)){
                        npcViews.get(id).render(npcEntry.getValue(), g);
                    } else {
                        npcViews.get(0).render(npcEntry.getValue(), g);
                    }
                }
	}

}
