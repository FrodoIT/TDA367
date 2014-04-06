package view;

import java.util.*;

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
	private RoomView roomView;
	
	private List<PlayerView> playerViewList = new ArrayList<PlayerView>();
    private Map<Integer, String> npcViews;
	
	public View(Model model) {
		this.model = model;
        npcViews = new TreeMap<Integer, String>();
	}
	
	public void render(GameContainer c, Graphics g) {
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
    public void addNpcView(int id, String imagePath){

    }
	
	public void removePLayerView(PlayerView playerView) {
		playerViewList.remove(playerView);
	}
}
