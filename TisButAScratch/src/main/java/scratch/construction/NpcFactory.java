package scratch.construction;

import scratch.construction.plugin.PluginConstants;
import scratch.construction.plugin.exported.SimpleNPCPlugin;
import scratch.model.*;
import scratch.model.weapons.DefaultWeapon;

import java.awt.geom.Rectangle2D;
import java.util.Map;

public final class NpcFactory extends PluginUserFactory<NpcType> {



    public static final String KEY = "npc_factory";

    private Room room;

    public NpcFactory(IMap map, Room room){
        super(map);
        this.room = room;
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

            //TODO This should be like this check how the plugin is loaded into the program and replace simplenpcplugin
            INPCMove movePlugin = new SimpleNPCPlugin();
            movePlugin.setRoomData(room);

            super.getGivenTypeMap().put(keyToConstant,
                    new NpcType(entry.getValue(), new DefaultWeapon(), 10, 2, "monster.tmx", 10, movePlugin, room));

        }

    }
}
