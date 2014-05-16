package scratch.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Alma Ottedag
 *
 */
public class Game {

    private List<Player> playerList;
	private List<Room> rooms;
    private Room activeRoom;

    public Game() {
        playerList = new ArrayList<>();
        activeRoom = null;

    }

    public boolean addPlayer(Player player) {
        if (activeRoom == null) {
            return false;
        }
        playerList.add(player);
        activeRoom.enterRoom(player);
        return true;
    }

    //TODO bad method. either delete it or loop through all rooms and return all npcs in the game //Tejp
    public List<NpcType> getNpcs() {
        return activeRoom.getNpcs();
    }

    public List<Player> getPlayers() {
        return playerList;
    }
    
    public List<AbstractCharacter> getCharacters(){
        List <AbstractCharacter> characters = new ArrayList<>();
        //TODO: This needs to change with several rooms
        for (Player player:activeRoom.getPlayers()){
            characters.add((AbstractCharacter)player);
        }
        for (NpcType npc:activeRoom.getNpcs()){
            characters.add((AbstractCharacter)npc);
        }
        return characters;
    }

    public boolean removePlayer(Player player) {
        return playerList.remove(player)
                && activeRoom.exitRoom(player);
    }

    public void setMap(List<Room> gameMapRooms) {
	    this.rooms= gameMapRooms;
        activeRoom = gameMapRooms.get(0);
        //TODO if index 0 isn't the spawn location then this is wrong.
	    //maybe the spawn room should be searched for
    }

    public Room getActiveRoom() {
        return activeRoom;
    }
}
