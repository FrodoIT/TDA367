package scratch.model;

import com.google.inject.Guice;
import com.google.inject.Injector;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import scratch.model.mockmodules.MockIMap;
import scratch.model.mockmodules.MockModule;
import scratch.model.weapons.Weapon;

import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;

public class GameTest extends TestCase {

	private Game game;
    private final List<Room> rooms = new ArrayList<>();
    private GameCharacter player;
    private NpcType npcType;
	private final Injector injector = Guice.createInjector(new MockModule());

	@Before
	public void setUp() {
        game = new Game();
        rooms.add(new Room(new MockIMap(), new DoorHandler()));

        npcType = new NpcType(new Rectangle2D.Double(0, 0, 32, 32),
                new Weapon(),
                10,
                2,
                "",
                42,
                injector.getInstance(INPCMove.class)
        );

        rooms.get(0).addCharacter(npcType);

        game.setMap(rooms);
        player = new GameCharacter(new Rectangle2D.Double(0, 0, 32, 32), new Weapon(),10, 2, 0, "/res/monster.tmx");
    }

	@Test
	public void testAddPlayer() {
		game.addPlayer(player);
		assertTrue(game.getPlayers().contains(player));

	}

    public void testAddPlayerWithoutRooms() {
        Game game = new Game();
        assertFalse(game.addPlayer(player));
        game.setMap(new ArrayList<Room>());
        assertFalse(game.addPlayer(player));
    }

	@Test
	public void testRemovePlayer() {
		game.addPlayer(player);
		game.removePlayer(player);
		assertTrue(game.getPlayers().isEmpty());
	}

    @Test
    public void testGetActiveRoom() {
        assertSame(rooms, game.getRooms());
    }

	@Test
	public void testUpdate() {
		//TODO can't figure out anything to test. the update method just forwards the update to active rooms
	}
}