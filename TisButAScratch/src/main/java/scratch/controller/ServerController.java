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
public final class ServerController implements org.newdawn.slick.Game {

    private final NetworkServer networkServer;
    private final Game game;
    private List<PlayerController> playerControllerList;
    private List<NpcController> npcControllerList;
    private List<RoomController> roomControllerList;

    public ServerController(Game game, String ip) {
        this.game = game;
        networkServer = new NetworkServer(game);
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
                npcControllerList.add(
                        new NpcController(npcEntry.getValue(),
                                new NpcView(npcEntry.getValue(), gameContainer, "/res/playerSprite.tmx")));
            }
            for (Player player : room.getPlayers()) {
                playerControllerList.add(
                        new PlayerController(player,
                                new PlayerView(player, gameContainer, "res/playerSprite.tmx")));
            }
        }
        networkServer.start();
    }


    private TiledMap getTiledMap(RoomFactory roomFactory) {
        TiledMap map = roomFactory.getMap();
        game.setMap(roomFactory.getRooms());
        return map;
    }

    public void update(GameContainer container, int delta) throws SlickException {
        for (PlayerController playerController : playerControllerList) {
            playerController.updatePlayer();
        }

        for (NpcController npcController : npcControllerList) {
            npcController.updateNpc();
        }

        for (RoomController roomController : roomControllerList) {
            roomController.updateRoom();
        }
        networkServer.update();
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
