package scratch.construction;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;
import scratch.model.DoorHandler;
import scratch.model.IInteractiveObject;
import scratch.model.NpcType;
import scratch.model.Room;
import scratch.utils.FileScanner;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The Room factory is the main factory for creating rooms. It uses all sub-factories to store
 * objects and npcs in the given room, which are in turn loaded from the given TiledMap.
 * @author André Samuelsson
 *
 */
public final class RoomFactory {

    private List<TiledMapPlus> maps= new ArrayList<>();
    private final List<Room> rooms;
    private final NpcFactory npcFactory;
    private List<SlickMap> slickMaps;
	private final DoorHandler doorHandler = new DoorHandler();

    public RoomFactory() {
        maps = loadMaps();
		slickMaps = createSlickMaps(maps);
        rooms = createRooms();

	    System.out.println("HAFHLAFKHJ" + rooms.size());

        npcFactory = new NpcFactory(rooms);

        addInteractiveObjectstoRooms();
	    setupDoorHandler();
        addNpcstoRoom();
    }

	private List<SlickMap> createSlickMaps(List<TiledMapPlus> maps){
		final List<SlickMap> slickMap = new ArrayList<>();
		for(TiledMapPlus room : maps){
			slickMap.add(new SlickMap(room));
		}
		return slickMap;
	}

	private List<TiledMapPlus> loadMaps(){
		final List<File> worldFiles = FileScanner.getFiles(new File("res/world/"));
		final List<TiledMapPlus> world = new ArrayList<>();
		for(File room: worldFiles){
			if(room.getName().endsWith(".tmx")){
				try {
					world.add(new TiledMapPlus(room.getCanonicalPath()));
				} catch (IOException | SlickException e) {
					e.printStackTrace();
				}
			}
		}
		return world;

	}

	/**
	 * gets all doors from all rooms.
	 * it is the doorHandlers job to make sure a player is moved
	 * from one door to another when a door is interacted with
	 */
	private void setupDoorHandler() {
		for (TiledMapPlus map : maps) {
			for (final IInteractiveObject interactiveObject : map.getInteractiveObjects()) {
				if ("door".equals(interactiveObject.getProperties().get("objectType"))) {
					doorHandler.addDoor(rooms.get(0), interactiveObject);
				}
			}
		}

	}

	private void addInteractiveObjectstoRooms() {
		for (TiledMapPlus map : maps) {
			for (final IInteractiveObject interactiveObject : map.getInteractiveObjects()) {
				rooms.get(0).addInteractivObject(interactiveObject);
			}
		}
    }

    private void addNpcstoRoom(){
        for (final Room room : rooms) {
            for (final NpcType npcType : npcFactory.getNpcsForRoom(room)) {
                room.addNpc(npcType);
            }
        }
    }

    private List<Room> createRooms() {
	    List<Room> tempRooms = new ArrayList<>();
	    for(SlickMap s: slickMaps){
		    tempRooms.add(new Room(s,doorHandler));
	    }
	    return tempRooms;
    }

    public List<Room> getRooms() {
        return rooms;
    }
}
