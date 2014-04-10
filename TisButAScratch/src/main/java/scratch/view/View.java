package scratch.view;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.tiled.TiledMap;
import scratch.model.Player;
import scratch.model.Room;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 *
 * @author André Samuelsson
 *
 */

public class View {

	private List<RoomView> roomView = new ArrayList<RoomView>();

	private List<PlayerView> playerViewList = new ArrayList<PlayerView>();
	private Map<Integer, NpcView> npcViews = new TreeMap<Integer, NpcView>();;

	public View() {
	}

	public void render(GameContainer c, Graphics g) {
		for (RoomView view : roomView) {
			view.render(c, g);
		}

		for (PlayerView playerView : playerViewList) {
			playerView.render(c, g);
		}
	}

	public void addRoomView(Room room, TiledMap map){
		roomView.add(new RoomView(room, map, npcViews));
	}

	public void addPlayerView(Player player) {
		playerViewList.add(new PlayerView(player));
	}

	public void addNpcView(int id, String imagePath){
		npcViews.put(id, new NpcView(imagePath));
	}

	public void removePlayerView(PlayerView playerView) {
		playerViewList.remove(playerView);
	}
}
