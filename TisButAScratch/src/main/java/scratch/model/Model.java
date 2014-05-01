package scratch.model;

import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;

import java.awt.Rectangle;
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
	
	public Player addPlayer(IPlayerInput input){
            if (activeRoom != null){
		Player newPlayer = new Player(input, new Rectangle2D.Double(0, 0, 32, 32), playerList.size());
		playerList.add(newPlayer);
		activeRoom.enterRoom(newPlayer);
		return newPlayer;
            }
            return null;
	}
	public void removePlayer(Player player){
		playerList.remove(player);
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
	}
}
