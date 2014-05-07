package scratch.construction;

import scratch.model.IInteraction;
import scratch.model.IInteractiveObject;
import scratch.model.IMap;
import scratch.model.InteractiveObjectType;

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

    @Override
    public IInteractiveObject createType(int id, double xPosition, double yPosition) {
        for(IInteractiveObject object : super.getGivenTypeList()){
            if(object.getID() == id){
                return object.createCopy(xPosition, yPosition);
            }
        }
        return super.getGivenTypeList().get(id).createCopy(xPosition, yPosition);
    }

    public <IInteractiveObject> void loadType() {

    }
}
