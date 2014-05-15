package scratch.model;

import junit.framework.TestCase;
import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.Test;

import java.awt.geom.Point2D;

/**
 * Created by pippin on 4/30/14.
 */
public class Vector2DTest extends TestCase {

    @Test
    public void testGetMagnitude() throws Exception {
        Point2D.Double startPoint = new Point2D.Double(0,0);
        Point2D.Double endPoint = new Point2D.Double(1,0);
        Vector2D unitVector = new Vector2D(startPoint, endPoint);
        assertTrue(unitVector.getMagnitude() == 1);

    }

    /**
     * The normalised vector should have magnitude 1 but now has a magnitude that is virtually
     * 1 but still a decimal number.
     * @throws Exception
     */
    @Test
    public void testGetNormalisedVector() throws Exception {
        Point2D.Double startPoint = new Point2D.Double(1,0);
        Point2D.Double endPoint = new Point2D.Double(6,9);
        Vector2D longVector = new Vector2D(startPoint, endPoint);
        assertTrue(longVector.getNormalisedVector().getMagnitude() > 0.99999);
        assertTrue(longVector.getNormalisedVector().getMagnitude() < 1);
    }

    @Test
    public void testGetX() throws Exception {
        Vector2D vector = new Vector2D(1,5);
        assertTrue(vector.getX() == 1);
    }
    @Test
    public void testGetY() throws Exception {
        Vector2D vector = new Vector2D(1,4);
        assertTrue(vector.getY() == 4);
    }

    @Test
    public void testEquals(){
        EqualsVerifier.forClass(Vector2D.class).verify();

    }
}
