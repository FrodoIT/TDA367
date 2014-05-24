package scratch.controller;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import scratch.construction.RoomFactory;
import scratch.model.Room;
import scratch.network.*;

import java.util.HashMap;
import java.util.Map;

/**
 * The main controller class to control updates, rendering, initiating and
 * delegate tasks to other controllers.
 *
 * @author Anna Nylander
 *
 */
public final class ClientController extends Listener {

    private final Map<Integer, RoomController> roomControllerMap;
    private final NetworkClient networkClient;
    private UIController uiController;
    private int id;
    private int roomId;

    public ClientController(String ip) {
        super();
        roomControllerMap = new HashMap<>();
        networkClient = new NetworkClient(ip);
    }

    public void init(GameContainer gameContainer) throws SlickException {
        //Send this class to be set as listener for the connection
        initRooms(new RoomFactory());
        networkClient.start(this);
    }

    private void initRooms(RoomFactory roomFactory) {
        for (final Room room : roomFactory.getRooms()) {
            final RoomController roomController = new RoomController(room);
            roomControllerMap.put(roomController.getId(), roomController);
            roomController.setClient(networkClient);
        }
    }
    
    public void update(GameContainer container, int delta) {
        networkClient.send(new PacketPlayerInput(getId(), container.getInput()));
    }

    public void render(GameContainer gameContainer, Graphics graphics) throws SlickException {
        if (roomId != 0) {
            roomControllerMap.get(roomId).render(gameContainer);
            if (uiController != null) { //if uicontroller has been added yet, show playerstats.
                uiController.render(gameContainer);
            }
        }
    }

    @Override
    public void received(Connection connection, Object object) {
        if (object.getClass() == PacketMoveCharacter.class) {
            final PacketMoveCharacter info = (PacketMoveCharacter) object;
            roomControllerMap.get(info.getFromRoomId()).moveCharacter(
                    info.getCharacterId(),
                    roomControllerMap.get(info.getToRoomId()));

            if (info.getCharacterId() == getId()) {
                roomId = info.getToRoomId();
            }
        } else if (object.getClass() == PacketNewConnection.class) {
            final PacketNewConnection info = (PacketNewConnection) object;
            setId(info.getId());
            this.roomId = info.getRoomId();
        } else if (object.getClass() == PacketNewCharacter.class) {
            final PacketNewCharacter packet = (PacketNewCharacter) object;
            final CharacterController characterController = new CharacterController(packet.getCharacter());
            networkClient.addListener(characterController);
            roomControllerMap.get(roomId).addCharacter(characterController);
            if (characterController.getId() == getId()){
                uiController = new UIController(characterController.getCharacter());
            }
        }
    }
    
    private synchronized void setId(int id){
        this.id = id;
    }
    
    private synchronized int getId() {
        return id;
    }
}
