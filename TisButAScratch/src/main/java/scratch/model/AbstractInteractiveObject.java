package scratch.model;

import java.awt.geom.Rectangle2D;
import java.util.Properties;

/**
 * Created by tejp on 2014-05-09.
 */
public abstract class AbstractInteractiveObject {

	private IInteractiveObjectProperties properties;

	public Rectangle2D.Double getArea() {
		return new Rectangle2D.Double(properties.getX(), properties.getY(), properties.getWidth(), properties.getHeight());
	}

	public String getName() {
		return properties.getName();
	}

	public String getType() {
		return properties.getType();
	}

	public void setInteractiveObjectProperties(IInteractiveObjectProperties properties) {
		this.properties = properties;
	}

	public Properties getProperties() {
		return properties.getProperties();
	}

	abstract void interact();
}
