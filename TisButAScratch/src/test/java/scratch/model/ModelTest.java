package scratch.model;

import com.google.inject.Guice;
import com.google.inject.Injector;
import junit.framework.TestCase;
import org.junit.Before;
import scratch.model.mockModules.MockModule;
import scratch.model.mockModules.MockPlayerInput;
import scratch.model.mockModules.MockRoom;

import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;

public class ModelTest extends TestCase {

	private Model m;
	private Player p;
	private Injector injector = Guice.createInjector(new MockModule());

	@Before
	public void initialize() throws Exception {
		m = new Model();

		IPlayerInput playerInput = injector.getInstance(MockPlayerInput.class);
		Player p = new Player(playerInput, new Rectangle2D.Double(0, 0, 32, 32), 0);
	}

	public void testAddPlayer() throws Exception {
		m.addPlayer(p);
		assertTrue(m.getPlayers().contains(p));

	}

	public void testRemovePlayer() throws Exception {
		m.addPlayer(p);
		m.removePlayer(p);
		assertTrue(m.getPlayers().isEmpty());
	}

	public void testSetMap() throws Exception {
		//TODO currently untestable... you're not able to se any result
		assertTrue(false);
	}

	public void testUpdate() throws Exception {
		//TODO can't figure out anything to test. the update method just forwards the update to active rooms
	}
}