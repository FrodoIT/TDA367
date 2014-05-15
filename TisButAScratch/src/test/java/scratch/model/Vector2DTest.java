package scratch.model;

import junit.framework.TestCase;
import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.Test;

import java.awt.geom.Point2D;

/**
 * Created by pippin on 4/30/14.
 */
public class Vector2DTest extends TestCase {

    final private double epsilon = Math.pow(10, -10);

    @Test
    public void testGetMagnitude() throws Exception {
        final Point2D.Double startPoint = new Point2D.Double(0,0);
        final Point2D.Double endPoint = new Point2D.Double(1,0);
        final Vector2D unitVector = new Vector2D(startPoint, endPoint);
        assertEquals(1.d, unitVector.getMagnitude(), epsilon);

    }

    /**
     * The normalised vector should have magnitude 1 but now has a magnitude that is virtually
     * 1 but still a decimal number.
     * @throws Exception
     */
    @Test
    public void testGetNormalisedVector() throws Exception {
        final Point2D.Double startPoint = new Point2D.Double(1,0);
        final Point2D.Double endPoint = new Point2D.Double(6,9);
        final Vector2D longVector = new Vector2D(startPoint, endPoint);
        assertTrue(longVector.getNormalisedVector().getMagnitude() > 0.99999);
        assertTrue(longVector.getNormalisedVector().getMagnitude() < 1);
    }

    @Test
    public void testGetX() throws Exception {
        final Vector2D vector = new Vector2D(1,5);
        assertEquals(1.d, vector.getX(), epsilon);
    }
    @Test
    public void testGetY() throws Exception {
        final Vector2D vector = new Vector2D(1,4);
        assertEquals(4.d, vector.getY(), epsilon);
    }

    @Test
    public void testEquals(){
        EqualsVerifier.forClass(Vector2D.class).verify();

    }
}
