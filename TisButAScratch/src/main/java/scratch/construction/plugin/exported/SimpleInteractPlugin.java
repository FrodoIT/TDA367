package scratch.construction.plugin.exported;

import scratch.construction.plugin.InteractionPlugin;
import scratch.construction.plugin.Pluggable;
import scratch.model.IInteractiveObject;

import java.awt.geom.Rectangle2D;
import java.util.Properties;

@InteractionPlugin(id = 2)
public final class SimpleInteractPlugin implements Pluggable<SimpleInteractPlugin>, IInteractiveObject {


    @Override
    public SimpleInteractPlugin get() {
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
