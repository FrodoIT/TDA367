package scratch.construction;

import java.awt.geom.Rectangle2D;
import java.util.*;

import com.google.inject.internal.cglib.proxy.$MethodInterceptor;
import net.java.games.util.plugins.Plugin;
import scratch.construction.plugin.PluginConstants;
import scratch.model.IInteractiveObject;
import scratch.model.INpc;
import scratch.model.Room;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;
import scratch.model.Vector2D;

/**
 * The Room factory is the main factory for creating rooms. It uses all sub-factories to store
 * objects and npcs in the given room, which are in turn loaded from the given TiledMap.
 * @author André Samuelsson
 *
 */
public final class RoomFactory {

    private TiledMap map;
    private List<Room> rooms;
    //private List<PluginUserFactory> pluginUserFactories;
	private Map<String, PluginUserFactory> pluginUserFactories;
    private SlickMap slickMap;


    public RoomFactory() {
        try {
            map = new TiledMap("res/untitled.tmx");
        } catch (SlickException e) {
            e.printStackTrace();
        }


        pluginUserFactories = new TreeMap<String, PluginUserFactory>();
        slickMap = new SlickMap(map);
        rooms = new ArrayList<Room>();
        addSubFactories();
        addRooms();

        addInteractiveObjects();

    }

	private void addInteractiveObjects() {
		//TODO get interactiveobjects from plugins and match them with objects from TiledMap

        InteractiveObjectFactory objectFactory = ((InteractiveObjectFactory)pluginUserFactories.get(InteractiveObjectFactory.KEY));
		for (IInteractiveObject interactiveObject : objectFactory.getGivenTypeList()) {
			rooms.get(0).addInteractivObject(interactiveObject);
		}
	}

	public void initialiseRoom(Room room){

    }

    private void addRooms() {
        //TODO: This should be further extended when several rooms are implemented.
        rooms.add(new Room(slickMap));
    }

    private void addSubFactories() {
        System.out.println("BLEEPP " + pluginUserFactories);
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
