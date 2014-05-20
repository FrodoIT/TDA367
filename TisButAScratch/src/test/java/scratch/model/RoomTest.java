package scratch.model;

import com.google.inject.Guice;
import com.google.inject.Injector;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import scratch.model.mockmodules.MockIMap;
import scratch.model.mockmodules.MockModule;
import scratch.model.mockmodules.MockWeapon;
import scratch.model.weapons.Weapon;

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
    private GameCharacter createPlayerForTest(int id) {
        final IPlayerInput playerInput = injector.getInstance(IPlayerInput.class);
        playerInput.setAttackStatus(true);
        playerInput.setInteractStatus(true);
        return new GameCharacter(new Rectangle2D.Double(50, 50, 100, 100), new Weapon(), 10, 2, id, "/res/playerSprite.tmx");
    }

    @Test
    public void testUpdate() {
        final GameCharacter testPlayer = createPlayerForTest(1);
        room.addCharacter(testPlayer);
        testPlayer.setMovementSpeed(0);
        final INPCMove move = injector.getInstance(INPCMove.class);
        NpcType testNpc = new NpcType(new Rectangle2D.Double(50, 55, 100, 100),
                new MockWeapon(), 100, 0, "test", 10, move);
        testNpc.setNextMoveDirection(new Vector2D());
        putCharactersInRoom(testPlayer, testNpc);
        assertTrue(room.isActive());
        testNpc.registerListener(room);
        testPlayer.interact();
        testNpc.update();
        testPlayer.setAttacking(true);
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

    private void putCharactersInRoom(GameCharacter testPlayer, NpcType testNpc) {
        room.addCharacter(testNpc);
        room.addCharacter(testPlayer);
    }


    @Test
    public void testRemoveNoneExistingCharacter() {
        final GameCharacter player = createPlayerForTest(0);
        final boolean removed = room.removeCharacter(player);
        assertFalse(removed);
    }

}
