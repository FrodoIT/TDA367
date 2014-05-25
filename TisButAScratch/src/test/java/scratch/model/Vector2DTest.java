package scratch.model;

import junit.framework.TestCase;
import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.Test;

import java.awt.geom.Point2D;
import org.junit.Before;
import scratch.model.weapons.Weapon;

/**
 * Created by pippin on 4/30/14.
 */
public class Vector2DTest extends TestCase {

    final private double epsilon = Math.pow(10, -10);
    private Vector2D vector;
    private Vector2D vectorPoint;

    @Before
    @Override
    public void setUp() throws Exception {
        super.setUp();
        final Point2D.Double startPoint = new Point2D.Double(1, 0);
        final Point2D.Double endPoint = new Point2D.Double(6, 7);
        vector = new Vector2D(startPoint, endPoint);
        vectorPoint = new Vector2D(1, 5);
    }


    /**
     * The normalised vector should have magnitude 1 but now has a magnitude
     * that is virtually 1 but still a decimal number.
     *
     * @throws Exception
     */
    @Test
    public void testGetNormalisedVector() throws Exception {
        assertEquals(1.d, vector.getNormalisedVector().getMagnitude(), epsilon);
    }

    @Test
    public void testGetX() throws Exception {
        assertEquals(1.d, vectorPoint.getX(), epsilon);
    }

    @Test
    public void testGetY() throws Exception {
        assertEquals(5.d, vectorPoint.getY(), epsilon);
    }

    public void testEmptyConstructor() {
        final Vector2D vector2D = new Vector2D();
        assertEquals(vector2D.getMagnitude(), 0.d);
        assertEquals(vector2D.getX(), 0.d);
        assertEquals(vector2D.getY(), 0.d);
    }

    @Test
    public void testEquals() {
        EqualsVerifier.forClass(Vector2D.class).verify();
    }

    @Test
    public void testDistance() {
        final Vector2D toPoint = new Vector2D(5, 8);
        assertEquals(5.d, vectorPoint.distance(toPoint), epsilon);

    }
}
