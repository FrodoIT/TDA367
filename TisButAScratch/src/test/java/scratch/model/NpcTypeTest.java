package scratch.model;

import com.google.inject.Guice;
import com.google.inject.Injector;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import scratch.model.mockmodules.MockModule;
import scratch.model.weapons.Weapon;
import scratch.model.weapons.IWeapon;

import java.awt.geom.Rectangle2D;


public class NpcTypeTest extends TestCase {
	private NpcType npcType;


    @Before
	public void setUp() {
        final Injector injector = Guice.createInjector(new MockModule());
        final IPlayerInput playerInput = injector.getInstance(IPlayerInput.class);
        final IWeapon weapon= injector.getInstance(IWeapon.class);
        final IMap map = injector.getInstance(IMap.class);
        final GameCharacter player = new GameCharacter(new Rectangle2D.Double(0, 0, 32, 32), new Weapon(),10, 2, 0, "/res/monster.tmx");
        final INPCMove npcMove = injector.getInstance(INPCMove.class);
        final Room room = new Room(map, new DoorHandler());
        room.addCharacter(player);
		npcType = new NpcType(new Rectangle2D.Double(10,0,32,32),
                weapon, 1, 1, "/res/playerSprite.tmx", 0, npcMove);
	}

    @Test
	public void testTakeDamage() {
		npcType.takeDamage(2);
		assertEquals(0, npcType.getHealth());
	}

    @Test
	public void testSetPosition() {
		npcType.setPosition(new Vector2D(-1,-1));
		assertTrue(npcType.getPosition().equals(new Vector2D(-1,-1)));
	}


    @Test
    public void testUpdate() {
        assertDirectionForMovement(new Vector2D(50, 50), MoveDirection.NONE);
        assertDirectionForMovement(new Vector2D(40, 50), MoveDirection.EAST);
        assertDirectionForMovement(new Vector2D(60, 50), MoveDirection.WEST);
        assertDirectionForMovement(new Vector2D(50, 40), MoveDirection.SOUTH);
        assertDirectionForMovement(new Vector2D(50, 70), MoveDirection.NORTH);
        assertDirectionForMovement(new Vector2D(60, 70), MoveDirection.NORTHWEST);
        assertDirectionForMovement(new Vector2D(40, 70), MoveDirection.NORTHEAST);
        assertDirectionForMovement(new Vector2D(60, 40), MoveDirection.SOUTHWEST);
        assertDirectionForMovement(new Vector2D(45, 40), MoveDirection.SOUTHEAST);
    }

    private void assertDirectionForMovement(Vector2D npcPosition, MoveDirection direction) {
        npcType.setPosition(npcPosition);
        npcType.update();
        assertSame(npcType.getMoveDirection(), direction);
    }
}


