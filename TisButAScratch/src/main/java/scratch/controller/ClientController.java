package scratch.controller;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;
import scratch.construction.RoomFactory;
import scratch.construction.SlickMap;
import scratch.model.AbstractCharacter;
import scratch.model.NpcType;
import scratch.model.Player;
import scratch.model.Room;
import scratch.network.NetworkClient;
import scratch.view.CharacterView;
import scratch.view.RoomView;

import java.util.ArrayList;
import java.util.List;

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
            final TiledMap map = ((SlickMap) room.getMap()).getMap();
            RoomController roomController = new RoomController(room, new RoomView(map));
            roomControllerList.add(roomController);

            for (final NpcType npc : room.getNpcs()) {
                characterControllerList.add(new CharacterController(npc));
            }
            for (final Player player : room.getPlayers()) {
                characterControllerList.add(new CharacterController(player));
            }
        }
        client.start(this);
    }

    public void characterRecieved(AbstractCharacter character) {
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
        client.send(new PlayerInput(1, container.getInput()));
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
        if (object instanceof AbstractCharacter) {
            characterRecieved((AbstractCharacter) object);
        }
    }
}
