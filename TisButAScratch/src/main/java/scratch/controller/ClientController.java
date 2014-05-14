package scratch.controller;

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
public final class ClientController implements org.newdawn.slick.Game {

    private final NetworkClient networkClient;
    private final Game game;
    private List<PlayerController> playerControllerList;
    private List<NpcController> npcControllerList;
    private List<RoomController> roomControllerList;

    public ClientController(Game game, String ip) {
        this.game = game;
        networkClient = new NetworkClient(ip);
        playerControllerList = new ArrayList<PlayerController>();
        roomControllerList = new ArrayList<RoomController>();
        npcControllerList = new ArrayList<NpcController>();
    }

    @Override
    public void init(GameContainer gameContainer) throws SlickException {
        //TODO: This will need to change when we read from XML.
        gameContainer.setTargetFrameRate(60);
        RoomFactory roomFactory = new RoomFactory();
        TiledMap map = getTiledMap(roomFactory);
        Player tempPlayer = new Player(
                new PlayerInput(gameContainer.getInput()),
                new Rectangle2D.Double(0, 0, 32, 32), 2);

        game.addPlayer(tempPlayer);

        for (Room room : roomFactory.getRooms()) {
            roomControllerList.add(
                    new RoomController(room,
                            new RoomView(gameContainer, room, map)));

            for (Map.Entry<Integer, NpcType> npcEntry : room.getNpcs().entrySet()) {
                NpcController npcController = new NpcController(npcEntry.getValue(),
                        new NpcView(npcEntry.getValue(), gameContainer, "/res/playerSprite.tmx"));
                npcControllerList.add(npcController);

            }
            for (Player player : room.getPlayers()) {
                PlayerController npcController = new PlayerController(player,
                        new PlayerView(player, gameContainer, "res/playerSprite.tmx"));
                playerControllerList.add(npcController);

            }
        }
        networkClient.start();
    }

    private TiledMap getTiledMap(RoomFactory roomFactory) {
        TiledMap map = roomFactory.getMap();
        game.setMap(roomFactory.getRooms());
        return map;
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
    public boolean closeRequested() {
        return true;
    }

    @Override
    public String getTitle() {
        return "'Tis but a Scratch";
    }
}
