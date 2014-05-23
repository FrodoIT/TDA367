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



public class InteractiveObjectTest extends TestCase {

    private InteractiveObject interactiveObject;

    @Before
    public void setUp() {
        HashMap<String,String> props = new HashMap<>();
        interactiveObject = new InteractiveObject("testObject", "box", 32, 32, 32, 32, props);
    }
    
    @Test
    public void testSetObject() {
        InteractiveObject cleanInteractiveObject = new InteractiveObject();
        cleanInteractiveObject.setObject(interactiveObject);
        assertTrue(cleanInteractiveObject.getProperties().equals(interactiveObject.getProperties()));
        assertTrue(cleanInteractiveObject.getName().equals(interactiveObject.getName()));
        assertTrue(cleanInteractiveObject.getType().equals(interactiveObject.getType()));
        assertTrue(cleanInteractiveObject.getUnitTile().equals(interactiveObject.getUnitTile()));
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
    public void testUpdate() {
        //Not certain why this class has update method, method should probably be removed from class. 
        interactiveObject.update();
        assertTrue(true);
    }
    
    @Test
    public void testEquals() {
        assertTrue(interactiveObject.equals(interactiveObject));
        assertFalse(interactiveObject.equals(null));
        assertFalse(interactiveObject.equals(32));
        assertFalse(interactiveObject.equals(new InteractiveObject()));
    }
    
    @Test
    public void testSerialization() {
        Kryo kryo = new Kryo();
        Output output = new Output(200);
        
        interactiveObject.write(kryo, output);
        InteractiveObject newInstance = new InteractiveObject();
        Input input = new Input(output.getBuffer());
        newInstance.read(kryo, input);
        assertTrue(interactiveObject.equals(newInstance));
    }
}