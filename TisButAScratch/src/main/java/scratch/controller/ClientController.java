package scratch.controller;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;
import scratch.construction.RoomFactory;
import scratch.model.Game;
import scratch.model.NpcType;
import scratch.model.Player;
import scratch.model.Room;
import scratch.network.NetworkClient;
import scratch.network.NetworkServer;
import scratch.view.NpcView;
import scratch.view.PlayerView;
import scratch.view.RoomView;

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
    private GameContainer gameContainer;
    private final NetworkClient client;

    public ClientController(String ip) {
        playerControllerList = new ArrayList<PlayerController>();
        roomControllerList = new ArrayList<RoomController>();
        npcControllerList = new ArrayList<NpcController>();
        client = new NetworkClient(ip);
    }

    @Override
    public void init(GameContainer gameContainer) throws SlickException {
        //TODO: This will need to change when we read from XML.
        gameContainer.setTargetFrameRate(60);
        this.gameContainer = gameContainer;
        RoomFactory roomFactory = new RoomFactory();
        TiledMap map = getTiledMap(roomFactory);
        //Send this class to be set as listener for the connection
        client.start(this);

    }

    private TiledMap getTiledMap(RoomFactory roomFactory) {
        TiledMap map = roomFactory.getMap();

        return map;
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
            playerControllerList.add(new PlayerController(player, new PlayerView(player, gameContainer, "res/playerSprite.tmx")));
        }
    }

    @Override
    public void update(GameContainer container, int delta) throws SlickException {
        //TODO: Send to server what keys are pressed
    }

    @Override
    public void render(GameContainer gameContainer, Graphics graphics) throws SlickException {

        for (RoomController roomController : roomControllerList) {
            roomController.getRoomView().render();
        }

        for (NpcController npcController : npcControllerList) {
            npcController.getNpcView().render();
        }

        for (PlayerController playerController : playerControllerList) {
            playerController.getPlayerView().render();
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
