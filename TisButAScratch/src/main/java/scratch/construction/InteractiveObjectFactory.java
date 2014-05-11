package scratch.construction;

import scratch.construction.plugin.InteractionPlugin;
import scratch.construction.plugin.Pluggable;
import scratch.construction.plugin.PluginConstants;
import scratch.construction.plugin.PluginLoader;
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
	    pluginMap = PluginLoader.loadPlugins(InteractionPlugin.class);
        loadType();
    }

    public void loadType() {

		    /*
		    System.out.println();
		    System.out.println("*******************************");
		    System.out.println("Found: " + interactiveObject.getName() + interactiveObject.getID());
		    System.out.println("Trying match...");
		    System.out.println("    Result: " + findMatchingProperties(pluginEntry.getKey()));

		    interactiveObject.setInteractiveObjectProperties(
				    findMatchingProperties(pluginEntry.getKey())
		    );

		    System.out.println("*******************************");
		    System.out.println();
		    */

	    /*
        for(Map.Entry<String, Rectangle2D.Double> entry : super.getMap().getObjectRectangleMap().entrySet()){
            int keyToConstant = 0;
	        if(entry.getKey().equals("door")){
		        keyToConstant = PluginConstants.DOOR;
            } else if(entry.getKey().equals("box")){
                keyToConstant = PluginConstants.BOX;
            }

	        for (Map.Entry<Integer, Pluggable<?>> interactivePluginEntry : pluginMap.entrySet()) {
		        IInteractiveObject interactiveObject = (IInteractiveObject)interactivePluginEntry.getValue();
		        System.out.println("you want this " + findMatchingProperties(3).getProperties());
		        System.out.println(keyToConstant);
				interactiveObject.setInteractiveObjectProperties(findMatchingProperties(keyToConstant));
	        }
		*/
          //  super.getGivenTypeMap().put(keyToConstant, (IInteractiveObject)pluginMap.get(keyToConstant));
        //}
    }
}
