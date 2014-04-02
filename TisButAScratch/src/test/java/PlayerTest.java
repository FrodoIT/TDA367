import static org.junit.Assert.*;

import java.awt.Point;

import model.MoveDirection;
import model.Player;

import org.junit.Test;

/**
 * 
 * @author Ivar 'Cannonbait' Josefsson
 *
 */
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
		assertTrue(player.getPosition().equals(new Point(320, 240)));
		player = new Player(new Point (25, 25));
		assertTrue(player.getPosition().equals(new Point(25, 25)));		
	}
	
	@Test
	public void testGetMovementSpeed() {
		Player player = new Player();
		assertTrue(player.getMovementSpeed() == 2);
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
	public void testCalculateNewPosition() {
		Player player = new Player(new Point(10, 10));
		assertTrue(player.calculateNewPosition(MoveDirection.SOUTH).equals(new Point(10, 10+player.getMovementSpeed())));
	}
	
	@Test
	public void testMove() {
		Player player = new Player(new Point(10, 10));
		player.move(MoveDirection.SOUTH);
		assertTrue(player.getPosition().equals(new Point(10, 10+player.getMovementSpeed())));
		player.move(MoveDirection.NORTH);
		assertTrue(player.getPosition().equals(new Point(10, 10)));
		player.move(MoveDirection.EAST);
		assertTrue(player.getPosition().equals(new Point(10+player.getMovementSpeed(), 10)));
		player.move(MoveDirection.WEST);
		assertTrue(player.getPosition().equals(new Point(10, 10)));
	}
}
