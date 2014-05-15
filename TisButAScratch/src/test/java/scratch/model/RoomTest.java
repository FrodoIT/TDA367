package scratch.model;

import com.google.inject.Guice;
import com.google.inject.Injector;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import scratch.model.mockModules.MockIMap;
import scratch.model.mockModules.MockModule;

import java.awt.geom.Rectangle2D;

/**
 * Created by Anna on 2014-05-02.
 */
public class RoomTest extends TestCase {
	private Room room;
    private final Injector injector = Guice.createInjector(new MockModule());
	@Before
    @Override
	public void setUp() {
        final IMap map = injector.getInstance(MockIMap.class);
		room= new Room(map, new DoorHandler());
	}

	//made to create mock-players used in tests
	private Player createPlayerForTest(int id) {
		final IPlayerInput playerInput = injector.getInstance(IPlayerInput.class);
		return new Player(playerInput, new Rectangle2D.Double(0,0,32,32),id, "/res/playerSprite.tmx");
	}


    @Test
    public void testUpdate() {

        final Player testPlayer = createPlayerForTest(1);
        room.enterRoom(testPlayer);
        testPlayer.attack();
        testPlayer.interact();
        testPlayer.update();
        assertFalse(room.getCharacterMovementMap().isEmpty());
        assertFalse(room.getAreaUnderAttack().isEmpty());
        assertFalse(room.getCharacterInteractAreaMap().isEmpty());
        assertFalse(room.getCharacterMovementMap().isEmpty());
        room.update();
        assertTrue(room.getAreaUnderAttack().isEmpty());
        assertTrue(room.getCharacterInteractAreaMap().isEmpty());
        assertTrue(room.getCharacterMovementMap().isEmpty());

    }

    @Test
	public void testEnterRoom() {
		final Player player1 = createPlayerForTest(0);
		room.enterRoom(player1);
		assertTrue(room.getPlayers().contains(player1));
	}
    @Test
	public void testExitRoom() {
		final Player player1 = createPlayerForTest(0);
		final Player player2 = createPlayerForTest(1);
		room.enterRoom(player1);
		room.enterRoom(player2);
		room.exitRoom(player1);
		assertFalse(room.getPlayers().contains(player1));
        assertSame(room.getPlayers().get(0).getId(), 1);
		room.exitRoom(player2);
		assertTrue(room.getPlayers().isEmpty());
	}

	@Test(expected=NullPointerException.class)
	public void testExitRoomException() {
		final Player player = createPlayerForTest(0);
		room.exitRoom(player);
		assertTrue(room.getPlayers().isEmpty());
	}

}
