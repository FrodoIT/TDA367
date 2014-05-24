package scratch.model;

import junit.framework.TestCase;
import nl.jqno.equalsverifier.EqualsVerifier;
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
        HashMap<String,String> props = new HashMap<>();
        movableObject = new MovableObject("testObject", "box", 32, 32, 32, 32, props);
    }

    @Test
    public void testGetMoveDirection() throws Exception {
        movableObject.setMoveDirection(Direction.EAST);
        assertTrue(movableObject.getMoveDirection() == Direction.EAST);
    }

    @Test
    public void testWrite() throws Exception {

    }

    @Test
    public void testRead() throws Exception {

    }

    @Test
    public void testEquals() throws Exception {
        assertTrue(movableObject.equals(movableObject));
        assertFalse(movableObject.equals(null));
        assertFalse(movableObject.equals(32));
        assertFalse(movableObject.equals(new MovableObject()));
        HashMap<String,String> props = new HashMap<>();
        MovableObject newObject = new MovableObject("testObject", "box", 32, 32, 32, 32, props);
        assertTrue(movableObject.equals(newObject));
        assertTrue(movableObject.hashCode() == newObject.hashCode());
    }
}
