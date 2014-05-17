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

	    //NOTE: The player created here wont have a room as listeners. this is added when the player
        //enters the first room. (game.addPlayer(newPlayer); enters a player to the first room)
        Player newPlayer = loadPlayer("StandardPlayer", new Vector2D(20, 20), 1, gameContainer);
        System.out.println(newPlayer.toString());
        game.addPlayer(newPlayer);

        for (final Room room : rooms) {
            final TiledMap map = (TiledMapPlus)room.getMap();
            //TODO refactor here. SlickMap and TiledMapPlus will be merged probably. Tejp fix
            RoomController roomController = new RoomController(room, new RoomView(map));
            roomControllerList.add(roomController);

            for (final AbstractCharacter character : room.getCharacters()) {
                CharacterController characterController = new CharacterController(character);
                characterController.addListener(networkServer);
                characterControllerList.add(characterController);
            }
        }

        networkServer.start(this);

    }

    private Player loadPlayer(String file, Vector2D position, int id, GameContainer gameContainer) {
        final Serializer serializer = new Persister(new MyMatcher());
        final StringBuilder fileBuild = new StringBuilder();
        fileBuild.append("res/");
        fileBuild.append(file);
        fileBuild.append(".xml");
        final File source = new File(fileBuild.toString());
        Player player;
        try {
            player = serializer.read(Player.class, source);

        } catch (Exception e) {
            System.out.println(e.toString());
            return null;
        }
        player.setPosition(position);
        player.setId(id);
        player.setPlayerInput(new PlayerInput(id, gameContainer.getInput()));
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
        connection.sendTCP(new PacketNewPlayer(nextPlayerId));
        nextPlayerId++;
    }

    @Override
    public void received(Connection connection, Object object) {
        //TODO We recieved something
        if (object instanceof PacketPlayerInput){
            Player player = game.getPlayers().get(0);
            PacketPlayerInput input = (PacketPlayerInput)object;
            player.setMoveDirection(input.getMovementDirection());
            player.setAttacking(input.getAttacking());
            player.setInteracting(input.getInteracting());
        }
    }
}
