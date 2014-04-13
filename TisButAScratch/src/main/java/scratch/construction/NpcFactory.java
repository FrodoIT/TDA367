package scratch.construction;

import scratch.construction.plugin.Pluggable;
import scratch.construction.plugin.PluginLoader;
import scratch.model.INPCMove;
import scratch.model.INpc;
import scratch.model.NpcType;
import scratch.model.weapons.DefaultWeapon;

import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NpcFactory {
    private List<INpc> npcs;
    private Map<Integer, Pluggable<?>> aiMoves = PluginLoader.loadPlugins();
    public NpcFactory(){
        npcs = new ArrayList<>();
        loadNpcs();
    }

    public INpc createEnemy(int id) {
        for(INpc npc : npcs){
            if(npc.getID() == id){
                return npc.copy();
            }
        }
        return npcs.get(0).copy();
    }

    private void loadNpcs() {
        //TODO this needs refactoring. for example all monsters have same id (1)
        npcs.add(new NpcType(new Rectangle2D.Double(32,32,32,32), new DefaultWeapon(), 1, 1, "/res/playerSprite.tmx", 0, (INPCMove) aiMoves.get(1).get()));
    }

    public List<INpc> getNpcs(){
        return npcs;
    }
}
