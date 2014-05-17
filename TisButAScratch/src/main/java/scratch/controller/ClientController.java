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
public final class ClientController extends Listener {

    private final List<CharacterController> characterControllerList;
    private final List<RoomController> roomControllerList;
    private final NetworkClient client;
    private int id;

    public ClientController(String ip) {
        super();
        characterControllerList = new ArrayList<>();
        roomControllerList = new ArrayList<>();
        client = new NetworkClient(ip);
    }

    public void init(GameContainer gameContainer) throws SlickException {
        //Send this class to be set as listener for the connection

        final RoomFactory roomFactory = new RoomFactory();

        for (final Room room : roomFactory.getRooms()) {
            final TiledMap map = (TiledMapPlus)room.getMap();
            RoomController roomController = new RoomController(room, new RoomView(map));
            roomControllerList.add(roomController);

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

    public void render(GameContainer gameContainer, Graphics graphics) throws SlickException {
        for (final RoomController roomController : roomControllerList) {
            roomController.render();
        }
        
        for (final CharacterController characterController : characterControllerList){
            characterController.render(gameContainer);
        }

    }

    @Override
    public void received(Connection connection, Object object) {
        if (object instanceof GameCharacter) {
            characterRecieved((GameCharacter) object);
        } else if (object instanceof PacketNewPlayer){
            this.id = ((PacketNewPlayer)object).getId();
        }
    }
}
