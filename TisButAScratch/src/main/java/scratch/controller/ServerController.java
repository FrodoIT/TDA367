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
import java.util.Map;

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
    private List<PlayerController> playerControllerList;
    private List<NpcController> npcControllerList;
    private List<RoomController> roomControllerList;

    public ServerController(Game game) {
        this.game = game;
        networkServer = new NetworkServer();
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
                new Rectangle2D.Double(0,0,32,32), 2, "/res/playerSprite.tmx");

        game.addPlayer(tempPlayer);



        for(Room room : roomFactory.getRooms()){
            roomControllerList.add(
                    new RoomController(room,
                            new RoomView(map)));
            for(Map.Entry<Integer, NpcType> npcEntry : room.getNpcs().entrySet()){
                NpcType npc = npcEntry.getValue();
                npcControllerList.add(
                        new NpcController(npc,
                                new CharacterView(npc)));
            }
            for(Player player : room.getPlayers()){
                playerControllerList.add(
                        new PlayerController(player,
                                new CharacterView(player)));
            }
        }
        networkServer.start(this);
    }

    private TiledMap getTiledMap(RoomFactory roomFactory) {
        TiledMap map = roomFactory.getMap();
        game.setMap(roomFactory.getRooms());
        return map;
    }

    @Override
    public void update(GameContainer container, int delta) throws SlickException {
        for (PlayerController playerController: playerControllerList){
            playerController.updatePlayer();
        }

        for (NpcController npcController : npcControllerList) {
            npcController.updateNpc();
        }

        for (RoomController roomController : roomControllerList) {
            roomController.updateRoom();
        }
    }

    @Override
    public void render(GameContainer gameContainer, Graphics graphics) throws SlickException {

        for (RoomController roomController : roomControllerList) {
            roomController.getRoomView().render();
        }

        for(NpcController npcController : npcControllerList){
            npcController.render(gameContainer);
        }

        for(PlayerController playerController : playerControllerList){
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
        connection.sendTCP(game);
    }
    
    @Override
    public void received(Connection connection, Object object) {
        //TODO We recieved something
        System.out.println("We recieved something");
    }
}
