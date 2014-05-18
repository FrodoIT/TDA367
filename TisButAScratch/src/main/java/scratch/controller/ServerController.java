package scratch.controller;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;
import scratch.construction.MyMatcher;
import scratch.construction.RoomFactory;
import scratch.construction.TiledMapPlus;
import scratch.model.*;
import scratch.network.NetworkServer;
import scratch.view.RoomView;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import scratch.network.PacketNewPlayer;
import scratch.network.PacketPlayerInput;

/**
 * The main controller class to control updates, rendering, initiating and
 * delegate tasks to other controllers.
 *
 * @author Anna Nylander
 *
 */
public final class ServerController extends Listener{

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
        //TODO: This will need to change when we read from XML.
        final RoomFactory roomFactory = new RoomFactory();
        final List<Room> rooms = roomFactory.getRooms();
        game.setMap(rooms);

        for (final Room room : rooms) {
            final TiledMapPlus map = (TiledMapPlus) room.getMap();
            RoomController roomController = new RoomController(room, new RoomView(map));
            roomControllerMap.put(roomController.getId(), roomController);

            for (final GameCharacter character : room.getCharacters()) {
                CharacterController characterController = new CharacterController(character);
                characterController.addListener(networkServer);
                roomController.addCharacterController(characterController);
            }
        }

        networkServer.start(this);

    }

    private synchronized GameCharacter loadPlayer(String file, Vector2D position, int id) {
        final Serializer serializer = new Persister(new MyMatcher());
        final StringBuilder fileBuild = new StringBuilder();
        fileBuild.append("res/");
        fileBuild.append(file);
        fileBuild.append(".xml");
        final File source = new File(fileBuild.toString());
        GameCharacter player;
        try {
            player = serializer.read(GameCharacter.class, source);

        } catch (Exception e) {
            System.out.println(e.toString());
            return null;
        }
        player.setPosition(position);
        player.setId(id);
        return player;
    }

    
    public synchronized void update(GameContainer container, int delta) throws SlickException {
        for (final RoomController roomController : roomControllerMap.values()) {
            roomController.updateRoom();
        }
    }

    @Override
    public synchronized void connected(Connection connection) {
        int roomId = 100;
        GameCharacter newPlayer = loadPlayer("StandardPlayer", new Vector2D(20, 20), nextPlayerId);
        game.addPlayer(newPlayer);
        System.out.println("We started a Player with reference: " + newPlayer);
        CharacterController playerController = new CharacterController(newPlayer);
        playerController.addListener(networkServer);
        roomControllerMap.get(roomId).addCharacter(playerController);
        connection.sendTCP(new PacketNewPlayer(nextPlayerId, roomId));
        nextPlayerId++;
    }

    @Override
    public synchronized void received(Connection connection, Object object) {
        if (object instanceof PacketPlayerInput){
            GameCharacter player = game.getPlayers().get(0);
            PacketPlayerInput input = (PacketPlayerInput)object;
            player.setNextMoveDirection(input.getMovementDirection());
            player.setAttacking(input.getAttacking());
            player.setInteracting(input.getInteracting());
        }
    }
}
