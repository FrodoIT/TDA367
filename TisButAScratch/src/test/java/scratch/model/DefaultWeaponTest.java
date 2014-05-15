package scratch.model;

import junit.framework.TestCase;
import nl.jqno.equalsverifier.EqualsVerifier;
import scratch.model.weapons.DefaultWeapon;

import java.awt.geom.Rectangle2D;

/**
 * Created by Anna on 2014-05-02.
 */
public class DefaultWeaponTest extends TestCase {
	DefaultWeapon weapon;
	public void setUp() throws Exception {
		super.setUp();
		weapon=new DefaultWeapon();

	}

	public void testAttack() throws Exception {
		assertTrue(weapon.hasCooledDown()==true);
		weapon.startCooldown();
		assertTrue(weapon.hasCooledDown()==false);
		int okMarginOfError = 15;
		long start = System.currentTimeMillis();
		do {

		}
		while(System.currentTimeMillis() < start + weapon.getAttackInterval()+okMarginOfError);

		assertTrue(weapon.hasCooledDown()==true);
	}

	public void testGetRange() throws Exception{
		assertTrue(weapon.getRange()==1);
	}
	public void testGetAttackArea() throws Exception{
		assertTrue(weapon.getAttackArea().intersects(0,0,32,32));
	}

	public void testEquals() throws Exception{
		//EqualsVerifier.forClass(DefaultWeapon.class).verify();
        //TODO halp...
	}
	public void testHashCode() throws Exception{
		DefaultWeapon weapon1 = new DefaultWeapon();
		if(weapon1.equals(weapon)){
			assertTrue(weapon1.hashCode()==weapon.hashCode());
		}
		weapon1.startCooldown();
		if(weapon1.equals(weapon)){
			assertTrue(weapon1.hashCode()==weapon.hashCode());
		}
	}

}
