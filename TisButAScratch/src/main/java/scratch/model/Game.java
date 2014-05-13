package scratch.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Alma Ottedag
 *
 */
public class Game implements Serializable {

    private List<Player> playerList;
    private List<NpcType> npcList;
    private Room activeRoom;

    public Game() {
        npcList = new ArrayList<NpcType>();
        playerList = new ArrayList<Player>();
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

    public void addNpcs() {
        for (Map.Entry<Integer, NpcType> npcTypeEntry : activeRoom.getNpcs().entrySet()) {
            this.npcList.add(npcTypeEntry.getValue());
        }
    }

    public List<Player> getPlayers() {
        return playerList;
    }

    public boolean removePlayer(Player player) {
        return playerList.remove(player)
                && activeRoom.exitRoom(player);
    }

    public void setMap(List<Room> gameMapRooms) {
		//TODO: will need to be further implemented later.
        //set the active room. Currently there should only be one.
        activeRoom = gameMapRooms.get(0);
    }

    public Room getActiveRoom() {
        return activeRoom;
    }

}
