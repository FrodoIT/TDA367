package scratch.model;

import com.google.inject.Guice;
import com.google.inject.Injector;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import scratch.model.mockModules.MockModule;
import scratch.model.mockModules.MockPlayerInput;

import java.awt.geom.Rectangle2D;

public class ModelTest extends TestCase {

	private Game m;
	private Player p;
	private Injector injector = Guice.createInjector(new MockModule());

	@Before
	public void initialize() throws Exception {
		m = new Game();

		IPlayerInput playerInput = injector.getInstance(MockPlayerInput.class);
		p = new Player(playerInput, new Rectangle2D.Double(0, 0, 32, 32), 0);
	}

	@Test
	public void testAddPlayer() throws Exception {
		initialize();
		m.addPlayer(p);
		assertTrue(m.getPlayers().contains(p));

	}

	@Test
	public void testRemovePlayer() throws Exception {
		initialize();
		System.out.println("player" + p);
		System.out.println("model" + m);
		m.addPlayer(p);
		m.removePlayer(p);
		assertTrue(m.getPlayers().isEmpty());
	}

	@Test
	public void testSetMap() throws Exception {
		//TODO currently untestable... you're not able to se any result
		assertTrue(false);
	}

	@Test
	public void testUpdate() throws Exception {
		//TODO can't figure out anything to test. the update method just forwards the update to active rooms
	}
}