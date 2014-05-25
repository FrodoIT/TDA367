package scratch.construction;

import org.simpleframework.xml.transform.Transform;
import scratch.construction.plugin.AIPlugin;
import scratch.construction.plugin.Pluggable;
import scratch.construction.plugin.PluginLoader;
import scratch.model.INPCMove;
import scratch.model.StandardNpcPlugin;

import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Anna on 2014-05-08.
 */
public class INPCMoveTransformer implements Transform<INPCMove> {

	private static Map<Integer, Pluggable <?>> plugins = PluginLoader.loadPlugins(AIPlugin.class);

	@Override
	public INPCMove read(String value){
		int id=-1;
		try {
			id = Integer.parseInt(value);
		}catch (NumberFormatException e){
			e.printStackTrace();
		}
        try {
            return (INPCMove) plugins.get(id).clone();
        } catch (CloneNotSupportedException e) {
            Logger.getLogger(INPCMoveTransformer.class.getName()).log(Level.SEVERE, null, e);
        }
        return new StandardNpcPlugin();
    }

	@Override
	public String write(INPCMove value) {
		return null;
	}
}
