import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.awt.Point;

import model.MoveCommand;
import model.Room;

import org.junit.Test;

/**
 * 
 * @author Ivar Cannonbait Josefsson
 *
 */
public class RoomTest {

	@Test
	public void testRoom() {
		Room room = new Room();
		assertTrue(room.getPlayerPosition().equals(new Point(320, 240)));
	}

	@Test
	public void testIsCompleted() {
		fail("Not yet implemented");
	}


	@Test
	public void testGetPlayerPosition() {
		Room room = new Room();
		assertTrue(room.getPlayerPosition().equals(new Point(320, 240)));
	}

	@Test
	public void testUpdate() {
		fail("Not yet implemented");
		
	}

}
