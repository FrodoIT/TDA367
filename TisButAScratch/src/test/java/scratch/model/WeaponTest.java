package scratch.model;

import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import scratch.model.weapons.Weapon;

/**
 * Created by Anna on 2014-05-02.
 */
public class WeaponTest extends TestCase {

    private Weapon weapon;

    @Before
    @Override
    public void setUp() throws Exception {
        super.setUp();
        weapon = new Weapon();

    }

    @Test
    public void testAttack() {
        assertTrue(weapon.hasCooledDown());
        weapon.startCooldown();
        assertFalse(weapon.hasCooledDown());
        final int okMarginOfError = 30;
        final long start = System.currentTimeMillis();
        do {

        } while (System.currentTimeMillis() < start + weapon.getAttackInterval() + okMarginOfError);

        assertTrue(weapon.hasCooledDown());
    }

    @Test
    public void testGetRange() {
        assertEquals(1, weapon.getRange());
    }

    @Test
    public void testGetAttackArea() {
        assertTrue(weapon.getAttackArea().intersects(0, 0, 32, 32));
    }

    @Test
    public void testHashCode() {
        final Weapon weapon1 = new Weapon();
        if (weapon1.equals(weapon)) {
            assertEquals(weapon1.hashCode(), weapon.hashCode());
        }
        weapon1.startCooldown();
        if (weapon1.equals(weapon)) {
            assertEquals(weapon1.hashCode(), weapon.hashCode());
        }
    }

    @Test
    public void testSerialization() {
        weapon = new Weapon(5, 10, 10);
        //TODO Error in Jacoco "Error while instrumenting class",
        /*Kryo kryo = new Kryo();
         Output output = new Output(200);
         kryo.register(java.awt.geom.Point2D.Double.class);
        


         weapon.write(kryo, output);
         Weapon newInstance = new Weapon();
         Input input = new Input(output.getBuffer());
         newInstance.read(kryo, input);
         assertTrue(weapon.equals(newInstance));*/

    }
}
