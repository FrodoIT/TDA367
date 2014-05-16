package scratch.construction;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;
import scratch.model.DoorHandler;
import scratch.model.IInteractiveObject;
import scratch.model.NpcType;
import scratch.model.Room;

import java.util.ArrayList;
import java.util.List;

/**
 * The Room factory is the main factory for creating rooms. It uses all sub-factories to store
 * objects and npcs in the given room, which are in turn loaded from the given TiledMap.
 * @author André Samuelsson
 *
 */
public final class RoomFactory {

    private TiledMapPlus map;
    private final List<Room> rooms;
    private final NpcFactory npcFactory;
    private final SlickMap slickMap;
	private final DoorHandler doorHandler = new DoorHandler();

    public RoomFactory() {
        try {
            map = new TiledMapPlus("res/untitled.tmx");
        } catch (SlickException e) {
            e.printStackTrace();
        }
        slickMap = new SlickMap(map);
        rooms = new ArrayList<>();
        addRooms();
        npcFactory = new NpcFactory(rooms);
        addInteractiveObjectstoRoom();
	    setupDoorHandler();
        addNpcstoRoom();
    }

	/**
	 * gets all doors from all rooms.
	 * it is the doorHandlers job to make sure a player is moved
	 * from one door to another when a door is interacted with
	 */
	private void setupDoorHandler() {
		for (final IInteractiveObject interactiveObject : map.getInteractiveObjects()) {
			if ("door".equals(interactiveObject.getProperties().get("objectType"))) {
				doorHandler.addDoor(rooms.get(0), interactiveObject);
			}
		}

	}

	private void addInteractiveObjectstoRoom() {
	    for (final IInteractiveObject interactiveObject : map.getInteractiveObjects()) {
		    rooms.get(0).addInteractivObject(interactiveObject);
	    }

    }

    private void addNpcstoRoom(){
        for (Room room : rooms) {
            for (NpcType npcType : npcFactory.getNpcsForRoom(room)) {
                room.addNpc(npcType);
            }
        }
    }

    private void addRooms() {
        //TODO: This should be further extended when several rooms are implemented.
        rooms.add(new Room(slickMap, doorHandler));
    }

    public TiledMap getTiledMap() {

        return map;
    }

    public TiledMap getMap() {
        return map;
    }

    public List<Room> getRooms() {
        return rooms;
    }
}
