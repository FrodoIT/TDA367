package scratch.construction;

import org.newdawn.slick.SlickException;
import scratch.model.DoorHandler;
import scratch.model.IInteractiveObject;
import scratch.model.NpcType;
import scratch.model.Room;
import scratch.utils.FileScanner;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * The Room factory is the main factory for creating rooms. It uses all sub-factories to store
 * objects and npcs in the given room, which are in turn loaded from the given TiledMap.
 * @author André Samuelsson
 *
 */
public final class RoomFactory {

    private final List<Room> rooms = new ArrayList<>();
    private final List<SlickMap> slickMaps = new ArrayList<>();
	private final DoorHandler doorHandler = new DoorHandler();

    public RoomFactory() {
		setUpSlickMaps( loadMaps() );
        setUpRooms();

        addInteractiveObjectstoRooms();
	    setupDoorHandler();
        addNpcstoRoom();
    }

	private void setUpSlickMaps(List<TiledMapPlus> maps){
        for(final TiledMapPlus room : maps){
			slickMaps.add(new SlickMap(room));
		}
	}

    private List<TiledMapPlus> loadMaps(){
        final List<File> worldFiles = FileScanner.getFiles(new File("res/world/"));
        final List<TiledMapPlus> world = new ArrayList<>();
        for(final File room: worldFiles){
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

    private void setUpRooms() {
        for(final SlickMap s: slickMaps){
            rooms.add(new Room(s,doorHandler));
        }
    }

	/**
	 * gets all doors from all rooms.
	 * it is the doorHandlers job to make sure a player is moved
	 * from one door to another when a door is interacted with
	 */
	private void setupDoorHandler() {
		for (final Room room : rooms) {
			for (final IInteractiveObject interactiveObject : room.getInteractiveObjects()) {
				if ("door".equals(interactiveObject.getProperties().get("objectType"))) {
					doorHandler.addDoor(room, interactiveObject);
				}
			}
		}

	}

	private void addInteractiveObjectstoRooms() {
		for (final Room room : rooms) {
            final TiledMapPlus map = ((SlickMap)room.getMap()).getMap();
			for (final IInteractiveObject interactiveObject : map.getInteractiveObjects()) {
                room.addInteractivObject(interactiveObject);
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

    public List<Room> getRooms() {
        return rooms;
    }
}
