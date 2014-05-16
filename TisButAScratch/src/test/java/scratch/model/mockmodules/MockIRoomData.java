package scratch.model.mockmodules;

import org.newdawn.slick.SlickException;
import scratch.construction.SlickMap;
import scratch.construction.TiledMapPlus;
import scratch.model.IMap;
import scratch.model.IRoomData;
import scratch.model.NpcType;
import scratch.model.Player;

import java.util.ArrayList;
import java.util.List;

public class MockIRoomData implements IRoomData{
    private final List<Player> players = new ArrayList<>();
    private final List<NpcType> npcs = new ArrayList<>();
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
    public List<NpcType> getNpcs() {

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
