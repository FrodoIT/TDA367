package scratch.model;

import com.google.inject.*;
import junit.framework.TestCase;
import org.junit.Before;

import java.awt.geom.Rectangle2D;

/**
 * Created by Anna on 2014-05-02.
 */
public class RoomTest extends TestCase {
	private IMap map;
	private Room room;

	@Before
	public void setUp() {
		Injector injector = Guice.createInjector();
		map = injector.getInstance(IMap.class);
		room= new Room(map);
	}

	public void tearDown() throws Exception {

	}

	public void testUpdate() throws Exception {

	}

	public void testEnterRoom() throws Exception {
		assertTrue(room.getPlayers().isEmpty());
		Player player1 = createPlayerForTest(0);
		Player player2 = createPlayerForTest(0);
		room.enterRoom(player1);
		room.enterRoom(player2);
		assertTrue(room.getPlayers().contains(player1) && room.getPlayers().contains(player2));
	}

	private Player createPlayerForTest(int id) {
		Injector injector = Guice.createInjector();
		IPlayerInput playerInput = injector.getInstance(IPlayerInput.class);
		return new Player(playerInput, new Rectangle2D.Double(0,0,32,32),id);
	}

	public void testExitRoom() throws Exception {

	}

	public void testGetPlayers() throws Exception {

	}

	public void testGetNpcs() throws Exception {

	}

	public void testGetMap() throws Exception {

	}
}
