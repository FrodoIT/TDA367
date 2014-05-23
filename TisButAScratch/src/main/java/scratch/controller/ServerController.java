package scratch.controller;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import scratch.construction.LoadXMLObject;
import scratch.construction.RoomFactory;
import scratch.construction.TiledMapPlus;
import scratch.model.*;
import scratch.network.NetworkServer;
import scratch.network.PacketNewCharacter;
import scratch.network.PacketNewConnection;

/**
 * The main controller class to control updates, rendering, initiating and
 * delegate tasks to other controllers.
 *
 * @author Anna Nylander
 *
 */
public final class ServerController extends Listener {

    private final NetworkServer networkServer;
    private final Game game;
    private final Map<Integer, RoomController> roomControllerMap;
    private int nextPlayerId;

    public ServerController(Game game) {
        super();
        this.game = game;
        networkServer = new NetworkServer();
        roomControllerMap = new HashMap<>();
        nextPlayerId = 1;
    }

    public void init(GameContainer gameContainer) throws SlickException {
        final RoomFactory roomFactory = new RoomFactory();
        final List<Room> rooms = roomFactory.getRooms();
        game.setMap(rooms);
        roomFactory.getDoorHandler().addListener(new DoorController(networkServer, roomControllerMap));
        initRooms(rooms);
        networkServer.start(this);
    }

    private void initRooms(List<Room> rooms) {
        for (final Room room : rooms) {
            final TiledMapPlus map = (TiledMapPlus) room.getMap();
            RoomController roomController = new RoomController(room);
            roomController.setServer(networkServer);
            roomControllerMap.put(roomController.getId(), roomController);
        }
    }

    private synchronized GameCharacter loadPlayer(String file, Vector2D position, int id) {
        GameCharacter player = (GameCharacter) new LoadXMLObject().loadObject("GameCharacter", file);
        player.setPosition(position);
        player.setId(id);
        return player;
    }

    public synchronized void update(GameContainer container, int delta) throws SlickException {
        for (final RoomController roomController : roomControllerMap.values()) {
            roomController.updateRoom();
        }
    }

    private void addPlayer(Connection connection) {
        int roomId = 100;
        connection.sendTCP(new PacketNewConnection(nextPlayerId, roomId));
        GameCharacter newPlayer = loadPlayer("StandardPlayer", new Vector2D(200, 300), nextPlayerId);
        game.addPlayer(newPlayer);
        CharacterController playerController = new CharacterController(newPlayer);
        networkServer.addListener(playerController);
        playerController.setServer(networkServer);
        roomControllerMap.get(roomId).addCharacter(playerController);

        networkServer.sendTCP(new PacketNewCharacter(roomId, newPlayer));
        nextPlayerId++;
    }

    @Override
    public synchronized void connected(Connection connection) {
        addPlayer(connection);
    }
}
