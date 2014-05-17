package scratch.model;

import com.google.inject.Guice;
import com.google.inject.Injector;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import scratch.model.mockModules.MockIWeapon;
import scratch.model.mockmodules.MockIMap;
import scratch.model.mockmodules.MockModule;

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
        IMap map = injector.getInstance(MockIMap.class);
        room = new Room(map, new DoorHandler());
    }

    //made to create mock-players used in tests
    private Player createPlayerForTest(int id) {
        final IPlayerInput playerInput = injector.getInstance(IPlayerInput.class);
        playerInput.setAttackStatus(true);
        playerInput.setInteractStatus(true);
        return new Player(playerInput, new Rectangle2D.Double(50, 50, 100, 100), id, "/res/playerSprite.tmx");
    }

    @Test
    public void testUpdate() {
        final Player testPlayer = createPlayerForTest(1);
        room.addCharacter(testPlayer);
        testPlayer.setMovementSpeed(0);
        final INPCMove move = injector.getInstance(INPCMove.class);
        NpcType testNpc = new NpcType(new Rectangle2D.Double(50, 55, 100, 100),
                new MockIWeapon(), 100, 0, "test", 10, move, room);
        testNpc.setMoveDirection(MoveDirection.NONE);
        putCharactersInRoom(testPlayer, testNpc);
        assertTrue(room.isActive());
        testNpc.registerListener(room);
        testPlayer.interact();
        testNpc.update();
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

    private void putCharactersInRoom(Player testPlayer, NpcType testNpc) {
        room.addCharacter(testNpc);
        room.addCharacter(testPlayer);
    }


    @Test
    public void testRemoveNoneExistingCharacter() {
        final Player player = createPlayerForTest(0);
        final boolean removed = room.removeCharacter(player);
        assertFalse(removed);
    }

}
