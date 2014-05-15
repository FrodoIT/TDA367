package scratch.controller;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;
import scratch.construction.RoomFactory;
import scratch.model.Player;
import scratch.network.NetworkClient;
import scratch.view.CharacterView;

import java.util.ArrayList;
import java.util.List;

/**
 * The main controller class to control updates, rendering, initiating and
 * delegate tasks to other controllers.
 *
 * @author Anna Nylander
 *
 */
public final class ClientController extends Listener implements org.newdawn.slick.Game {

    private List<PlayerController> playerControllerList;
    private List<NpcController> npcControllerList;
    private List<RoomController> roomControllerList;
    private final NetworkClient client;

    public ClientController(String ip) {
        playerControllerList = new ArrayList<>();
        roomControllerList = new ArrayList<>();
        npcControllerList = new ArrayList<>();
        client = new NetworkClient(ip);
    }

    @Override
    public void init(GameContainer gameContainer) throws SlickException {
        //TODO: This will need to change when we read from XML.
        gameContainer.setTargetFrameRate(60);
        //Send this class to be set as listener for the connection
        client.start(this);

    }

    public void playerRecieved(Player player) {
        boolean found = false;
        for (PlayerController playerController : playerControllerList) {
            if (playerController.getId() == player.getId()) {
                playerController.setPlayer(player);
                found = true;
            }
        }
        if (!found) {
            playerControllerList.add(new PlayerController(player, new CharacterView(player)));
        }
    }

    @Override
    public void update(GameContainer container, int delta) {
        //TODO: Send to server what keys are pressed
    }

    @Override
    public void render(GameContainer gameContainer, Graphics graphics) throws SlickException {

        for (RoomController roomController : roomControllerList) {
            roomController.getRoomView().render();
        }

        for (NpcController npcController : npcControllerList) {
            npcController.render(gameContainer);
        }

        for (PlayerController playerController : playerControllerList) {
            playerController.render(gameContainer);
        }
    }

    @Override
    public void received(Connection connection, Object object) {
        if (object instanceof Player) {
            playerRecieved((Player) object);
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
