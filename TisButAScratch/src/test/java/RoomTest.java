import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.awt.Point;

import model.PlayerCommand;
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
		Room room = new Room();
		room.update(PlayerCommand.GO_SOUTH);
		assertTrue(room.getPlayerPosition().equals(new Point(320, 242)));
	}

}
