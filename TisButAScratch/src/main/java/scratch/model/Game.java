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
    private List<Room> activeRooms;

    public Game() {
        playerList = new ArrayList<>();
    }

    public boolean addPlayer(Player player) {
        playerList.add(player);
        activeRooms.get(0).addCharacter(player);
        return true;
    }

    public List<Player> getPlayers() {
        return playerList;
    }

    public boolean removePlayer(Player player) {
        for (Room activeRoom : activeRooms) {
            if (activeRoom.removeCharacter(player)) {
                return playerList.remove(player);
            }
        }
        return false;
    }

    public void setMap(List<Room> gameMapRooms) {
        this.activeRooms = gameMapRooms;
    }

    public List<Room> getActiveRooms() {
        return activeRooms;
    }
}
