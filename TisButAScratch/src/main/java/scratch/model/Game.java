package scratch.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Alma Ottedag
 *
 */
public class Game {

    private List<GameCharacter> playerList;
    private List<Room> activeRooms;

    public Game() {
        playerList = new ArrayList<>();
    }

    public boolean addPlayer(GameCharacter player) {
        if (activeRooms == null || activeRooms.isEmpty()) {
            return false;
        }
        playerList.add(player);
        return true;
    }

    public List<GameCharacter> getPlayers() {
        return playerList;
    }

    public boolean removePlayer(GameCharacter player) {
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
