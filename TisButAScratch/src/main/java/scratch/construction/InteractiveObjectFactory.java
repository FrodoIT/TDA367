package scratch.construction;

import scratch.construction.plugin.exported.SimpleInteractPlugin;
import scratch.construction.plugin.exported.SimpleNPCPlugin;
import scratch.model.*;
import scratch.model.weapons.DefaultWeapon;

import java.awt.geom.Rectangle2D;

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
        for (Rectangle2D.Double interactiveObjectRectangle : super.getMap().getObjectRectangles().values()){
            super.getGivenTypeList().add(new InteractiveObjectType(2, new SimpleInteractPlugin(), "monster.tmx", interactiveObjectRectangle));
        }
    }
}
