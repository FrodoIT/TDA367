package scratch.model;

import com.google.inject.Guice;
import com.google.inject.Injector;
import junit.framework.TestCase;
import org.junit.Test;
import scratch.model.mockModules.MockModule;
import scratch.model.weapons.IWeapon;

import java.awt.geom.Rectangle2D;


public class NpcTypeTest extends TestCase {
	private NpcType npcType;


	public void setUp() throws Exception {
        Injector injector = Guice.createInjector(new MockModule());
        IPlayerInput playerInput = injector.getInstance(IPlayerInput.class);
		IWeapon weapon= injector.getInstance(IWeapon.class);
        IMap map = injector.getInstance(IMap.class);
        Player player = new Player(playerInput , new Rectangle2D.Double(50,50,32,32), 1, "/res/monster.tmx");
        INPCMove npcMove = injector.getInstance(INPCMove.class);
        Room room = new Room(map, new DoorHandler());
        room.enterRoom(player);
		npcType = new NpcType(new Rectangle2D.Double(10,0,32,32),
                weapon, 1, 1, "/res/playerSprite.tmx", 0, npcMove, room);
	}

    @Test
	public void testTakeDamage() throws Exception {
		npcType.takeDamage(2);
		assertTrue(npcType.getHealth()==(0));
	}

    @Test
	public void testSetPosition() throws Exception {
		npcType.setPosition(new Vector2D(-1,-1));
		assertTrue(npcType.getPosition().equals(new Vector2D(-1,-1)));
	}


    @Test
    public void testUpdate() throws Exception {
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
        assertTrue(npcType.getMoveDirection() == (direction));
    }
}


