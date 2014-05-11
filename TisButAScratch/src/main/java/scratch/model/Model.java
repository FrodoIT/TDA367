package scratch.model;

import java.util.ArrayList;
import java.util.List;
/**
 * 
 * @author Alma Ottedag
 *
 */
public class Model {
	
	private List<Player> playerList;
	private Room activeRoom;
	
	public Model(){
            playerList = new ArrayList<Player>();
            activeRoom = null;
            
	}
	
	public boolean addPlayer(Player player){
		if (activeRoom == null)
			return false;
		playerList.add(player);
		activeRoom.enterRoom(player);
		return true;
	}

	public List<Player> getPlayers() {
		return playerList;
	}

	public boolean removePlayer(Player player){
		return playerList.remove(player) &&
		activeRoom.exitRoom(player);
	}

	public void setMap(List<Room> gameMapRooms){
		//TODO: will need to be further implemented later.
		//set the active room. Currently there should only be one.
		activeRoom = gameMapRooms.get(0);
	}
	
	public Room getActiveRoom() {
		return activeRoom;
	}
	
	public void update(){
		//TODO: when co-op is implemented there will be more a list of active rooms. Implement.
		activeRoom.update();
        activeRoom.setUpdatingNpcs(false);
        activeRoom.setUpdatingPlayers(false);
	}
}
