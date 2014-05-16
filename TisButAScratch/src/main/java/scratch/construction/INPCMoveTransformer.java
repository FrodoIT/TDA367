package scratch.construction;

import org.simpleframework.xml.transform.Transform;
import scratch.construction.plugin.AIPlugin;
import scratch.construction.plugin.Pluggable;
import scratch.construction.plugin.PluginLoader;
import scratch.model.INPCMove;

import java.util.Map;

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
		return (INPCMove) plugins.get(id);
	}

	@Override
	public String write(INPCMove value) {
		return null;
	}
}
