package scratch.construction;

import scratch.construction.plugin.PluginConstants;
import scratch.construction.plugin.exported.SimpleNPCPlugin;
import scratch.model.IMap;
import scratch.model.NpcType;
import scratch.model.weapons.DefaultWeapon;

import java.awt.geom.Rectangle2D;
import java.util.Map;

public final class NpcFactory extends PluginUserFactory<NpcType> {



    public static final String KEY = "npc_factory";

    public NpcFactory(IMap map){
        super(map);
        loadType();
    }

    @Override
    public void loadType() {
        //TODO: This will extend when we add MORE types of NPCS.
        for(Map.Entry<String, Rectangle2D.Double> entry : super.getMap().getNpcRectangleMap().entrySet()){
            int keyToConstant = 0;
            if(entry.getKey().equals("basicMonster")){
                keyToConstant = PluginConstants.DOOR;
            } else if(entry.getKey().equals("specialMonster")){
                keyToConstant = PluginConstants.BOX;
            }
            super.getGivenTypeMap().put(keyToConstant,
                    new NpcType(entry.getValue(), new DefaultWeapon(), 10, 2, "monster.tmx", 10, new SimpleNPCPlugin()));

        }

    }
}
