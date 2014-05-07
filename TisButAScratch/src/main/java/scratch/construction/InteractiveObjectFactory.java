package scratch.construction;

import scratch.construction.plugin.PluginConstants;
import scratch.construction.plugin.exported.SimpleInteractPlugin;
import scratch.model.*;

import java.awt.geom.Rectangle2D;
import java.util.Map;

/**
 * Created by pippin on 4/26/14.
 */
public class InteractiveObjectFactory extends PluginUserFactory<IInteractiveObject> {

    public static final String KEY = "interactiveobjectfactory_key";

    public InteractiveObjectFactory(IMap map){
        super(map);
        loadType();
    }

    public void loadType() {
        for(Map.Entry<String, Rectangle2D.Double> entry : super.getMap().getNpcRectangleMap().entrySet()){
            int keyToConstant = 0;
            if(entry.getKey().equals("door")){
                keyToConstant = PluginConstants.DOOR;
            } else if(entry.getKey().equals("box")){
                keyToConstant = PluginConstants.BOX;
            }
            super.getGivenTypeMap().put(keyToConstant,
                    new InteractiveObjectType(2, new SimpleInteractPlugin(), "monster.tmx", entry.getValue()));
        }
    }
}
