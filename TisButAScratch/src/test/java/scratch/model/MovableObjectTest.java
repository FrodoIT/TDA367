package scratch.model;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import static junit.framework.TestCase.assertTrue;

/**
 * Created by pippin on 5/23/14.
 */
public class MovableObjectTest extends TestCase {

    private MovableObject movableObject;

    @Before
    public void setUp() {
        final HashMap<String, String> props = new HashMap<>();
        movableObject = new MovableObject("testObject", "box", 32, 32, 32, 32, props);
    }

    @Test
    public void testGetMoveDirection() throws Exception {
        movableObject.setMoveDirection(Direction.EAST);
        assertSame(movableObject.getMoveDirection(), Direction.EAST);
    }

    @Test
    public void testWrite() throws Exception {

    }

    @Test
    public void testRead() throws Exception {

    }

    @Test
    public void testEquals() throws Exception {
        assertEquals(movableObject, movableObject);
        assertNotNull(movableObject);
        assertNotSame(32, movableObject);
        assertNotSame(movableObject, new MovableObject());
        final HashMap<String, String> props = new HashMap<>();
        final MovableObject newObject = new MovableObject("testObject", "box", 32, 32, 32, 32, props);
        assertEquals(movableObject, newObject);
        assertEquals(movableObject.hashCode(), newObject.hashCode());
    }

    @Test
    public void testSerialization() {
        //TODO Error in Jacoco "Error while instrumenting class",
        Kryo kryo = new Kryo();
        Output output = new Output(200);

        movableObject.write(kryo, output);
        MovableObject object = new MovableObject();
        Input input = new Input(output.getBuffer());
        object.read(kryo, input);
        assertTrue(movableObject.equals(object));
    }
}
