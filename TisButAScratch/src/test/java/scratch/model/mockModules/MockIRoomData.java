package scratch.model.mockModules;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;
import scratch.construction.SlickMap;
import scratch.construction.TiledMapPlus;
import scratch.model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by pippin on 5/3/14.
 */
public class MockIRoomData implements IRoomData{
    private List<Player> players = new ArrayList<Player>();
    private Map<Integer, NpcType> npcs = new TreeMap<Integer, NpcType>();
    private IMap map;


    public void setup(){
        try{
          map = new SlickMap(new TiledMapPlus("res/untitled.tmx"));
        } catch (SlickException e){
            e.printStackTrace();
        }
    }

    @Override
    public List<Player> getPlayers() {
        return players;
    }

    @Override

    public Map<Integer, NpcType> getNpcs() {

        return npcs;
    }

    @Override
    public Map<Integer, IInteractiveObject> getDoors() {
        return null;
    }


	@Override
    public IMap getMap() {
        if(map == null){
            setup();
        }
        return map;
    }
}
