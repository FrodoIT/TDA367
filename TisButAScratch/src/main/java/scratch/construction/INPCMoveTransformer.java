package scratch.construction;

import org.simpleframework.xml.transform.Transform;
import scratch.construction.plugin.exported.SimpleNPCPlugin;
import scratch.model.INPCMove;

/**
 * Created by Anna on 2014-05-08.
 */
public class INPCMoveTransformer implements Transform<INPCMove> {
	@Override
	public INPCMove read(String value) throws Exception {
		if(value.compareTo("SimpleNPCPlugin")==0){
			return new SimpleNPCPlugin();
		}
		return new SimpleNPCPlugin();
	}

	@Override
	public String write(INPCMove value) throws Exception {
		return null;
	}
}
