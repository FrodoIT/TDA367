package scratch.controller;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
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
import scratch.view.CharacterView;
import scratch.view.RoomView;

import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;

/**
 * The main controller class to control updates, rendering, initiating and
 * delegate tasks to other controllers.
 *
 * @author Anna Nylander
 *
 */
public final class ServerController extends Listener implements org.newdawn.slick.Game {

    private final NetworkServer networkServer;
    private final Game game;
    private final List<PlayerController> playerControllerList;
    private final List<NpcController> npcControllerList;
    private final List<RoomController> roomControllerList;

    public ServerController(Game game) {
        super();
        this.game = game;
        networkServer = new NetworkServer();
        playerControllerList = new ArrayList<>();
        roomControllerList = new ArrayList<>();
        npcControllerList = new ArrayList<>();
    }

    @Override
    public void init(GameContainer gameContainer) throws SlickException {
        //TODO: This will need to change when we read from XML.
        gameContainer.setTargetFrameRate(60);
        final RoomFactory roomFactory = new RoomFactory();
        final TiledMap map = roomFactory.getMap();
        game.setMap(roomFactory.getRooms());
        final Player newPlayer = new Player(
                new PlayerInput(gameContainer.getInput()),
                new Rectangle2D.Double(0,0,32,32), 2, "/res/playerSprite.tmx");
        game.addPlayer(newPlayer);

        for(final Room room : roomFactory.getRooms()){
            RoomController roomController = new RoomController(room, new RoomView(map));
            //TODO: Add server as listener to room
            roomControllerList.add(roomController);

            for(final NpcType npc : room.getNpcs()){
                NpcController npcController = new NpcController(npc, new CharacterView(npc));
                npcController.addListener(networkServer);
                npcControllerList.add(npcController);
            }
            for(final Player player : room.getPlayers()){
                PlayerController playerController = new PlayerController(player, new CharacterView(player));
                playerController.addListener(networkServer);
                playerControllerList.add(playerController);
            }
        }

        networkServer.start(this);

        
    }

    @Override
    public void update(GameContainer container, int delta) throws SlickException {
        for (final PlayerController playerController: playerControllerList){
            playerController.updatePlayer();
        }

        for (final NpcController npcController : npcControllerList) {
            npcController.updateNpc();
        }

        for (final RoomController roomController : roomControllerList) {
            roomController.updateRoom();
        }
    }

    @Override
    public void render(GameContainer gameContainer, Graphics graphics) throws SlickException {

        for (final RoomController roomController : roomControllerList) {
            roomController.getRoomView().render();
        }

        for(final NpcController npcController : npcControllerList){
            npcController.render(gameContainer);
        }

        for(final PlayerController playerController : playerControllerList){
            playerController.render(gameContainer);
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
    
    @Override
    public void connected(Connection connection) {
        //connection.sendTCP(game);
    }
    
    @Override
    public void received(Connection connection, Object object) {
        //TODO We recieved something
        System.out.println("We recieved something");
    }
}
