package scratch.construction;

import scratch.construction.plugin.Pluggable;
import scratch.construction.plugin.PluginLoader;
import scratch.model.INPCMove;
import scratch.model.INpc;
import scratch.model.NpcType;
import scratch.model.weapons.Knuckles;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class EnemyFactory {
    private List<INpc> npcs;
    private Map<Integer, Pluggable<?>> aiMoves = PluginLoader.loadPlugins();
    public EnemyFactory(){
        npcs = new ArrayList<INpc>();
    }

    public List<INpc> getEnemies() {
        return npcs;
    }

    public void createEnemy(Rectangle rect) {
        //TODO this needs refactoring. for example all monsters have same id (1)
        NpcType npc = new NpcType(rect, new Knuckles(), 1, 1, "/res/playerSprite.tmx", 1);
        npcs.add(npc);
        npc.setMovementPattern((INPCMove) aiMoves.get(1).get());
    }
}
