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
    private List<Room> rooms;

    public Game() {
        playerList = new ArrayList<>();
    }

    public boolean addPlayer(GameCharacter player) {
        if (rooms == null || rooms.isEmpty()) {
            return false;
        }
        playerList.add(player);
        return true;
    }

    public List<GameCharacter> getPlayers() {
        return playerList;
    }

    public boolean removePlayer(GameCharacter player) {
        return playerList.remove(player);
    }

    public void setMap(List<Room> gameMapRooms) {
        this.rooms = gameMapRooms;
    }

    public List<Room> getRooms() {
        return rooms;
    }
}
