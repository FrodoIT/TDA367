package scratch.model;

import com.google.inject.Guice;
import com.google.inject.Injector;
import junit.framework.TestCase;
import org.junit.Before;
import scratch.model.mockModules.MockModule;
import scratch.model.mockModules.MockPlayerInput;
import scratch.model.mockModules.MockRoom;

import java.util.ArrayList;
import java.util.List;

public class ModelTest extends TestCase {

	private Model m;
	private IPlayerInput playerInput;
	private Injector injector = Guice.createInjector(new MockModule());

	@Before
	public void initialize() throws Exception {
		m = new Model();

		playerInput = injector.getInstance(MockPlayerInput.class);
	}

	public void testAddPlayer() throws Exception {
		Player p = m.addPlayer(playerInput);

		assertTrue(p != null);

	}

	public void testRemovePlayer() throws Exception {
		Player p = m.addPlayer(playerInput);
		m.removePlayer(p);  //TODO the model is inconsistent. this should be fixed in another commit
							//Add player returns the player beeing added and remove player returns void
							//which also makes it hard to test since there is no way on knowing number of players in model from outside of he model

	}

	public void testSetMap() throws Exception {
		//TODO currently untestable... you're not able to se any result
		assertTrue(false);
	}

	public void testUpdate() throws Exception {
		//TODO can't figure out anything to test. the update method just forwards the update to active rooms
	}
}