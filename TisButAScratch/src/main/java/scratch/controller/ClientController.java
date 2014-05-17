package scratch.controller;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;
import scratch.construction.RoomFactory;
import scratch.construction.TiledMapPlus;
import scratch.model.GameCharacter;
import scratch.model.Room;
import scratch.network.NetworkClient;
import scratch.view.RoomView;

import java.util.ArrayList;
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
public final class ClientController extends Listener {

    private final List<CharacterController> characterControllerList;
    private final Map<Integer, RoomController> roomControllerMap;
    private final NetworkClient client;
    private int id;
    private int roomId;

    public ClientController(String ip) {
        super();
        characterControllerList = new ArrayList<>();
        roomControllerMap = new HashMap<>();
        client = new NetworkClient(ip);
    }

    public void init(GameContainer gameContainer) throws SlickException {
        //Send this class to be set as listener for the connection

        final RoomFactory roomFactory = new RoomFactory();

        for (final Room room : roomFactory.getRooms()) {
            final TiledMap map = (TiledMapPlus) room.getMap();
            RoomController roomController = new RoomController(room, new RoomView(map));
            roomControllerMap.put(roomController.getId(), roomController);

            for (final GameCharacter character : room.getCharacters()) {
                characterControllerList.add(new CharacterController(character));
            }
        }
        client.start(this);
    }

    public void characterRecieved(GameCharacter character) {
        boolean found = false;
        for (final CharacterController characterController : characterControllerList) {
            if (characterController.getId() == character.getId()) {
                characterController.setCharacter(character);
                found = true;
            }
        }
        if (!found) {
            characterControllerList.add(new CharacterController(character));
        }
    }

    public void update(GameContainer container, int delta) {
        client.send(new PacketPlayerInput(id, container.getInput()));
    }

    public synchronized void render(GameContainer gameContainer, Graphics graphics) throws SlickException {
        if (roomId != 0){
            roomControllerMap.get(roomId).render(gameContainer);
        }
        
    }

    @Override
    public synchronized void received(Connection connection, Object object) {
        if (object instanceof GameCharacter) {
            characterRecieved((GameCharacter) object);
        } else if (object instanceof PacketNewPlayer) {
            PacketNewPlayer info = (PacketNewPlayer) object;
            this.id = info.getId();
            this.roomId = info.getRoomId();
        }
    }
}
