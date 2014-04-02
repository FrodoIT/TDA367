

import static org.junit.Assert.*;
import model.MoveDirection;
import model.PlayerInput;

import org.junit.Test;

public class PlayerInputTest {
	private final PlayerInput pInput;
	
	public PlayerInputTest(){
		pInput = new PlayerInput();
	}

	@Test
	public void testSetMoveInput(){
		pInput.setMoveInput(MoveDirection.EAST);
		assertTrue(pInput.getMoveInput().equals(MoveDirection.EAST));
		pInput.setMoveInput(MoveDirection.NONE);
	}
	
	@Test
	public void testSetAttackInput(){
		pInput.setAttackInput(true);
		assertTrue(pInput.getAttackInput());
		pInput.setAttackInput(false);
	}
	
	@Test
	public void testSetInteractInput(){
		pInput.setInteractInput(true);
		assertTrue(pInput.getInteractInput());
		pInput.setInteractInput(false);
	}
	
	@Test
	public void testGetAttackInput() {
		pInput.setAttackInput(true);
		assertTrue(pInput.getAttackInput());
	}
	@Test
	public void testGetMoveInput(){
		pInput.setMoveInput(MoveDirection.EAST);
		assertTrue(pInput.getMoveInput().equals(MoveDirection.EAST));
	}
	
	@Test
	public void testGetInteractInput(){
		pInput.setInteractInput(true);
		assertTrue(pInput.getInteractInput());
	}
	
}
