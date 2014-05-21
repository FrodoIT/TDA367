package scratch.controller;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import scratch.construction.RoomFactory;
import scratch.construction.TiledMapPlus;
import scratch.model.GameCharacter;
import scratch.model.IInteractiveObject;
import scratch.model.InteractiveObject;
import scratch.model.Room;
import scratch.network.*;
import scratch.view.RoomView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    private UIController uiController;
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
        initRooms(roomFactory);
        client.start(this);
    }

    private void initRooms(RoomFactory roomFactory) {
        for (final Room room : roomFactory.getRooms()) {
            final TiledMapPlus map = (TiledMapPlus) room.getMap();
            RoomController roomController = new RoomController(room, new RoomView(map, room));
            roomControllerMap.put(roomController.getId(), roomController);
            initGameCharacters(room, roomController);
            initInteractiveObjects(room, roomController);
        }
    }

    private void initInteractiveObjects(Room room, RoomController roomController) {
        for (final IInteractiveObject interactiveObject : room.getInteractiveObjects()) {
            InteractiveObjectController interactiveObjectController = new InteractiveObjectController(interactiveObject);
            roomController.addInteractiveObjectController(interactiveObjectController);
            client.addListener(interactiveObjectController);
        }
    }

    private void initGameCharacters(Room room, RoomController roomController) {
        for (final GameCharacter character : room.getCharacters()) {
            CharacterController characterController = new CharacterController(character);
            roomController.addCharacterController(characterController);
            client.addListener(characterController);
        }
    }

    public void update(GameContainer container, int delta) {
        client.send(new PacketPlayerInput(id, container.getInput()));
    }

    public synchronized void render(GameContainer gameContainer, Graphics graphics) throws SlickException {
        if (roomId != 0) {
            roomControllerMap.get(roomId).render(gameContainer);
            if (uiController != null) { //if uicontroller has been added yet, show playerstats.
                uiController.render(gameContainer);
            }
        }

    }

    @Override
    public synchronized void received(Connection connection, Object object) {
        if (object instanceof PacketMoveCharacter) {
            PacketMoveCharacter info = (PacketMoveCharacter) object;
            roomControllerMap.get(info.getFromRoomId()).moveCharacter(
                    info.getCharacterId(),
                    roomControllerMap.get(info.getToRoomId()));

            if (info.getCharacterId() == id) {
                roomId = info.getToRoomId();
            }
        }

        if (object instanceof PacketNewConnection) {

            final PacketNewConnection info = (PacketNewConnection) object;
            this.id = info.getId();
            this.roomId = info.getRoomId();

        } else if (object instanceof PacketNewCharacter) {
            PacketNewCharacter packet = (PacketNewCharacter) object;
            CharacterController characterController = new CharacterController(packet.getCharacter());
            client.addListener(characterController);
            roomControllerMap.get(roomId).addCharacter(characterController);
            if (characterController.getId() == id){
                uiController = new UIController(characterController.getCharacter());
            }
        }
    }
}
