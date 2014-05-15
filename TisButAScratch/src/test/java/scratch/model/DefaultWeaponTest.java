package scratch.model;

import junit.framework.TestCase;
import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.Before;
import org.junit.Test;
import scratch.model.weapons.DefaultWeapon;

/**
 * Created by Anna on 2014-05-02.
 */
public class DefaultWeaponTest extends TestCase {
	private DefaultWeapon weapon;

    @Before
    @Override
    public void setUp() throws Exception {
		super.setUp();
		weapon=new DefaultWeapon();

	}

    @Test
	public void testAttack() throws Exception {
		assertTrue(weapon.hasCooledDown());
		weapon.startCooldown();
		assertFalse(weapon.hasCooledDown());
		final int okMarginOfError = 15;
		final long start = System.currentTimeMillis();
		do {

		}
		while(System.currentTimeMillis() < start + weapon.getAttackInterval()+okMarginOfError);

		assertTrue(weapon.hasCooledDown());
	}

    @Test
	public void testGetRange() throws Exception{
		assertSame(weapon.getRange(), 1);
	}

    @Test
	public void testGetAttackArea() throws Exception{
		assertTrue(weapon.getAttackArea().intersects(0, 0, 32, 32));
	}

    @Test
	public void testEquals() throws Exception{
		EqualsVerifier.forClass(DefaultWeapon.class).verify();
	}

    @Test
	public void testHashCode() throws Exception{
		final DefaultWeapon weapon1 = new DefaultWeapon();
		if(weapon1.equals(weapon)){
			assertSame(weapon1.hashCode(), weapon.hashCode());
		}
		weapon1.startCooldown();
		if(weapon1.equals(weapon)){
			assertSame(weapon1.hashCode(), weapon.hashCode());
		}
	}

}
