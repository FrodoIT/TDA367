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
import java.util.ArrayList;
import java.util.List;
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
    private final List<CharacterController> characterControllerList;
    private final List<RoomController> roomControllerList;
    private int nextPlayerId;

    public ServerController(Game game) {
        super();
        this.game = game;
        networkServer = new NetworkServer();
        characterControllerList = new ArrayList<>();
        roomControllerList = new ArrayList<>();
        nextPlayerId = 1;
    }

    public void init(GameContainer gameContainer) throws SlickException {
        //TODO: This will need to change when we read from XML.
        final RoomFactory roomFactory = new RoomFactory();
        final List<Room> rooms = roomFactory.getRooms();
        game.setMap(rooms);

        for (final Room room : rooms) {
            final TiledMap map = (TiledMapPlus)room.getMap();
            //TODO refactor here. SlickMap and TiledMapPlus will be merged probably. Tejp fix
            RoomController roomController = new RoomController(room, new RoomView(map));
            roomControllerList.add(roomController);

            for (final GameCharacter character : room.getCharacters()) {
                CharacterController characterController = new CharacterController(character);
                characterController.addListener(networkServer);
                characterControllerList.add(characterController);
            }
        }

        networkServer.start(this);

    }

    private GameCharacter loadPlayer(String file, Vector2D position, int id) {
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

    
    public void update(GameContainer container, int delta) throws SlickException {
        for (final CharacterController characterController : characterControllerList) {
            characterController.update();
        }

        for (final RoomController roomController : roomControllerList) {
            roomController.updateRoom();
        }
    }

    @Override
    public void connected(Connection connection) {
        GameCharacter newPlayer = loadPlayer("StandardPlayer", new Vector2D(20, 20), nextPlayerId);
        game.addPlayer(newPlayer);
        characterControllerList.add(new CharacterController(newPlayer));
        connection.sendTCP(new PacketNewPlayer(nextPlayerId, 100));
        nextPlayerId++;
    }

    @Override
    public void received(Connection connection, Object object) {
        if (object instanceof PacketPlayerInput){
            GameCharacter player = game.getPlayers().get(0);
            PacketPlayerInput input = (PacketPlayerInput)object;
            player.setMoveDirection(input.getMovementDirection());
            player.setAttacking(input.getAttacking());
            player.setInteracting(input.getInteracting());
        }
    }
}
