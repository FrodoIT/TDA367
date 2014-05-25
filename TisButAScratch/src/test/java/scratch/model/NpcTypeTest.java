package scratch.model;

import com.google.inject.Guice;
import com.google.inject.Injector;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import scratch.construction.plugin.exported.CPNPCPlugin;
import scratch.model.mockmodules.MockModule;
import scratch.model.weapons.Weapon;

import java.awt.geom.Rectangle2D;

public class NpcTypeTest extends TestCase {

    private NpcType npcType;
    private GameCharacter player;

    @Before
    public void setUp() {
        final Injector injector = Guice.createInjector(new MockModule());
        final IMap map = injector.getInstance(IMap.class);
        player = new GameCharacter(new Rectangle2D.Double(0, 0, 32, 32), new Weapon(), 10, 2, 0, "/res/monster.tmx");
        final INPCMove npcMove = injector.getInstance(INPCMove.class);
        final Room room = new Room(map, new DoorHandler());
        final Weapon weapon = new Weapon();
        room.addCharacter(player);
        npcType = new NpcType(new Rectangle2D.Double(10, 0, 32, 32),
                weapon, 1, 1, "/res/playerSprite.tmx", 0, npcMove);
    }

    @Test
    public void testTakeDamage() {
        npcType.takeDamage(2);
        assertEquals(0, npcType.getHealth());
    }

    @Test
    public void testSetPosition() {
        npcType.setPosition(new Vector2D(-1, -1));
        assertTrue(npcType.getPosition().equals(new Vector2D(-1, -1)));
    }

    @Test
    public void testUpdate() {
        assertDirectionForMovement(new Vector2D(50, 50), Direction.NONE);
        assertDirectionForMovement(new Vector2D(40, 50), Direction.EAST);
        assertDirectionForMovement(new Vector2D(60, 50), Direction.WEST);
        assertDirectionForMovement(new Vector2D(50, 40), Direction.SOUTH);
        assertDirectionForMovement(new Vector2D(50, 70), Direction.NORTH);
        assertDirectionForMovement(new Vector2D(60, 70), Direction.NORTHWEST);
        assertDirectionForMovement(new Vector2D(40, 70), Direction.NORTHEAST);
        assertDirectionForMovement(new Vector2D(60, 40), Direction.SOUTHWEST);
        assertDirectionForMovement(new Vector2D(45, 40), Direction.SOUTHEAST);
    }

    private void assertDirectionForMovement(Vector2D npcPosition, Direction direction) {
        npcType.setPosition(npcPosition);
        npcType.update();
        assertSame(npcType.getMoveDirection(), direction);
    }

    @Test
    public void testAttacking() {
        player.setPosition(new Vector2D(20, 20));
        npcType.setPosition(new Vector2D(30, 30));
        npcType.update();
        assertTrue(npcType.isAttacking());
    }

    @Test
    public void testSetMovementPattern() {
        final INPCMove newMovementPattern = new CPNPCPlugin();
        npcType.setMovementPattern(newMovementPattern);
        assertTrue(npcType.getMovementPattern().equals(newMovementPattern));

    }
}
