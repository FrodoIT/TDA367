import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.awt.Dimension;
import java.awt.Point;

import model.Room;

import org.junit.Test;

/**
 * 
 * @author Ivar Cannonbait Josefsson
 *
 */
public class RoomTest {

	private int [][] roomMap =
		{
			{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
			{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
			{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
			{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
			{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
			{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
			{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
			{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
			{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
		};
	
	@Test
	public void testRoom() {
		Room room = new Room(roomMap, new Dimension(16, 9), new Dimension(32, 32));
		assertTrue(room.getPlayerPosition().equals(new Point(320, 240)));
	}

	@Test
	public void testIsCompleted() {
		fail("Not yet implemented");
	}


	@Test
	public void testGetPlayerPosition() {
		Room room = new Room(roomMap, new Dimension(16, 9), new Dimension(32, 32));
		assertTrue(room.getPlayerPosition().equals(new Point(320, 240)));
	}

	@Test
	public void testUpdate() {
		fail("Not yet implemented");
		
	}

}
