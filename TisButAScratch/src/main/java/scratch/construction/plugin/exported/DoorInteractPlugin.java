package scratch.construction.plugin.exported;

import scratch.construction.plugin.InteractionPlugin;
import scratch.construction.plugin.Pluggable;
import scratch.model.IInteractiveObject;

import java.awt.geom.Rectangle2D;
import java.util.Properties;

/**
 * Created by tejp on 2014-05-07.
 */
@InteractionPlugin(id = 3)
public class DoorInteractPlugin implements Pluggable<DoorInteractPlugin>, IInteractiveObject {

	@Override
	public DoorInteractPlugin get() {
		return this;
	}

	@Override
	public String getName() {
		return null;
	}

	@Override
	public String getType() {
		return null;
	}

	@Override
	public Rectangle2D.Double getArea() {
		return null;
	}

	@Override
	public Properties getProperties() {
		return null;
	}
}
