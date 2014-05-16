package scratch.controller;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import scratch.construction.SlickMap;
import scratch.model.Player;
import scratch.network.NetworkClient;
import scratch.view.CharacterView;

import java.util.ArrayList;
import java.util.List;
import org.newdawn.slick.tiled.TiledMap;
import scratch.construction.RoomFactory;
import scratch.model.AbstractCharacter;
import scratch.model.Game;
import scratch.model.NpcType;
import scratch.model.Room;
import scratch.view.RoomView;

/**
 * The main controller class to control updates, rendering, initiating and
 * delegate tasks to other controllers.
 *
 * @author Anna Nylander
 *
 */
public final class ClientController extends Listener implements org.newdawn.slick.Game {

    private final List<PlayerController> playerControllerList;
    private final List<NpcController> npcControllerList;
    private final List<RoomController> roomControllerList;
    private final NetworkClient client;

    public ClientController(String ip) {
        super();
        playerControllerList = new ArrayList<>();
        roomControllerList = new ArrayList<>();
        npcControllerList = new ArrayList<>();
        client = new NetworkClient(ip);
    }

    @Override
    public void init(GameContainer gameContainer) throws SlickException {
        gameContainer.setTargetFrameRate(60);
        //Send this class to be set as listener for the connection
        
        final RoomFactory roomFactory = new RoomFactory();

        for (final Room room : roomFactory.getRooms()) {

	        final TiledMap map = ((SlickMap)room.getMap()).getMap();
            RoomController roomController = new RoomController(room, new RoomView(map));
            roomControllerList.add(roomController);

            for (final NpcType npc : room.getNpcs()) {
                NpcController npcController = new NpcController(npc, new CharacterView(npc));
                npcControllerList.add(npcController);
            }
            for (final Player player : room.getPlayers()) {
                PlayerController playerController = new PlayerController(player, new CharacterView(player));
                playerControllerList.add(playerController);
            }
        }
        client.start(this);
    }

    public void characterRecieved(AbstractCharacter character) {
        if (character instanceof Player) {
            boolean found = false;
            for (final PlayerController playerController : playerControllerList) {
                if (playerController.getId() == character.getId()) {
                    playerController.setPlayer((Player) character);
                    found = true;
                }
            }
            if (!found) {
                playerControllerList.add(new PlayerController((Player) character, new CharacterView(character)));
            }
        } else if (character instanceof NpcType) {
            boolean found = false;
            
            for (final NpcController npcController : npcControllerList) {
                if (npcController.getId() == character.getId()) {
                    npcController.setNpc((NpcType) character);
                    found = true;
                }
            }
            if (!found) {
                npcControllerList.add(new NpcController((NpcType) character, new CharacterView(character)));
            }
        }
    }

    @Override
    public void update(GameContainer container, int delta) {
        //TODO: Send to server what keys are pressed
    }

    @Override
    public void render(GameContainer gameContainer, Graphics graphics) throws SlickException {
        for (final RoomController roomController : roomControllerList) {
            roomController.getRoomView().render();
        }

        for (final NpcController npcController : npcControllerList) {
            npcController.render(gameContainer);
        }

        for (final PlayerController playerController : playerControllerList) {
            playerController.render(gameContainer);
        }
    }

    @Override
    public void received(Connection connection, Object object) {
        if (object instanceof Room) {
            System.out.println("We recieved a Room");
        }
        if (object instanceof AbstractCharacter) {
            characterRecieved((AbstractCharacter) object);
        }
    }

    @Override
    public boolean closeRequested() {
        return true;
    }

    @Override
    public String getTitle() {
        return "'Tis but a Scratch";
    }
}
