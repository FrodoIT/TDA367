package scratch.construction;

import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;
import scratch.construction.plugin.Pluggable;
import scratch.construction.plugin.PluginConstants;
import scratch.construction.plugin.exported.SimpleNPCPlugin;
import scratch.model.IMap;
import scratch.model.INPCMove;
import scratch.model.NpcType;
import scratch.model.weapons.DefaultWeapon;

public final class NpcFactory extends PluginUserFactory<NpcType> {

    public static final String KEY = "npc_factory";

    public NpcFactory(IMap map){
        super(map);
        loadType();
    }

    @Override
    public void loadType() {
        System.out.println(super.getMap().hasInteractiveObject() + " given type size");
        for (Rectangle2D.Double npcRectangle : super.getMap().getNpcRectangleMap().values()){
            super.getGivenTypeList().add(new NpcType(npcRectangle, new DefaultWeapon(), 10, 2, "monster.tmx", 0, new SimpleNPCPlugin()));
        }

    }
}
