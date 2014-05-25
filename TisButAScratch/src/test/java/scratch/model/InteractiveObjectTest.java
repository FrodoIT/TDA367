package scratch.model;

import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

import java.awt.geom.Rectangle2D;
import java.util.HashMap;
import java.util.Properties;



public class InteractiveObjectTest extends TestCase {

    private InteractiveObject interactiveObject;

    @Before
    public void setUp(){
        final HashMap<String,String> props = new HashMap<>();
        interactiveObject = new InteractiveObject("testObject", "box", 32, 32, 32, 32, props);
    }
    
    @Test

    public void testPosition(){
	    interactiveObject.setPosition(new Vector2D(20,20));
	    assertTrue(interactiveObject.getPosition().equals(new Vector2D(20,20)));
    }
	@Test
	public void testSetObject(){
		final InteractiveObject newInteractiveObject = new InteractiveObject("newTestObject", "door",0,0,0,0, new HashMap());
		interactiveObject.setObject(newInteractiveObject);
		assertTrue(interactiveObject.getProperties().equals(newInteractiveObject.getProperties()) &&
				interactiveObject.getUnitTile().equals(newInteractiveObject.getUnitTile()) &&
				interactiveObject.getName().equals(newInteractiveObject.getName()) &&
				interactiveObject.getType().equals(newInteractiveObject.getType()));
	}
	@Test
	public void testGetName() throws Exception {
		assertEquals("testObject", interactiveObject.getName());
	}

	@Test
	public void testGetType() throws Exception {
		assertEquals("box", interactiveObject.getType());
	}

	@Test
    public void testGetArea() throws Exception {
        assertEquals(new Rectangle2D.Double(32, 32, 32, 32), interactiveObject.getUnitTile());
    }

    @Test
    public void testGetProperties() throws Exception {
        assertEquals(new Properties(), interactiveObject.getProperties());
    }
    
    @Test
    public void testSetPosition() {
        final Double epsilon = 0.001;
        interactiveObject.setPosition(new Vector2D(50, 50));
        assertTrue(interactiveObject.getPosition().getX() - 50.0 < epsilon);
        assertTrue(interactiveObject.getPosition().getY() - 50.0 < epsilon);
    }
    
    @Test
    public void testEquals() {
        assertEquals(interactiveObject, interactiveObject);
        assertNotNull(interactiveObject);
        assertNotSame(32, interactiveObject);
        assertNotSame(interactiveObject, new InteractiveObject());
        final HashMap<String,String> props = new HashMap<>();
        final InteractiveObject newObject = new InteractiveObject("testObject", "box", 32, 32, 32, 32, props);
        assertEquals(interactiveObject, newObject);
    }
    
    @Test
    public void testSerialization() {
        //TODO Error in Jacoco "Error while instrumenting class",
        /*Kryo kryo = new Kryo();
        Output output = new Output(200);
        Utilities.kryoRegister(kryo);
        
        interactiveObject.write(kryo, output);
        InteractiveObject newInstance = new InteractiveObject();
        Input input = new Input(output.getBuffer());
        newInstance.read(kryo, input);
        assertTrue(interactiveObject.equals(newInstance));
        */
    }
}