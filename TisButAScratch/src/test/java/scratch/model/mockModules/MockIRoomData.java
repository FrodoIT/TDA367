package scratch.model.mockModules;

import org.newdawn.slick.SlickException;
import scratch.construction.SlickMap;
import scratch.construction.TiledMapPlus;
import scratch.model.IMap;
import scratch.model.IRoomData;
import scratch.model.NpcType;
import scratch.model.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class MockIRoomData implements IRoomData{
    private List<Player> players = new ArrayList<Player>();
    private Map<Integer, NpcType> npcs = new TreeMap<Integer, NpcType>();
    private IMap map;


    public void setUp(){
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
    public IMap getMap() {
        if(map == null){
            setUp();
        }
        return map;
    }
}
