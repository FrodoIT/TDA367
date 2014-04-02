package view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.Model;
import model.Room;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

/**
 * 
 * @author André Samuelsson
 * 
 */

public class View {
	
	private Model model;
//	private Map<Room, RoomView> roomMap = new HashMap<Room, RoomView>();
	private RoomView roomView;
	
	private List<PlayerView> playerViewList = new ArrayList<PlayerView>();
	
	public View(Model model) {
		this.model = model;
	}
	
	public void render(GameContainer c, Graphics g) {
//		roomMap.get(model.getActiveRoom()).render(c, g);
		roomView.render(c, g);
		
		for (PlayerView playerView : playerViewList) {
			playerView.render(c, g);
		}
	}
	
	//TODO rename this method accordingly
	public void setRoomView(RoomView roomView) {
		this.roomView = roomView; 
	}
	
	public void addPlayerView(PlayerView playerView) {
		playerViewList.add(playerView);
	}
	
	public void removePLayerView(PlayerView playerView) {
		playerViewList.remove(playerView);
	}
}
