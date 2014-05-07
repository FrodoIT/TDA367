package scratch.construction.plugin.exported;

import scratch.construction.plugin.InteractionPlugin;
import scratch.construction.plugin.Pluggable;
import scratch.model.IInteraction;

/**
 * Created by Anna on 2014-05-07.
 */
@InteractionPlugin(id = 4)
public class BoxInteracPlugin implements Pluggable<BoxInteracPlugin>, IInteraction {

	@Override
	public void performInteraction() {
		System.out.println("DONT TOUCH ME!!");
	}

	@Override
	public BoxInteracPlugin get() {
		return this;
	}
}
