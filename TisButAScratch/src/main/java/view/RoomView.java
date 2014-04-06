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
        private Map npcIDs;
	
	public RoomView(Room room, TiledMap map) {
		this.room = room;
		this.map = map;
                npcIDs = new HashMap<Integer, NpcView>();
                npcIDs.put(0, new NpcView(null));
	}
        
	public void render(GameContainer c, Graphics g) {
		map.render(0, 0);
                
                for(INpc npc : room.getNpcs()){
                    if(npcIDs.containsKey(npc.getType())){
                        ((NpcView)npcIDs.get(npc.getType())).render(npc.getPosition(), g);
                    }
                }
                
                
	}

}
