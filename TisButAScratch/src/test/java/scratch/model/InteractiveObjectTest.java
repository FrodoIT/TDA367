package scratch.model;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import java.awt.geom.Rectangle2D;
import java.util.HashMap;
import java.util.Properties;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import scratch.network.Utilities;



public class InteractiveObjectTest extends TestCase {

    private InteractiveObject interactiveObject;

    @Before
    public void setUp(){
        HashMap<String,String> props = new HashMap<>();
        interactiveObject = new InteractiveObject("testObject", "box", 32, 32, 32, 32, props);
    }
    
    @Test

    public void testPosition(){
	    interactiveObject.setPosition(new Vector2D(20,20));
	    assertTrue(interactiveObject.getPosition().equals(new Vector2D(20,20)));
    }
	@Test
	public void testSetObject(){
		InteractiveObject newInteractiveObject = new InteractiveObject("newTestObject", "door",0,0,0,0, new HashMap());
		interactiveObject.setObject(newInteractiveObject);
		System.out.println(interactiveObject + " " + newInteractiveObject);
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
        Double epsilon = 0.001;
        interactiveObject.setPosition(new Vector2D(50, 50));
        assertTrue(interactiveObject.getPosition().getX() - 50.0 < epsilon);
        assertTrue(interactiveObject.getPosition().getY() - 50.0 < epsilon);
    }
    
    @Test
    public void testEquals() {
        assertTrue(interactiveObject.equals(interactiveObject));
        assertFalse(interactiveObject.equals(null));
        assertFalse(interactiveObject.equals(32));
        assertFalse(interactiveObject.equals(new InteractiveObject()));
        HashMap<String,String> props = new HashMap<>();
        InteractiveObject newObject = new InteractiveObject("testObject", "box", 32, 32, 32, 32, props);
        assertTrue(interactiveObject.equals(newObject));
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