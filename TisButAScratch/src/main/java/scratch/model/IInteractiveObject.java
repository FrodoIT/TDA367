package scratch.model;

import java.awt.geom.Rectangle2D;
import java.util.Properties;

/**
 * Created by pippin on 4/13/14.
 */
public interface IInteractiveObject {
	String getName();
	String getType();
	Rectangle2D.Double getArea();
	Properties getProperties();
}
