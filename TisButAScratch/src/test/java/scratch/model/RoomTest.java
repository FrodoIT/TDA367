package scratch.model;

import com.google.inject.Guice;
import com.google.inject.Injector;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import scratch.model.mockmodules.MockIMap;
import scratch.model.mockmodules.MockModule;
import scratch.model.mockModules.MockIWeapon;

import java.awt.geom.Rectangle2D;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by Anna on 2014-05-02.
 */
public class RoomTest extends TestCase {
	private Room room;
    private final Injector injector = Guice.createInjector(new MockModule());
    private INPCMove move;
    private IInteractiveObject interactiveObject;

	@Before
    @Override
	public void setUp() {
        IMap map = injector.getInstance(MockIMap.class);
        interactiveObject = injector.getInstance(IInteractiveObject.class);
        move = injector.getInstance(INPCMove.class);
		room= new Room(map, new DoorHandler());
	}

	//made to create mock-players used in tests
	private Player createPlayerForTest(int id) {
		final IPlayerInput playerInput = injector.getInstance(IPlayerInput.class);
        playerInput.setAttackStatus(true);
        playerInput.setInteractStatus(true);
		return new Player(playerInput, new Rectangle2D.Double(50,50,100,100),id, "/res/playerSprite.tmx");
	}


    @Test
    public void testUpdate() {
        final Player testPlayer = createPlayerForTest(1);
        room.enterRoom(testPlayer);
        testPlayer.setMovementSpeed(0);
        //System.out.println(interactiveObject);
       // room.addInteractivObject(interactiveObject);
        NpcType testNpc = new NpcType(new Rectangle2D.Double(50,55,100,100),
                new MockIWeapon(), 100, 0, "test", 10, move, room);
        testNpc.setMoveDirection(MoveDirection.NONE);
        putCharactersInRoom(testPlayer, testNpc);
        assertTrue(room.hasPlayers());
        assertFalse(room.getNpcs().isEmpty());
        testNpc.registerListener(room);
        testPlayer.interact();
        testNpc.update();
        testPlayer.update();
        System.out.println(room.getAreaUnderAttack().size());
        assertFalse(room.getCharacterMovementMap().isEmpty());
        assertFalse(room.getAreaUnderAttack().isEmpty());
        assertFalse(room.getCharacterInteractAreaMap().isEmpty());
        assertFalse(room.getCharacterMovementMap().isEmpty());
        room.update();
        assertTrue(room.getAreaUnderAttack().isEmpty());
        assertTrue(room.getCharacterInteractAreaMap().isEmpty());
        assertTrue(room.getCharacterMovementMap().isEmpty());
    }

    private void putCharactersInRoom(Player testPlayer, NpcType testNpc) {
        room.addNpc(testNpc);
        room.enterRoom(testPlayer);
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
