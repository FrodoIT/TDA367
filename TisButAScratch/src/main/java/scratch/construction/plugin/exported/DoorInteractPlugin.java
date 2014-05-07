package scratch.construction.plugin.exported;

import scratch.construction.plugin.InteractionPlugin;
import scratch.construction.plugin.Pluggable;
import scratch.model.IInteraction;

/**
 * Created by tejp on 2014-05-07.
 */
@InteractionPlugin(id = 3)
public class DoorInteractPlugin implements Pluggable<DoorInteractPlugin>, IInteraction {

	@Override
	public void performInteraction() {
		System.out.println("DOOR INTERACTION");
	}

	@Override
	public DoorInteractPlugin get() {
		return this;
	}
}
