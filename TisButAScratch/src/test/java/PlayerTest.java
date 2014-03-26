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
		Player player = new Player();
		assertTrue(player.equals(new Point(10, 10)));
		player = new Player(new Point (25, 25));
		assertTrue(player.getPosition().equals(new Point(25, 25)));		
	}
	
	@Test
	public void testGetMovementSpeed() {
		Player player = new Player();
		assertTrue(player.getMovementSpeed() == 5);
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
	
	@Test
	public void testMove() {
		Player player = new Player();
		player.move(Direction.SOUTH);
		assertTrue(player.getPosition().equals(new Point(10, 10+player.getMovementSpeed())));
		player.move(Direction.NORTH);
		assertTrue(player.getPosition().equals(new Point(10, 10)));
		player.move(Direction.EAST);
		assertTrue(player.getPosition().equals(new Point(10+player.getMovementSpeed(), 10)));
		player.move(Direction.WEST);
		assertTrue(player.getPosition().equals(new Point(10, 10)));
	}
}
