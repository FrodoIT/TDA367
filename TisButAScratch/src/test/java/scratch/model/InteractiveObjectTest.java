package scratch.model;

import junit.framework.TestCase;
import org.junit.Before;

import java.awt.geom.Rectangle2D;
import java.util.Properties;

public class InteractiveObjectTest extends TestCase {

    private InteractiveObject interactiveObject;

    @Before
    public void setUp() {
        Properties props = new Properties();
        interactiveObject = new InteractiveObject("testObject", "box", 32, 32, 32, 32, props);
    }

    public void testGetName() throws Exception {
        assertEquals("testObject", interactiveObject.getName());
    }

    public void testGetType() throws Exception {
        assertEquals("box", interactiveObject.getType());
    }

    public void testGetArea() throws Exception {
        assertEquals(new Rectangle2D.Double(32, 32, 32, 32), interactiveObject.getUnitTile());
    }

    public void testGetProperties() throws Exception {
        assertEquals(new Properties(), interactiveObject.getProperties());
    }
}