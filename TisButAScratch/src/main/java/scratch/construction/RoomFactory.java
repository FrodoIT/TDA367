package scratch.construction;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;
import scratch.model.DoorHandler;
import scratch.model.IInteractiveObject;
import scratch.model.NpcType;
import scratch.model.Room;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * The Room factory is the main factory for creating rooms. It uses all sub-factories to store
 * objects and npcs in the given room, which are in turn loaded from the given TiledMap.
 * @author André Samuelsson
 *
 */
public final class RoomFactory {

    private TiledMapPlus map;
    private List<Room> rooms;
    private Map<String, PluginUserFactory> pluginUserFactories;
    private SlickMap slickMap;
	private DoorHandler doorHandler = new DoorHandler();

    public RoomFactory() {
        try {
            map = new TiledMapPlus("res/untitled.tmx");
        } catch (SlickException e) {
            e.printStackTrace();
        }
        pluginUserFactories = new TreeMap<String, PluginUserFactory>();
        slickMap = new SlickMap(map);
        rooms = new ArrayList<Room>();
        addRooms();
        addSubFactories();
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

		for (IInteractiveObject interactiveObject : map.getInteractiveObjects()) {
			if ("door".equals(interactiveObject.getProperties().get("objectType"))) {
				doorHandler.addDoor(rooms.get(0), interactiveObject);
			}
		}

	}

	private void addInteractiveObjectstoRoom() {

	    for (IInteractiveObject interactiveObject : map.getInteractiveObjects()) {
		    rooms.get(0).addInteractivObject(interactiveObject);
	    }

    }

    private void addNpcstoRoom(){
        NpcFactory npcFactory = ((NpcFactory)pluginUserFactories.get(NpcFactory.KEY));
        rooms.get(0).addNpc(npcFactory.getGivenTypeMap());
    }

    public void initialiseRoom(Room room){

    }

    private void addRooms() {
        //TODO: This should be further extended when several rooms are implemented.
        rooms.add(new Room(slickMap, doorHandler));
    }

    private void addSubFactories() {
        pluginUserFactories.put(NpcFactory.KEY, new NpcFactory(slickMap, rooms.get(0)));
        //pluginUserFactories.put(InteractiveObjectFactory.KEY, new InteractiveObjectFactory(slickMap));
    }

    public TiledMap getTiledMap() {

        return map;
    }

    public TiledMap getMap() {
        return map;
    }

    public Map<String, PluginUserFactory> getPluginUserFactories() {
        return pluginUserFactories;
    }

    public List<Room> getRooms() {
        return rooms;
    }
}
