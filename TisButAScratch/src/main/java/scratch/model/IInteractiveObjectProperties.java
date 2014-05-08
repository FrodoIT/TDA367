package scratch.model;

import java.util.Map;
import java.util.Properties;

/**
 * Created by tejp on 2014-05-08.
 */
public interface IInteractiveObjectProperties {
	String getName();
	String getType();
	int getWidth();
	int getHeight();
	int getX();
	int getY();
	Properties getProperties();

}
