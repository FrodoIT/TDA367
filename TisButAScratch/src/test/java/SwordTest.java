import static org.junit.Assert.*;

import org.junit.Test;


public class SwordTest {

	@Test
	public void testSword() {
		Sword sword = new Sword();
		assertTrue(!sword.equals(null));
	}

	@Test
	public void testSwordIntInt() {
		Sword sword = new Sword(9, 10);
		assertTrue(sword.getDamage() == 9);
		assertTrue(sword.getRange() == 10);
	}

	@Test
	public void testGetDamage() {
		Sword sword = new Sword();
		assertTrue(sword.getDamage() == 5);
	}

	@Test
	public void testGetRange() {
		Sword sword = new Sword();
		assertTrue(sword.getRange() == 1);
	}
	
	
	
	

}
