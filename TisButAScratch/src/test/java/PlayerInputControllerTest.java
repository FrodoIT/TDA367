import model.Model;

import org.junit.Test;

import controller.PlayerInputController;


public class PlayerInputControllerTest {
	private Model model;

	public PlayerInputControllerTest(Model model){
		this.model = model;
	}

	@Test
	public void updateTest() {
		PlayerInputController pc = new PlayerInputController(model);
		//TODO: Implement this when the update method is easier to test.

	}
}