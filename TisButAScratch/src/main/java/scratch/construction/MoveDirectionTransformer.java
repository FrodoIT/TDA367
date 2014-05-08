package scratch.construction;

import org.simpleframework.xml.transform.Transform;
import scratch.model.MoveDirection;

/**
 * Created by Anna on 2014-05-08.
 */
public class MoveDirectionTransformer implements Transform<MoveDirection> {
	@Override
	public MoveDirection read(String value) throws Exception {
		return MoveDirection.SOUTH;
	}

	@Override
	public String write(MoveDirection value) throws Exception {
		return null;
	}
}
