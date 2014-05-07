package scratch.construction;

import java.awt.geom.Rectangle2D;
import java.util.*;

import com.google.inject.internal.cglib.proxy.$MethodInterceptor;
import net.java.games.util.plugins.Plugin;
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

        addSubFactories();

        slickMap = new SlickMap(map);
        rooms = new ArrayList<Room>();
        addRooms();
	    //Move to initialise or other addNpc method?
	    rooms.get(0).addNpc((INpc) pluginUserFactories.get(NpcFactory.KEY).createType(0, 32, 32));
		addInteractiveObjects();
    }

	private void addInteractiveObjects() {
		//TODO get interactiveobjects from plugins and match them with objects from TiledMap
		InteractiveObjectFactory interaObjFact = ((InteractiveObjectFactory)pluginUserFactories.get(InteractiveObjectFactory.KEY));

		for (Map.Entry<String, Rectangle2D.Double> entry : slickMap.getObjectRectangles().entrySet()) {
			rooms.get(0).addInteractivObject(
					interaObjFact.createType(3, entry.getValue().getX(), entry.getValue().getY())
			);
		}
	}

	public void initialiseRoom(Room room){

    }

    private void addRooms() {
        //TODO: This should be further extended when several rooms are implemented.
        rooms.add(new Room(slickMap));
    }

    private void addSubFactories() {
        pluginUserFactories = new HashMap<>();
        pluginUserFactories.put(NpcFactory.KEY, new NpcFactory());
        pluginUserFactories.put(InteractiveObjectFactory.KEY, new InteractiveObjectFactory());
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
