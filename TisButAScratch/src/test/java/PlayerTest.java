import static org.junit.Assert.*;

import java.awt.Point;

import org.junit.Test;


public class PlayerTest {
	Player player;
	
	@Before
	public void before() {
		player = new Player();
	}
	
	@Test
	public void testPlayer() {
	}

	@Test
	public void testPlayerPoint() {
		Player player = new Player(new Point(30, 30));
	}
	
	@Test
	public void testGetPos() {
		assertTrue(player.equals(new Point(10, 10)));
		Player player = new Player(new Point (25, 25));
		assertTrue(player.getPos().equals(new Point(25, 25)));		
	}
	
	@Test
	public void testGetDamage() {
		assertTrue(player.getDamage() == 2);
	}
	
	@Test
	public void testGetRange() {
		assertTrue(player.getRange() == 1);
	}
	
	@Test
	public void testMove() {
		player.move(Direction.SOUTH);
		assertTrue(player.getPos.equals(new Point(10, 20)));
		player.move(Direction.EAST);
		assertTrue(player.getPos.equals(new Point(20, 20)));
		player.move(Direction.NORTH);
		assertTrue(player.getPos.equals(new Point(20, 10)));
		player.move(Direction.WEST);
		assertTrue(player.getPos.equals(new Point(10, 10)));
	}
	
	
	
	
}
