package scratch.model;

import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

/**
 * Created by pippin on 5/23/14.
 */
public class MovableObjectTest extends TestCase {
    private MovableObject movableObject;

    @Before
    public void setUp() {
        final HashMap<String,String> props = new HashMap<>();
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
        assertEquals(movableObject, movableObject)  ;
        assertNotNull(movableObject);
        assertNotSame(32, movableObject);
        assertFalse(movableObject.equals(null));
        assertNotSame(movableObject, new MovableObject());
        final HashMap<String,String> props = new HashMap<>();
        final MovableObject newObject = new MovableObject("testObject", "box", 32, 32, 32, 32, props);
        assertEquals(movableObject, newObject);
        assertEquals(movableObject.hashCode(), newObject.hashCode());
    }
}
