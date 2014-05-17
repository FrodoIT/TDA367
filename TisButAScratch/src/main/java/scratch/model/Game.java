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
        activeRoom.addCharacter(player);
        return true;
    }

    public List<Player> getPlayers() {
        return playerList;
    }

    public List<AbstractCharacter> getCharacters() {
        return activeRoom.getCharacters();
    }

    public boolean removePlayer(Player player) {
        return playerList.remove(player)
                && activeRoom.removeCharacter(player);
    }

    public void setMap(List<Room> gameMapRooms) {
        this.rooms = gameMapRooms;
        activeRoom = gameMapRooms.get(0);
        //TODO if index 0 isn't the spawn location then this is wrong.
        //maybe the spawn room should be searched for
    }

    public Room getActiveRoom() {
        return activeRoom;
    }
}
