package model;

import java.util.ArrayList;
import java.util.List;

import construction.MapInterface;
import java.awt.Rectangle;
/**
 * 
 * @author Alma Ottedag
 *
 */
public class Model {
	
	//variables
	private List<Room> roomList;
	private List<Player> playerList;
	private Room activeRoom;
	
	//constructor
	public Model(){
		roomList = new ArrayList<Room>();
		playerList = new ArrayList<Player>();
	}
	
	//methods
	public Player addPlayer(PlayerInput input){
		Player newPlayer = new Player(input, new Rectangle(0, 0, 32, 32));
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
