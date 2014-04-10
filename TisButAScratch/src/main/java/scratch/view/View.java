package scratch.view;

import java.util.*;

import scratch.model.Model;
import scratch.model.Room;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.tiled.TiledMap;

/**
 * 
 * @author André Samuelsson
 * 
 */

public class View {
	
	private Model model;
	private RoomView roomView;
	
	private List<PlayerView> playerViewList = new ArrayList<PlayerView>();
        private Map<Integer, NpcView> npcViews;
	
	public View(Model model) {
		this.model = model;
                npcViews = new TreeMap<Integer, NpcView>();
	}
	
	public void render(GameContainer c, Graphics g) {
		roomView.render(c, g);
		
		for (PlayerView playerView : playerViewList) {
			playerView.render(c, g);
		}
	}
	
        public void addRoomView(Room room, TiledMap map){
            roomView = new RoomView(room, map, npcViews);
        }
        
	//TODO rename this method accordingly
	public void setRoomView(RoomView roomView) {
		this.roomView = roomView; 
	}
	
	public void addPlayerView(PlayerView playerView) {
		playerViewList.add(playerView);
	}
        
        public void addNpcView(int id, String imagePath){
                npcViews.put(id, new NpcView(imagePath));
        }
	
	public void removePLayerView(PlayerView playerView) {
		playerViewList.remove(playerView);
	}
}
