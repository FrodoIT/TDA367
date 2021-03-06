package scratch.construction;

import org.newdawn.slick.SlickException;
import scratch.model.DoorHandler;
import scratch.model.InteractiveObject;
import scratch.model.NpcType;
import scratch.model.Room;
import scratch.utils.FileScanner;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The Room factory is the main factory for creating rooms. It uses all sub-factories to store
 * objects and npcs in the given room, which are in turn loaded from the given TiledMap.
 * @author André Samuelsson
 *
 */
public final class RoomFactory {

    private final List<Room> rooms = new ArrayList<>();
    private final List<TiledMapPlus> tiledMaps = new ArrayList<>();
    private final DoorHandler doorHandler = new DoorHandler();
    private final Map<Integer, String> interactiveObjectImageMap;

    public RoomFactory() {
        interactiveObjectImageMap = new TreeMap<Integer, String>();
        loadMaps();
        setUpRooms();
        addInteractiveObjectstoRooms();
        setupDoorHandler();
        addNpcstoRoom();
    }


    private void loadMaps(){
        final List<File> worldFiles = FileScanner.getFiles(new File("res/world/"));
        for(final File room: worldFiles){
            if(room.getName().endsWith(".tmx")){
                try {
                    tiledMaps.add(new TiledMapPlus(room.getCanonicalPath()));
                } catch (IOException | SlickException e) {
                    Logger.getLogger(RoomFactory.class.getName()).log(Level.SEVERE, null, e);
                }
            }
        }
    }

    private void setUpRooms() {
        for(final TiledMapPlus tiledMap: tiledMaps){
            rooms.add(new Room(tiledMap, doorHandler));
        }
    }

    /**
     * gets all doors from all rooms.
     * it is the doorHandlers job to make sure a player is moved
     * from one door to another when a door is interacted with
     */
    private void setupDoorHandler() {
        for (final Room room : rooms) {
            for (final InteractiveObject interactiveObject : room.getInteractiveObjects()) {

                if ("door".equals(interactiveObject.getProperties().get("objectType"))) {
                    doorHandler.addDoor(room, interactiveObject);
                }
            }
        }
    }

    public Map<Integer, String> getInteractiveObjectImageMap() {
        return interactiveObjectImageMap;
    }

    private void addInteractiveObjectstoRooms() {
        for (final Room room : rooms) {
            final TiledMapPlus map = (TiledMapPlus)room.getMap();
            for (final InteractiveObject interactiveObject : map.getInteractiveObjects()) {
                room.addInteractiveObject(interactiveObject);
            }
        }
    }

    private void addNpcstoRoom(){
        final NpcFactory npcFactory = new NpcFactory(rooms);
        for (final Room room : rooms) {
            for (final NpcType npcType : npcFactory.getNpcsForRoom(room)) {
                room.addCharacter(npcType);
            }
        }
    }

    public DoorHandler getDoorHandler (){
        return doorHandler;
    }

    public List<Room> getRooms() {
        return rooms;
    }
}
