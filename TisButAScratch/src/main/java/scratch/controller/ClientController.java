package scratch.controller;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import scratch.model.Player;
import scratch.network.NetworkClient;
import scratch.view.CharacterView;

import java.util.ArrayList;
import java.util.List;
import scratch.model.AbstractCharacter;

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
        client.start(this);
    }

    public void characterRecieved(AbstractCharacter character) {
        if (character instanceof Player) {
            boolean found = false;
            for (final PlayerController playerController : playerControllerList) {
                if (playerController.getId() == character.getId()) {
                    playerController.setPlayer((Player)character);
                    found = true;
                }
            }
            if (!found) {
                playerControllerList.add(new PlayerController((Player)character, new CharacterView(character)));
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
