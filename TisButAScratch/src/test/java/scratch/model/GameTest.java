package scratch.model;

import com.google.inject.Guice;
import com.google.inject.Injector;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import scratch.model.mockModules.MockIMap;
import scratch.model.mockModules.MockModule;
import scratch.model.mockModules.MockPlayerInput;

import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;

public class GameTest extends TestCase {

	private Game game;
	private Player player;
	private final Injector injector = Guice.createInjector(new MockModule());

	@Before
	public void initialize() {
        game = new Game();
        final List<Room> rooms = new ArrayList<>();
        rooms.add(new Room(new MockIMap(), new DoorHandler()));
        game.setMap(rooms);
        final IPlayerInput playerInput = injector.getInstance(MockPlayerInput.class);
        player = new Player(playerInput, new Rectangle2D.Double(0, 0, 32, 32), 0, "/res/monster.tmx");
    }

	@Test
	public void testAddPlayer() {
		initialize();
		game.addPlayer(player);
		assertTrue(game.getPlayers().contains(player));

	}

	@Test
	public void testRemovePlayer() {
		initialize();
		game.addPlayer(player);
		game.removePlayer(player);
		assertTrue(game.getPlayers().isEmpty());
	}


	@Test
	public void testUpdate() {
		//TODO can't figure out anything to test. the update method just forwards the update to active rooms
	}
}