package scratch.construction;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;
import scratch.model.Room;

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

    private TiledMap map;
    private List<Room> rooms;
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
