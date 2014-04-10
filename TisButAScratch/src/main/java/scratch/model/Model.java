package scratch.model;

import java.util.ArrayList;
import java.util.List;

import java.awt.Rectangle;
/**
 * 
 * @author Alma Ottedag
 *
 */
public class Model {
	
	private List<Room> roomList = new ArrayList<Room>();
	private List<Player> playerList = new ArrayList<Player>();
	private Room activeRoom;
	
	public Model(){
	}
	
	public Player addPlayer(IPlayerInput input){
		Player newPlayer = new Player(input, new Rectangle(0, 0, 32, 32), playerList.size());
		playerList.add(newPlayer);
		activeRoom.enterRoom(newPlayer);
		return newPlayer;
	}
	public void removePlayer(Player player){
		playerList.remove(player);
		activeRoom.exitRoom(player);
	}
	public void setMap(List<Room> gameMapRooms){
		//TODO: will need to be further implemented later.
		//set the active room. Currently there should only be one.
		roomList = gameMapRooms;
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
