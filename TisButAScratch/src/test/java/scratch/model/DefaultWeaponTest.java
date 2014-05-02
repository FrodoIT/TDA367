package scratch.model;

import junit.framework.TestCase;
import org.junit.Test;
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

	public void tearDown() throws Exception {

	}

	public void testAttack() throws Exception {
		assertTrue(weapon.isCooledDown()==true);
		weapon.attack();
		assertTrue(weapon.isCooledDown()==false);
		int okMarginOfError = 15;
		long start = System.currentTimeMillis();
		do {

		}
		while(System.currentTimeMillis() < start + weapon.getAttackInterval()+okMarginOfError);

		assertTrue(weapon.isCooledDown()==true);
	}
}
