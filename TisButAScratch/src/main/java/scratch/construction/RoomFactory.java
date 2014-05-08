package scratch.construction;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;
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

	private NpcType NPCXML(){
		Serializer serializer = new Persister(new MyMatcher());
		File source = new File("res/NPCType.xml");
		try {
			NpcType npc= serializer.read(NpcType.class, source);
			System.out.println(npc.toString());
			return npc;
		} catch (Exception e) {
			System.out.println("XML-READING FAILED: " + e.toString());
		}
		return null;
	}
    public RoomFactory() {
        try {
            map = new TiledMapPlus("res/untitled.tmx");
        } catch (SlickException e) {
            e.printStackTrace();
        }
	    NPCXML();
        pluginUserFactories = new TreeMap<String, PluginUserFactory>();
        slickMap = new SlickMap(map);
        rooms = new ArrayList<Room>();
        addSubFactories();
        addRooms();
        addInteractiveObjectstoRoom();
        addNpcstoRoom();
    }

    private void addInteractiveObjectstoRoom() {
        //TODO get interactiveobjects from plugins and match them with objects from TiledMap

        InteractiveObjectFactory objectFactory = ((InteractiveObjectFactory)pluginUserFactories.get(InteractiveObjectFactory.KEY));
        rooms.get(0).setInteractivObjects(objectFactory.getGivenTypeMap());

    }

    private void addNpcstoRoom(){
        NpcFactory npcFactory = ((NpcFactory)pluginUserFactories.get(NpcFactory.KEY));
        rooms.get(0).addNpc(npcFactory.getGivenTypeMap());
    }

    public void initialiseRoom(Room room){

    }

    private void addRooms() {
        //TODO: This should be further extended when several rooms are implemented.
        rooms.add(new Room(slickMap));
    }

    private void addSubFactories() {
        pluginUserFactories.put(NpcFactory.KEY, new NpcFactory(slickMap));
        pluginUserFactories.put(InteractiveObjectFactory.KEY, new InteractiveObjectFactory(slickMap));
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
