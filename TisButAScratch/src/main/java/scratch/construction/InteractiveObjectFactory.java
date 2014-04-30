package scratch.construction;

import scratch.model.IInteraction;
import scratch.model.IInteractiveObject;
import scratch.model.InteractiveObjectType;

import java.awt.geom.Rectangle2D;

/**
 * Created by pippin on 4/26/14.
 */
public class InteractiveObjectFactory extends PluginUserFactory<IInteractiveObject> {

    public InteractiveObjectFactory(){
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
        super.getGivenTypeList().add(new InteractiveObjectType(1, (IInteraction) getPluginMap().get(2).get(), "/res/playerSprite.tmx", new Rectangle2D.Double(32,32,32,32)));
    }
}
