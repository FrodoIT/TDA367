import static org.junit.Assert.*;

import java.awt.Point;

import org.junit.Test;


public class PlayerTest {

	@Test
	public void testPlayer() {
		Player player = new Player();
	}

	@Test
	public void testPlayerPoint() {
		Player player = new Player(new Point(30, 30));
	}
	
	@Test
	public void testGetPos() {
		Player player = new Player(new Point (25, 25));
		assertTrue(player.getPos().equals(new Point(25, 25)));
		player = new Player();
		assertTrue(player.equals(new Point(10, 10)));
	}
	
	@Test
	public void testGetDamage() {
		Player player = new Player();
		assertTrue(player.getDamage() == 2);
	}
	
	@Test
	public void testGetRange() {
		Player player = new Player();
		assertTrue(player.getRange() == 1);
	}
	
	
}
