package scratch.model;

import com.google.inject.Guice;
import com.google.inject.Injector;
import junit.framework.TestCase;
import org.junit.Test;
import scratch.model.mockModules.MockModule;
import scratch.model.weapons.DefaultWeapon;
import scratch.model.weapons.IWeapon;

import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;


/**
 * Created by Anna on 2014-05-02.
 */
public class NpcTypeTest extends TestCase {
	private NpcType npcType;
	private INPCMove npcMove;
    private Player player;
    private Room room;


	public void setUp() throws Exception {
        Injector injector = Guice.createInjector(new MockModule());
        IPlayerInput playerInput = injector.getInstance(IPlayerInput.class);
        player = new Player(playerInput , new Rectangle2D.Double(50,50,32,32), 1);
		IWeapon weapon= injector.getInstance(IWeapon.class);
		npcMove = injector.getInstance(INPCMove.class);
        IMap map = injector.getInstance(IMap.class);
        room = new Room(map, new DoorHandler());
        room.enterRoom(player);
		npcType = new NpcType(new Rectangle2D.Double(10,0,32,32),
                weapon, 1, 1, "/res/playerSprite.tmx", 0, npcMove, room);
	}

    @Test
	public void testTakeDamage() throws Exception {
        assertTrue(npcType.getHealth() == 1);
		npcType.takeDamage(2);
		assertTrue(npcType.getHealth()==(0));
	}

    @Test
	public void testSetPosition() throws Exception {
		npcType.setPosition(new Vector2D(10,10));
		assertTrue(npcType.getPosition().equals(new Vector2D(10,10)));
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
        assertTrue(npcType.getMoveDirection().equals(direction));
    }

    @Test
    public void testEquals() {
        NpcType testNpc = new NpcType(new Rectangle2D.Double(10,0,32,32),
                new DefaultWeapon(), 1, 1, "/res/playerSprite.tmx", 0, npcMove, room);

        assertTrue(testNpc.equals(testNpc));
        assertFalse(testNpc.equals(null));

        NpcType otherNpc = new NpcType (new Rectangle2D.Double(10,0,32,32),
                new DefaultWeapon(), 2, 2, "/res/playerSprite.tmx", 0, npcMove, room);

        assertFalse (testNpc.equals(otherNpc));
        otherNpc = new NpcType(new Rectangle2D.Double(10,0,32,32),
                new DefaultWeapon(), 1, 1, "/res/playerSprite.tmx", 0, npcMove, room);

        assertTrue(testNpc.equals(otherNpc));
    }
}
