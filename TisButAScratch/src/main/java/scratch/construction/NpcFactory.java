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

public final class NpcFactory {
    private final List<INpc> npcs;
    private final Map<Integer, Pluggable<?>> aiMoves = PluginLoader.loadPlugins();

    public NpcFactory(){
        npcs = new ArrayList<>();
        loadNpcs();
    }

    public INpc createEnemy(int id, double xPosition, double yPosition) {
        for(INpc npc : npcs){
            if(npc.getID() == id){
                return npc.createCopy(xPosition, yPosition);
            }
        }
        return npcs.get(0).createCopy(xPosition, yPosition);
    }

    private void loadNpcs() {
        //TODO this needs refactoring. for example all monsters have same id (1)
        npcs.add(new NpcType(new Rectangle2D.Double(32,32,32,32), new DefaultWeapon(), 1, 1, "/res/playerSprite.tmx", 0, (INPCMove) aiMoves.get(1).get()));
    }

    public List<INpc> getNpcs(){
        //TODO: should return a clone... if we want this to be immutable..
        return npcs;
    }
}
